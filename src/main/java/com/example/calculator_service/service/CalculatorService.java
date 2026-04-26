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

	public String pow(double a, double b) {
		return a + " ^ " + b + " = " + Math.pow(a, b);
	}

	public String sqrt(double a) {
		if (a < 0) {
			return "Error: Cannot calculate square root of negative number";
		}
		return "sqrt(" + a + ") = " + Math.sqrt(a);
	}

	public String avg(List<Integer> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return "Error: Number list cannot be empty";
		}

		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return " Average = " + ((double) total / numbers.size());
	}

	public String min(List<Integer> numbers) {
		if (numbers == null || numbers.isEmpty()) {
			return "Error: Number list cannot be empty";
		}

		int min = numbers.get(0);
		for (int num : numbers) {
			if (num < min) min = num;
		}
		return " MinNumber = " + min;
	}

	public String percent(double value, double total) {
		if (total == 0) {
			return "Error: Total cannot be 0";
		}
		return value + " / " + total + " = " + ((value / total) * 100) + "%";
	}
}
