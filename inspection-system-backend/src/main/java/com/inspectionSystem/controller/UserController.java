package com.inspectionSystem.controller;


import com.inspectionSystem.entity.User;
import com.inspectionSystem.entity.details.PasswordChange;
import com.inspectionSystem.entity.details.UserDetails;
import com.inspectionSystem.entity.resp.RestBean;
import com.inspectionSystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    PasswordEncoder encoder;

    @GetMapping("/userInfo")
    public RestBean<?> userInfo(Principal principal) {
        User user = userService.findUserByPhone(principal.getName());
        user.setPassword("");
        return new RestBean<>(HttpStatus.OK, "获取成功！", user);
    }

    @GetMapping("")
    public RestBean<?> user() {
        return new RestBean<>(HttpStatus.OK, "获取成功！", userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public RestBean<?> user(@PathVariable("id") Integer id) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", userService.findUserById(id));
    }

    @PostMapping("/{id}/change-password")
    public RestBean<?> changePassword(@PathVariable("id") Integer id,
                                      @RequestBody PasswordChange passwordChange) {
        Optional<User> user = Optional.ofNullable(userService.findUserById(id));
        RestBean<?> restBean;
        if (user.isPresent() && encoder.matches(passwordChange.getOldPassword(), user.get().getPassword())) {
            userService.changePassword(id, encoder.encode(passwordChange.getNewPassword()));
            restBean = new RestBean<>(HttpStatus.OK, "修改成功!");
        } else {
            restBean = new RestBean<>(HttpStatus.BAD_REQUEST, user.isPresent() ? "修改失败，密码错误!" : "修改失败，用户不存在!");
        }
        return restBean;

    }

    @PostMapping("/{id}")
    public RestBean<?> changeUser(@RequestBody User user) {
        Optional<User> userOptional = Optional.ofNullable(userService.findUserByPhone(user.getPhone()));
        RestBean<?> restBean;
        if (userOptional.isPresent() && !userOptional.get().getId().equals(user.getId())) {
            restBean = new RestBean<>(HttpStatus.BAD_REQUEST, "修改失败，手机号冲突!");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userService.changeUser(user, true);
            restBean = new RestBean<>(HttpStatus.OK, "修改成功！");
        }
        return restBean;
    }

    @PutMapping("")
    public RestBean<?> register(@RequestBody User user) {
        Optional<User> optionalUser = Optional.ofNullable(userService.findUserByPhone(user.getPhone()));
        if (optionalUser.isPresent()) {
            return new RestBean<>(HttpStatus.BAD_REQUEST, "注册失败，手机号已被注册！");
        }
        try {
            user.setPassword(encoder.encode("123456"));
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestBean<>(HttpStatus.BAD_REQUEST, "注册失败，服务器内部错误！");
        }
        return new RestBean<>(HttpStatus.OK, "用户注册成功!");
    }

    @DeleteMapping("/{id}")
    public RestBean<?> remove(@PathVariable("id") Integer id) {
        userService.removeUserById(id);
        return new RestBean<>(HttpStatus.OK, "删除成功！");
    }

    @GetMapping("/kpi/{id}")
    public RestBean<?> kpi(@PathVariable("id") Integer id) {
        return new RestBean<>(HttpStatus.OK, "获取成功！", userService.getKpiById(id));
    }

}
