package com.example.calculator_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

	//   my work finish：FANG Keshen

	// 4. 除法
	@GetMapping("/div")
	public String div(
			@RequestParam double a,
			@RequestParam double b
	) {
		if (b == 0) {
			return "错误：除数不能为 0";
		}
		return a + " ÷ " + b + " = " + (a / b);
	}

	// 5. 平方
	@GetMapping("/square")
	public String square(
			@RequestParam int a
	) {
		return a + " 的平方 = " + (a * a);
	}

	// 6. 多个数字求和
	@GetMapping("/sum")
	public String sum(@RequestParam List<Integer> numbers) {
		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return "输入数字总和 = " + total;
	}
}