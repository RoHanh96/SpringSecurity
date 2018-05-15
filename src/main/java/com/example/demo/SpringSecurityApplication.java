package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.controller.MainController;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		System.out.println("aaaaa");
		SpringApplication.run(SpringSecurityApplication.class, args);
	}
}
