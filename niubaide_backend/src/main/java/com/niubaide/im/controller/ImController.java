package com.niubaide.im.controller;

import com.niubaide.im.common.ServerResponse;
import com.niubaide.im.pojo.TbUser;
import com.niubaide.im.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<TbUser> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public ServerResponse login(@RequestBody TbUser user){
        return ServerResponse.success();
    }

}
