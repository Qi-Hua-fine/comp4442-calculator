package com.example.calculator_service.service;

import com.example.calculator_service.model.CalculationRecord;
import com.example.calculator_service.model.CalculationResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CalculatorService {

	private final List<CalculationRecord> history = new CopyOnWriteArrayList<>();

	public CalculationResponse add(Integer a, Integer b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("add", input, "Required parameter is missing");
		}

		int result = a + b;
		return success("add", input, result, a + " + " + b + " = " + result);
	}

	public CalculationResponse sub(Integer a, Integer b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("sub", input, "Required parameter is missing");
		}

		int result = a - b;
		return success("sub", input, result, a + " - " + b + " = " + result);
	}

	public CalculationResponse mul(Integer a, Integer b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("mul", input, "Required parameter is missing");
		}

		int result = a * b;
		return success("mul", input, result, a + " × " + b + " = " + result);
	}

	public CalculationResponse div(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("div", input, "Required parameter is missing");
		}
		if (b == 0) {
			return error("div", input, "Divisor cannot be 0");
		}

		double result = a / b;
		return success("div", input, result, a + " ÷ " + b + " = " + result);
	}

	public CalculationResponse square(Integer a) {
		String input = "a=" + a;
		if (a == null) {
			return error("square", input, "Required parameter is missing");
		}

		int result = a * a;
		return success("square", input, result, a + " squared = " + result);
	}

	public CalculationResponse sum(List<Integer> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("sum", input, "Number list cannot be empty");
		}

		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		return success("sum", input, total, " InputSum = " + total);
	}

	public CalculationResponse max(List<Integer> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("max", input, "Number list cannot be empty");
		}

		int max = numbers.get(0);
		for (int num : numbers) {
			if (num > max) max = num;
		}
		return success("max", input, max, " MaxNumber = " + max);
	}

	public CalculationResponse pow(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("pow", input, "Required parameter is missing");
		}

		double result = Math.pow(a, b);
		return success("pow", input, result, a + " ^ " + b + " = " + result);
	}

	public CalculationResponse sqrt(Double a) {
		String input = "a=" + a;
		if (a == null) {
			return error("sqrt", input, "Required parameter is missing");
		}
		if (a < 0) {
			return error("sqrt", input, "Cannot calculate square root of negative number");
		}

		double result = Math.sqrt(a);
		return success("sqrt", input, result, "sqrt(" + a + ") = " + result);
	}

	public CalculationResponse avg(List<Integer> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("avg", input, "Number list cannot be empty");
		}

		int total = 0;
		for (int num : numbers) {
			total += num;
		}
		double result = (double) total / numbers.size();
		return success("avg", input, result, " Average = " + result);
	}

	public CalculationResponse min(List<Integer> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("min", input, "Number list cannot be empty");
		}

		int min = numbers.get(0);
		for (int num : numbers) {
			if (num < min) min = num;
		}
		return success("min", input, min, " MinNumber = " + min);
	}

	public CalculationResponse percent(Double value, Double total) {
		String input = "value=" + value + ", total=" + total;
		if (hasMissing(value, total)) {
			return error("percent", input, "Required parameter is missing");
		}
		if (total == 0) {
			return error("percent", input, "Total cannot be 0");
		}

		double result = (value / total) * 100;
		return success("percent", input, result, value + " / " + total + " = " + result + "%");
	}

	public List<CalculationRecord> getHistory() {
		return new ArrayList<>(history);
	}

	public CalculationResponse clearHistory() {
		history.clear();
		return new CalculationResponse("clearHistory", "", null, "History cleared");
	}

	private boolean hasMissing(Number a, Number b) {
		return a == null || b == null;
	}

	private boolean isEmpty(List<Integer> numbers) {
		return numbers == null || numbers.isEmpty();
	}

	private String binaryInput(Number a, Number b) {
		return "a=" + a + ", b=" + b;
	}

	private String listInput(List<Integer> numbers) {
		return "numbers=" + numbers;
	}

	private CalculationResponse success(String operation, String input, Object result, String message) {
		history.add(new CalculationRecord(operation, input, result, Instant.now().toString()));
		return new CalculationResponse(operation, input, result, message);
	}

	private CalculationResponse error(String operation, String input, String message) {
		return new CalculationResponse(operation, input, null, "Error: " + message);
	}
}
