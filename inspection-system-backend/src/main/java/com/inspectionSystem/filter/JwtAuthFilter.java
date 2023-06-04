package com.inspectionSystem.filter;


import com.inspectionSystem.entity.User;
import com.inspectionSystem.service.UserService;
import com.inspectionSystem.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Resource
    private JwtUtils jwtUtils;


    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseJwt(request);
            if (token != null) {
                UserDetails userDetails = getUserDetails(token);
                if (jwtUtils.validateToken(token, userDetails)) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }

    public UserDetails getUserDetails(String token) throws UnsupportedEncodingException {
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userService.findUserByPhone(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getPhone())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}

