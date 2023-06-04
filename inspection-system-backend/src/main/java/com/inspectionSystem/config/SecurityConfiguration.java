package com.inspectionSystem.config;


import com.inspectionSystem.filter.JwtAuthFilter;
import com.inspectionSystem.handler.JwtAuthenticationSuccessHandler;
import com.inspectionSystem.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private AuthService authService;

    @Resource
    JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/auth/access-deny")//未登录的都转发到这里
                .usernameParameter("phone")
                .loginProcessingUrl("/api/auth/login")//验证登录
                .successForwardUrl("/api/auth/login-success")//登录成功
                .successHandler(jwtAuthenticationSuccessHandler)
                .failureForwardUrl("/api/auth/login-failure")//登录失败
                .and()
                .logout()
                .logoutUrl("/api/auth/logout")//登出
                .logoutSuccessUrl("/api/auth/logout-success")//登出成功
                .and()
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter();
    }

    @Bean
    public CorsFilter corsFilter() {
        //创建CorsConfiguration对象后添加配置
        CorsConfiguration config = new CorsConfiguration();
        //设置放行哪些原始域，这里直接设置为所有
        config.addAllowedOriginPattern("*");
        //你可以单独设置放行哪些原始域 config.addAllowedOrigin("http://localhost:2222");
        //放行哪些原始请求头部信息
        config.addAllowedHeader("*");
        //放行哪些请求方式，*代表所有
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        //映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        //返回CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

}

