package com.gc.springcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
/**
 * 
 * @author leader zhuo
 * 
 * 具体哪些URL需要拦截，哪些URL需要哪些权限才能访问
 * 
 * 
 */
@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/project/test")
		.hasAuthority("abc")
		.antMatchers("/project/get").hasAuthority("bcd1") 
		//此处有点坑 如果把.antMatchers("/**").authenticated(); 写在.antMatchers("/project/test")
		//.hasAuthority("abc") 上面 那么.hasAuthority("abc") 无效
		.antMatchers("/**").authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("foo").tokenStore(tokenStore());
	}

	/*
	 * 此处在配置文件中自己定义：
	 *    1、要求与analyzer-auth中要一样
	 *    2、也可以自己定义随便写，比如converter.setSigningKey("123123");那么analyzer-auth重也要是converter.setSigningKey("123123")
	 * 
	 * 
	 */
	@ConfigurationProperties("jwt")
	@Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey("123123");
		return converter;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenEnhancer());
	}
	

}
