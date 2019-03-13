package com.gc.springcloud.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@RequestMapping("/test")
	public String test(HttpServletRequest request) {

		System.out.println("----------------header----------------");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			System.out.println(key + ": " + request.getHeader(key));
		}
		System.out.println("----------------header----------------");
		return "hellooooooooooooooo!";
	}
	
	@RequestMapping("/get")
	public String get(HttpServletRequest request) {

		System.out.println("----------------header----------------");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			System.out.println(key + ": " + request.getHeader(key));
		}
		System.out.println("----------------header----------------");
		return "hellooooooooooooooo!";
	}
}
