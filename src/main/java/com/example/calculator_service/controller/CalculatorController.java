package com.example.calculator_service.controller;

import com.example.calculator_service.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

	private final CalculatorService calculatorService;

	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@GetMapping("/add")
	public String add(
			@RequestParam int a,
			@RequestParam int b
	) {
		return calculatorService.add(a, b);
	}

	@GetMapping("/sub")
	public String sub(
			@RequestParam int a,
			@RequestParam int b
	) {
		return calculatorService.sub(a, b);
	}

	@GetMapping("/mul")
	public String mul(
			@RequestParam int a,
			@RequestParam int b
	) {
		return calculatorService.mul(a, b);
	}

	@GetMapping("/div")
	public String div(
			@RequestParam double a,
			@RequestParam double b
	) {
		return calculatorService.div(a, b);
	}

	@GetMapping("/square")
	public String square(
			@RequestParam int a
	) {
		return calculatorService.square(a);
	}

	@GetMapping("/sum")
	public String sum(@RequestParam List<Integer> numbers) {
		return calculatorService.sum(numbers);
	}

	@GetMapping("/max")
	public String max(@RequestParam List<Integer> numbers) {
		return calculatorService.max(numbers);
	}

	@GetMapping("/pow")
	public String pow(
			@RequestParam double a,
			@RequestParam double b
	) {
		return calculatorService.pow(a, b);
	}

	@GetMapping("/sqrt")
	public String sqrt(
			@RequestParam double a
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
			@RequestParam double value,
			@RequestParam double total
	) {
		return calculatorService.percent(value, total);
	}
}
