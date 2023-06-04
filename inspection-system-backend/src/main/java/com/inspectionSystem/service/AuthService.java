package com.inspectionSystem.service;

import com.inspectionSystem.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<String> roles = user.getRoles();
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getPhone())
                .password(user.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }
}
