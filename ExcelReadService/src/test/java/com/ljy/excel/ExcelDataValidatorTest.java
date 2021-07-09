package com.ljy.excel;

import org.junit.jupiter.api.Test;

import com.ljy.excel.validator.ExcelDataValidator;

public class ExcelDataValidatorTest {
	
	@Test
	void test() {
		ExcelDataValidator validator = new ExcelDataValidator();
		validator.validation(TestData.class);
	}
}
