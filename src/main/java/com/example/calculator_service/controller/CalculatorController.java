package com.example.calculator_service.controller;

import com.example.calculator_service.service.CalculatorService;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
public class CalculatorController {

	private final CalculatorService calculatorService;

	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@GetMapping("/add")
	public String add(
			@RequestParam(required = false) Integer a,
			@RequestParam(required = false) Integer b
	) {
		return calculatorService.add(a, b);
	}

	@GetMapping("/sub")
	public String sub(
			@RequestParam(required = false) Integer a,
			@RequestParam(required = false) Integer b
	) {
		return calculatorService.sub(a, b);
	}

	@GetMapping("/mul")
	public String mul(
			@RequestParam(required = false) Integer a,
			@RequestParam(required = false) Integer b
	) {
		return calculatorService.mul(a, b);
	}

	@GetMapping("/div")
	public String div(
			@RequestParam(required = false) Double a,
			@RequestParam(required = false) Double b
	) {
		return calculatorService.div(a, b);
	}

	@GetMapping("/square")
	public String square(
			@RequestParam(required = false) Integer a
	) {
		return calculatorService.square(a);
	}

	@GetMapping("/sum")
	public String sum(@RequestParam(required = false) List<Integer> numbers) {
		return calculatorService.sum(numbers);
	}

	@GetMapping("/max")
	public String max(@RequestParam(required = false) List<Integer> numbers) {
		return calculatorService.max(numbers);
	}

	@GetMapping("/pow")
	public String pow(
			@RequestParam(required = false) Double a,
			@RequestParam(required = false) Double b
	) {
		return calculatorService.pow(a, b);
	}

	@GetMapping("/sqrt")
	public String sqrt(
			@RequestParam(required = false) Double a
	) {
		return calculatorService.sqrt(a);
	}

	@GetMapping("/avg")
	public String avg(@RequestParam(required = false) List<Integer> numbers) {
		return calculatorService.avg(numbers);
	}

	@GetMapping("/min")
	public String min(@RequestParam(required = false) List<Integer> numbers) {
		return calculatorService.min(numbers);
	}

	@GetMapping("/percent")
	public String percent(
			@RequestParam(required = false) Double value,
			@RequestParam(required = false) Double total
	) {
		return calculatorService.percent(value, total);
	}

	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class
	})
	public String handleBadRequest() {
		return "Error: Invalid request parameter";
	}
}
