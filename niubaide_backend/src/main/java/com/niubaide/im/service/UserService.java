package com.niubaide.im.service;

import com.niubaide.im.pojo.po.TbUser;
import com.niubaide.im.pojo.vo.User;

import java.util.List;

public interface UserService {

    List<TbUser> getAllUser();

    User login(String userName,String password);
}
