package com.gc.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author leader zhuo
 * 只是测试用的 
 *
 *
 */
@RestController
public class GatewayController {
	@RequestMapping("/aa")
	public String aa() {
		return "asd";
	}

}
