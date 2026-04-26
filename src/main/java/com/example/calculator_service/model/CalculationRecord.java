package com.example.calculator_service.model;

public class CalculationRecord {

	private String operation;
	private String input;
	private Object result;
	private String timestamp;

	public CalculationRecord(String operation, String input, Object result, String timestamp) {
		this.operation = operation;
		this.input = input;
		this.result = result;
		this.timestamp = timestamp;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
