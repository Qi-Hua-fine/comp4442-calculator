package com.example.calculator_service.repository;

import com.example.calculator_service.model.CalculationRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalculationHistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	public CalculationHistoryRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void save(CalculationRecord record) {
		jdbcTemplate.update(
				"INSERT INTO calculation_history (operation, input, result, created_at) VALUES (?, ?, ?, ?)",
				record.getOperation(),
				record.getInput(),
				String.valueOf(record.getResult()),
				record.getTimestamp()
		);
	}

	public List<CalculationRecord> findAll() {
		return jdbcTemplate.query(
				"SELECT operation, input, result, created_at FROM calculation_history ORDER BY id",
				(rs, rowNum) -> new CalculationRecord(
						rs.getString("operation"),
						rs.getString("input"),
						rs.getString("result"),
						rs.getString("created_at")
				)
		);
	}

	public void deleteAll() {
		jdbcTemplate.update("DELETE FROM calculation_history");
	}
}
