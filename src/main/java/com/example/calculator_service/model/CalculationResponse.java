package com.example.calculator_service.model;

public class CalculationResponse {

	private String operation;
	private String input;
	private Object result;
	private String message;

	public CalculationResponse(String operation, String input, Object result, String message) {
		this.operation = operation;
		this.input = input;
		this.result = result;
		this.message = message;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
