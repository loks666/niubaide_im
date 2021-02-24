package com.niubaide.im.controller;

import com.niubaide.im.pojo.bean.TbUser;
import com.niubaide.im.pojo.vo.User;
import com.niubaide.im.service.UserService;
import com.niubaide.im.util.ResponseCode;
import com.niubaide.im.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author jax
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

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

    @PostMapping("/upload")
    public ServerResponse upload(MultipartFile file, String userid) {
        try {
            Assert.hasText(userid, "用户id不能为空");
            User user = userService.upload(file, userid);
            if (user != null) {
                System.out.println(user);
                return ServerResponse.success(ResponseCode.SUCCESS);
            }
            return ServerResponse.error("用户为空！");
        } catch (Exception e) {
            log.error("ImController#upload", e);
            return ServerResponse.error("上传服务异常:" + e.getMessage());
        }
    }

    @PostMapping("/updateNickname")
    public ServerResponse updateNickname(@RequestBody TbUser user) {
        try {
            Assert.hasText(user.getId(), "用户id不能为空");
            Assert.hasText(user.getNickname(), "用户名不能为空");
            int result = userService.updateNickname(user);
            if (result != 0) {
                return ServerResponse.success(ResponseCode.SUCCESS, "更新成功！");
            }
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), "更新失败！");
        } catch (Exception e) {
            log.error("ImController#updateNickname", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping("/findById")
    public ServerResponse findById(String userid) {
        try {
            Assert.hasText(userid, "用户id不能为空");
            User user = userService.findById(userid);
            if (user != null) {
                return ServerResponse.success(ResponseCode.SUCCESS, "查询成功！");
            }
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), "查询失败！");
        } catch (Exception e) {
            log.error("ImController#updateNickname", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

    @PostMapping("/findByUserName")
    public ServerResponse findByUserName(String userid, String friendUsername) {
        try {
            Assert.hasText(userid, "用户id不能为空！");
            Assert.hasText(friendUsername, "好友姓名不能为空！");
            User result = userService.findByUserName(userid,friendUsername);
            if (result != null) {
                return ServerResponse.success(ResponseCode.SUCCESS, "添加好友成功！");
            }
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), "添加好友失败！");
        } catch (Exception e) {
            log.error("ImController#findByUserName", e);
            return ServerResponse.error(ResponseCode.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}
