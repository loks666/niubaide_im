package com.niubaide.im.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niubaide.im.mapper.UserMapper;
import com.niubaide.im.pojo.po.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

/**
 * @Author 飞飞
 * @Description
 * @Date 2020/6/14 18:04
 */
class UserServiceImplTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void getAllUser() {
    }


    @Test
    void login() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("xiaoming");
        tbUser.setPassword("c4ca4238a0b923820dcc509a6f75849b");
        tbUser = userMapper.selectOne(new QueryWrapper<>(tbUser));
        System.out.println(tbUser);
    }
    @Test
    public void test(){
        System.out.println(DigestUtils.md5DigestAsHex("1".getBytes()));
    }
}