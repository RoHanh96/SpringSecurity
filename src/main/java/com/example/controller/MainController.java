package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String index() {
		System.out.println("Redirect index");
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
	@RequestMapping(value= "/login_custom", method = RequestMethod.GET)
	public String getLogin() {
		System.out.println("Redirect login");
		return "login_custom";
	}
}
