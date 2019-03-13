package com.gc.springcloud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 
 * @author leader zhuo
 * 个人感觉此类可以写可以不写 没什么太大卵用
 * 可以把http.authorizeRequests().antMatchers("/me").permitAll(); 写到SecurityConfig.java 中
 */
@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
	  @Override
      public void configure(HttpSecurity http) throws Exception {
          http.authorizeRequests().antMatchers("/me").permitAll();
      }
}
