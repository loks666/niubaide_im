package com.niubaide.im.controller;

import com.niubaide.im.pojo.vo.User;
import com.niubaide.im.util.ResponseCode;
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
@RequestMapping("/user")
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
            Assert.hasText(user.getUsername(), "用户名不能为空");
            Assert.hasText(user.getPassword(), "用户密码不能为空");
            User result = userService.login(user.getUsername(), user.getPassword());
            return ServerResponse.success(ResponseCode.LOGIN_SUCCESS, result);
        } catch (NullPointerException e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.NULL_PARAM);
        } catch (IllegalArgumentException e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.ILLEGAL_PARAMETER);
        } catch (Exception e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ServerResponse register(@RequestBody TbUser user) {
        try {
            Assert.hasText(user.getUsername(), "用户名不能为空");
            Assert.hasText(user.getPassword(), "用户密码不能为空");
            int register = userService.register(user);
            if (register == 1) {
                ServerResponse<Object> success = ServerResponse.success(ResponseCode.REGISTER_SUCCESS);
                return success;
            }
            return ServerResponse.error(ResponseCode.REGISTER_FAIL);
        } catch (NullPointerException e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.NULL_PARAM);
        } catch (IllegalArgumentException e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.ILLEGAL_PARAMETER);
        } catch (RuntimeException e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("ImController#login", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR);
        }
    }

}
