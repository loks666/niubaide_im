package com.niubaide.im.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.niubaide.im.pojo.po.TbUser;
import com.niubaide.im.pojo.vo.User;

import java.util.List;

public interface UserService extends IService<TbUser> {

    List<TbUser> getAllUser();

    User login(String userName,String password);

    int register(TbUser user);
}
