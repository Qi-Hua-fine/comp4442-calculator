package com.example.calculator_service.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

	public String add(Integer a, Integer b) {
		if (hasMissing(a, b)) {
			return error("Required parameter is missing");
		}
		return a + " + " + b + " = " + (a + b);
	}

	public String sub(Integer a, Integer b) {
		if (hasMissing(a, b)) {
			return error("Required parameter is missing");
		}
		return a + " - " + b + " = " + (a - b);
	}

	public String mul(Integer a, Integer b) {
		if (hasMissing(a, b)) {
			return error("Required parameter is missing");
		}
		return a + " × " + b + " = " + (a * b);
	}

	public String div(Double a, Double b) {
		if (hasMissing(a, b)) {
			return error("Required parameter is missing");
		}
		if (b == 0) {
			return error("Divisor cannot be 0");
		}
		return a + " ÷ " + b + " = " + (a / b);
	}

	public String square(Integer a) {
		if (a == null) {
			return error("Required parameter is missing");
		}
		return a + " squared = " + (a * a);
	}

	public String sum(List<Integer> numbers) {
		if (isEmpty(numbers)) {
			return error("Number list cannot be empty");
		}

		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return " InputSum = " + total;
	}

	public String max(List<Integer> numbers) {
		if (isEmpty(numbers)) {
			return error("Number list cannot be empty");
		}

		int max = numbers.get(0);
		for (int num : numbers) {
			if (num > max) max = num;
		}
		return " MaxNumber = " + max;
	}

	public String pow(Double a, Double b) {
		if (hasMissing(a, b)) {
			return error("Required parameter is missing");
		}
		return a + " ^ " + b + " = " + Math.pow(a, b);
	}

	public String sqrt(Double a) {
		if (a == null) {
			return error("Required parameter is missing");
		}
		if (a < 0) {
			return error("Cannot calculate square root of negative number");
		}
		return "sqrt(" + a + ") = " + Math.sqrt(a);
	}

	public String avg(List<Integer> numbers) {
		if (isEmpty(numbers)) {
			return error("Number list cannot be empty");
		}

		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return " Average = " + ((double) total / numbers.size());
	}

	public String min(List<Integer> numbers) {
		if (isEmpty(numbers)) {
			return error("Number list cannot be empty");
		}

		int min = numbers.get(0);
		for (int num : numbers) {
			if (num < min) min = num;
		}
		return " MinNumber = " + min;
	}

	public String percent(Double value, Double total) {
		if (hasMissing(value, total)) {
			return error("Required parameter is missing");
		}
		if (total == 0) {
			return error("Total cannot be 0");
		}
		return value + " / " + total + " = " + ((value / total) * 100) + "%";
	}

	private boolean hasMissing(Number a, Number b) {
		return a == null || b == null;
	}

	private boolean isEmpty(List<Integer> numbers) {
		return numbers == null || numbers.isEmpty();
	}

	private String error(String message) {
		return "Error: " + message;
	}
}
