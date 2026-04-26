package com.example.calculator_service.model;

import java.util.Map;

public class CalculationStats {

	private int totalCalculations;
	private Map<String, Integer> countByOperation;
	private CalculationRecord latestCalculation;

	public CalculationStats(int totalCalculations, Map<String, Integer> countByOperation, CalculationRecord latestCalculation) {
		this.totalCalculations = totalCalculations;
		this.countByOperation = countByOperation;
		this.latestCalculation = latestCalculation;
	}

	public int getTotalCalculations() {
		return totalCalculations;
	}

	public void setTotalCalculations(int totalCalculations) {
		this.totalCalculations = totalCalculations;
	}

	public Map<String, Integer> getCountByOperation() {
		return countByOperation;
	}

	public void setCountByOperation(Map<String, Integer> countByOperation) {
		this.countByOperation = countByOperation;
	}

	public CalculationRecord getLatestCalculation() {
		return latestCalculation;
	}

	public void setLatestCalculation(CalculationRecord latestCalculation) {
		this.latestCalculation = latestCalculation;
	}
}
