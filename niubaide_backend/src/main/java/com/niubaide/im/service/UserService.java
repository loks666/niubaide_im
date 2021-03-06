package com.niubaide.im.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niubaide.im.pojo.bean.TbUser;
import com.niubaide.im.pojo.vo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService extends IService<TbUser> {

    List<TbUser> getAllUser();

    User login(String userName,String password);

    int register(TbUser user);

    User upload(MultipartFile file, String userid);

    int updateNickname(TbUser user);

    User findById(String userid);

    User findByUserName(String userid, String friendUsername);
}
