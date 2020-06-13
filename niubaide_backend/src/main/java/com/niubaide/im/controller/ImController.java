package com.niubaide.im.controller;

import com.niubaide.im.pojo.TbUser;
import com.niubaide.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ImController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<TbUser> getAllUser(){
        return userService.getAllUser();
    }

}
