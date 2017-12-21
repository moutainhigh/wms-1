package com.teeny.wms.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see WebSecurityConfiguration
 * @since 2017/10/25
 */

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String ROLE_USER = "ROLE_USER";

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //通过通用的用户认证
        auth.userDetailsService(getUserService()).passwordEncoder(passwordEncoder());
        //auth.inMemoryAuthentication();   //通过内存中的用户认证
        //auth.jdbcAuthentication();       //通过JDBC中的用户认证
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}