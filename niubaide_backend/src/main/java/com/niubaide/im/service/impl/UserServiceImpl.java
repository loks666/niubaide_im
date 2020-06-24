package com.niubaide.im.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.niubaide.im.expection.ImException;
import com.niubaide.im.mapper.UserMapper;
import com.niubaide.im.pojo.po.TbUser;
import com.niubaide.im.pojo.vo.User;
import com.niubaide.im.service.UserService;
import com.niubaide.im.util.FastDFSClient;
import com.niubaide.im.util.IdWorker;
import com.niubaide.im.util.QRCodeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Autowired
    private Environment env;

    @Autowired
    private QRCodeUtils qrCodeUtils;

    @Override
    public List<TbUser> getAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public User login(String userName, String password) {
        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<TbUser> tb = new QueryWrapper<>();
        // 用加密后的密码和用户名查询
        tb.eq("username", userName).eq("password", md5Pwd);
        TbUser tbUser = userMapper.selectOne(tb);
        if (tbUser != null) {
            User user = new User();
            BeanUtils.copyProperties(tbUser, user);
            return user;
        }
        throw new NullPointerException("没有该用户，请检查");
    }

    @Override
    public int register(TbUser user) {
        try {
            QueryWrapper<TbUser> tb = new QueryWrapper<>();
            tb.eq("username", user.getUsername());
            TbUser register = userMapper.selectOne(tb);
            if (register != null) {
                throw new RuntimeException("该用户已存在！");
            }
            // 雪花算法算id
            user.setId(idWorker.nextId());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

            // 生成二维码，将生成的二维码保存到数据库中
            String qrCode = "niubaide://" + user.getUsername();
            // 获取一个临时目录，保存二维码生成的图片
            String tempDir = env.getProperty("niubaide.tmpdir");
            // 要生成的二维码字符串
            String qrCodePath = tempDir + user.getUsername() + ".png";
            qrCodeUtils.createQRCode(qrCodePath, qrCode);

            // 拼接二维码生成路径
            String url = env.getProperty("fdfs.httpurl") + fastDFSClient.uploadFile(new File(qrCodePath));
            user.setQrcode(url);
            user.setNickname(user.getUsername());
            user.setCreatetime(new Date());
            return userMapper.insert(user);
        } catch (Exception e) {
            log.error("UserServiceImpl#register", e);
            throw new RuntimeException("注册失败！");
        }
    }

    @Override
    public User upload(MultipartFile file, String userid) {
        try {
            // fdfs返回地址不带域名前缀
            String url = fastDFSClient.uploadFace(file);
            String[] fileNameList = url.split("\\.");
            // 文件名
            String fileName = fileNameList[0];
            // 类型后缀名
            String ext = fileNameList[1];
            // 缩略图
            String picSmallUrl = fileName + "_150×150." + ext;
            // 获取url前缀
            String perfix = env.getProperty("fdfs.httpurl");
            TbUser tbUser = userMapper.selectById(userid);
            tbUser.setPicNormal(perfix + url);
            tbUser.setPicSmall(perfix + picSmallUrl);
            int i = userMapper.updateById(tbUser);
            if (i != 0) {
                User user = new User();
                BeanUtils.copyProperties(tbUser, user);
                return user;
            }
            throw new ImException("更新用户图片没有成功！");
        } catch (IOException e) {
            log.error("upload#ServiceImpl", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int updateNickname(TbUser user) {
        return userMapper.updateById(user);
    }

    @Override
    public User findById(String userid) {
        User user = new User();
        TbUser tbUser = userMapper.selectById(userid);
        BeanUtils.copyProperties(tbUser, user);
        return user;
    }
}
