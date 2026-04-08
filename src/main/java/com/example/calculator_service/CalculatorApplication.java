package com.example.calculator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	// 加法
	@GetMapping("/add")
	public String add(
			@RequestParam int a,
			@RequestParam int b
	) {
		return a + " + " + b + " = " + (a + b);
	}

	// 减法
	@GetMapping("/sub")
	public String sub(
			@RequestParam int a,
			@RequestParam int b
	) {
		return a + " - " + b + " = " + (a - b);
	}

	// 乘法
	@GetMapping("/mul")
	public String mul(
			@RequestParam int a,
			@RequestParam int b
	) {
		return a + " × " + b + " = " + (a * b);
	}
}