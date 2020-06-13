package com.niubaide.im.service.impl;

import com.niubaide.im.mapper.TbUserMapper;
import com.niubaide.im.pojo.TbUser;
import com.niubaide.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Override
    public List<TbUser> getAllUser() {
        return userMapper.selectList(null);
    }
}
