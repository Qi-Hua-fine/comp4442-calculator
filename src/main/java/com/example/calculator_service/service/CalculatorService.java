package com.example.calculator_service.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

	public String add(int a, int b) {
		return a + " + " + b + " = " + (a + b);
	}

	public String sub(int a, int b) {
		return a + " - " + b + " = " + (a - b);
	}

	public String mul(int a, int b) {
		return a + " × " + b + " = " + (a * b);
	}

	public String div(double a, double b) {
		if (b == 0) {
			return "Error: Divisor cannot be 0";
		}
		return a + " ÷ " + b + " = " + (a / b);
	}

	public String square(int a) {
		return a + " squared = " + (a * a);
	}

	public String sum(List<Integer> numbers) {
		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return " InputSum = " + total;
	}

	public String max(List<Integer> numbers) {
		int max = numbers.get(0);
		for (int num : numbers) {
			if (num > max) max = num;
		}
		return " MaxNumber = " + max;
	}
}
