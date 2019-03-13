package com.gc.springcloud.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
	@GetMapping("/me")
	Authentication userinfo(Authentication authentication) {
		return authentication;
	}
}
