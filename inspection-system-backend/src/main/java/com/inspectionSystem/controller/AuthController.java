package com.inspectionSystem.controller;

import com.inspectionSystem.entity.User;
import com.inspectionSystem.entity.details.ForgetPasswordDetails;
import com.inspectionSystem.entity.details.UserDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;



    @RequestMapping("/login-success")
    public RestBean<?> loginSuccess(HttpServletResponse response){
        Map<String, String> map = new HashMap<>();
        map.put("access_token", response.getHeader("Authorization"));
        return new RestBean<>(HttpStatus.OK, "登陆成功", map);
    }


    @RequestMapping("/logout-success")
    public RestBean<?> logoutSuccess(){
        return new RestBean<>(HttpStatus.OK, "退出成功");
    }


    @RequestMapping("/login-failure")
    public RestBean<?> loginFailure(){
        return new RestBean<>(HttpStatus.UNAUTHORIZED, "登录失败，用户名或密码错误");
    }

    @RequestMapping("/access-deny")
    public RestBean<?> accessDeny(){
        return new RestBean<>(HttpStatus.UNAUTHORIZED,"未验证，请先登录！");
    }


    @PostMapping("/register")
    public RestBean<?> register(@RequestBody UserDetails userDetails) {
        Optional<User> optionalUser = Optional.ofNullable(userService.findUserByPhone(userDetails.getPhone()));
        if (optionalUser.isPresent()) {
            return new RestBean<>(HttpStatus.BAD_REQUEST, "注册失败，手机号已被注册！");
        }
        try {
            userDetails.setPassword(encoder.encode(userDetails.getPassword()));
            userService.registerUser(userDetails);
        } catch (Exception e) {
            log.error("注册失败{}",e.getMessage());
            return new RestBean<>(HttpStatus.BAD_REQUEST,"注册失败，服务器内部错误！");
        }
        return new RestBean<>(HttpStatus.OK, "用户注册成功!");
    }

    @PostMapping("/forget-password")
    public RestBean<?> forgetPassword(@RequestBody ForgetPasswordDetails forgetPasswordDetails){
        Optional<User> user = Optional.ofNullable(userService.findUserByPhone(forgetPasswordDetails.getPhone()));
        RestBean<?> restBean;
        if (user.isPresent() && user.get().getPhone().equals(forgetPasswordDetails.getPhone()) && user.get().getIdCard().equals(forgetPasswordDetails.getIdCard())) {
            userService.changePassword(user.get().getId(), encoder.encode(forgetPasswordDetails.getNewPassword()));
            restBean = new RestBean<>(HttpStatus.OK, "修改成功!");
        } else {
            restBean = new RestBean<>(HttpStatus.BAD_REQUEST, user.isPresent() ? "修改失败，手机号或身份证错误!" : "修改失败，用户不存在!");
        }
        return restBean;
    }


}

