package com.niubaide.im.controller;

import com.niubaide.im.pojo.vo.User;
import com.niubaide.im.util.ServerResponse;
import com.niubaide.im.pojo.po.TbUser;
import com.niubaide.im.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ImController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public List<TbUser> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/login")
    public ServerResponse login(@RequestBody TbUser user) {
        try {
            Assert.hasText(user.getUsername(),"用户名不能为空");
            Assert.notNull(user.getPassword(),"用户密码不能为空");
            User result = userService.login(user.getUsername(), user.getPassword());
            ServerResponse<User> success = ServerResponse.success(result);
            return success;
        } catch (NullPointerException e) {
            log.error("ImController#login",e);
            return ServerResponse.error(e.getMessage());
        } catch (IllegalArgumentException e){
            return ServerResponse.error(e.getMessage());
        }
    }

}
