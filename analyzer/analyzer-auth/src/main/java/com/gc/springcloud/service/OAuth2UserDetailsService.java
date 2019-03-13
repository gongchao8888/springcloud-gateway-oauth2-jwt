package com.gc.springcloud.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author leader zhuo
 * 
 * 
 * 此处为了方便 没有到数据库中查找
 */
@Component
public class OAuth2UserDetailsService implements UserDetailsService {
  

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    	//用户有哪些权限
        authorities.add(new SimpleGrantedAuthority("abc"));
        authorities.add(new SimpleGrantedAuthority("bcd"));
        //登录时候 用户名为user，密码为123456
        //123456加密后为：$2a$10$aZDOWYEvK06TrxN6g0Mta.X3gtnj1sHPReRic5YRcOiXl4yMctwS6
    	return new User("user", "$2a$10$aZDOWYEvK06TrxN6g0Mta.X3gtnj1sHPReRic5YRcOiXl4yMctwS6", authorities);
    }
}
