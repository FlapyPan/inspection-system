package com.inspectionSystem.handler;

import com.inspectionSystem.util.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token;
        try {
            token = jwtUtils.generateToken(userDetails);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        response.addHeader("Authorization", "Bearer " + token);
        request.getRequestDispatcher("/api/auth/login-success").forward(request, response);
    }
}