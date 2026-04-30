package com.example.calculator_service.service;

import com.example.calculator_service.model.CalculationRecord;
import com.example.calculator_service.model.CalculationResponse;
import com.example.calculator_service.model.CalculationStats;
import com.example.calculator_service.repository.CalculationHistoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CalculatorService {

	private static final double SCIENTIFIC_UPPER_LIMIT = 1_000_000_000_000.0;
	private static final double SCIENTIFIC_LOWER_LIMIT = 0.000001;

	private final CalculationHistoryRepository historyRepository;

	public CalculatorService(CalculationHistoryRepository historyRepository) {
		this.historyRepository = historyRepository;
	}

	public CalculationResponse add(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("add", input, "Required parameter is missing");
		}

		double result = a + b;
		return success("add", input, result, a + " + " + b + " = ", "");
	}

	public CalculationResponse sub(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("sub", input, "Required parameter is missing");
		}

		double result = a - b;
		return success("sub", input, result, a + " - " + b + " = ", "");
	}

	public CalculationResponse mul(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("mul", input, "Required parameter is missing");
		}

		double result = a * b;
		return success("mul", input, result, a + " × " + b + " = ", "");
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
		return success("div", input, result, a + " ÷ " + b + " = ", "");
	}

	public CalculationResponse square(Double a) {
		String input = "a=" + a;
		if (a == null) {
			return error("square", input, "Required parameter is missing");
		}

		double result = a * a;
		return success("square", input, result, a + " squared = ", "");
	}

	public CalculationResponse sum(List<Double> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("sum", input, "Number list cannot be empty");
		}

		double total = 0;
		for (double num : numbers) {
			total += num;
		}
		return success("sum", input, total, " InputSum = ", "");
	}

	public CalculationResponse max(List<Double> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("max", input, "Number list cannot be empty");
		}

		double max = numbers.get(0);
		for (double num : numbers) {
			if (num > max) max = num;
		}
		return success("max", input, max, " MaxNumber = ", "");
	}

	public CalculationResponse pow(Double a, Double b) {
		String input = binaryInput(a, b);
		if (hasMissing(a, b)) {
			return error("pow", input, "Required parameter is missing");
		}

		double result = Math.pow(a, b);
		return success("pow", input, result, a + " ^ " + b + " = ", "");
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
		return success("sqrt", input, result, "sqrt(" + a + ") = ", "");
	}

	public CalculationResponse avg(List<Double> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("avg", input, "Number list cannot be empty");
		}

		double total = 0;
		for (double num : numbers) {
			total += num;
		}
		double result = total / numbers.size();
		return success("avg", input, result, " Average = ", "");
	}

	public CalculationResponse min(List<Double> numbers) {
		String input = listInput(numbers);
		if (isEmpty(numbers)) {
			return error("min", input, "Number list cannot be empty");
		}

		double min = numbers.get(0);
		for (double num : numbers) {
			if (num < min) min = num;
		}
		return success("min", input, min, " MinNumber = ", "");
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
		return success("percent", input, result, value + " / " + total + " = ", "%");
	}

	public List<CalculationRecord> getHistory() {
		return historyRepository.findAll();
	}

	public CalculationResponse clearHistory() {
		historyRepository.deleteAll();
		return new CalculationResponse("clearHistory", "", null, "History cleared");
	}

	public CalculationStats getStats() {
		List<CalculationRecord> records = getHistory();
		Map<String, Integer> countByOperation = new LinkedHashMap<>();

		for (CalculationRecord record : records) {
			countByOperation.put(
					record.getOperation(),
					countByOperation.getOrDefault(record.getOperation(), 0) + 1
			);
		}

		CalculationRecord latestCalculation = records.isEmpty() ? null : records.get(records.size() - 1);
		return new CalculationStats(records.size(), countByOperation, latestCalculation);
	}

	private boolean hasMissing(Number a, Number b) {
		return a == null || b == null;
	}

	private boolean isEmpty(List<Double> numbers) {
		return numbers == null || numbers.isEmpty();
	}

	private String binaryInput(Number a, Number b) {
		return "a=" + a + ", b=" + b;
	}

	private String listInput(List<Double> numbers) {
		return "numbers=" + numbers;
	}

	private CalculationResponse success(String operation, String input, double result, String messagePrefix, String messageSuffix) {
		if (!Double.isFinite(result)) {
			return error(operation, input, "The result is too large to calculate or display.");
		}

		String formattedResult = formatResult(result);
		String message = messagePrefix + formattedResult + messageSuffix;

		historyRepository.save(new CalculationRecord(operation, input, formattedResult, Instant.now().toString()));
		return new CalculationResponse(operation, input, formattedResult, message);
	}

	private String formatResult(double result) {
		double absoluteResult = Math.abs(result);
		if (absoluteResult >= SCIENTIFIC_UPPER_LIMIT || (absoluteResult > 0 && absoluteResult < SCIENTIFIC_LOWER_LIMIT)) {
			DecimalFormat scientificFormat = new DecimalFormat("0.###############E0", DecimalFormatSymbols.getInstance(Locale.US));
			return scientificFormat.format(result);
		}

		BigDecimal formatted = BigDecimal.valueOf(result)
				.setScale(10, RoundingMode.HALF_UP)
				.stripTrailingZeros();
		if (formatted.scale() < 0) {
			return formatted.setScale(0).toPlainString();
		}
		return formatted.toPlainString();
	}

	private CalculationResponse error(String operation, String input, String message) {
		return new CalculationResponse(operation, input, null, "Error: " + message);
	}
}
