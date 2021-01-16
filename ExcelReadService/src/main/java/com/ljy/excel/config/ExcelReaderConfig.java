package com.ljy.excel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ljy.excel.domain.TestMenu;
import com.ljy.excel.service.ExcelDataReadService;
import com.ljy.excel.service.ExcelDataReader;
import com.ljy.excel.util.DefaultExcelFormValidator;
import com.ljy.excel.util.ExcelFormValidator;
import com.ljy.excel.util.TestListExcelDataReader;

@Configuration
public class ExcelReaderConfig {

	@Bean
	public ExcelDataReader<TestMenu> excelDataReader() {
		return new TestListExcelDataReader();
	}

	@Bean
	public ExcelFormValidator excelFormValidator() {
		return new DefaultExcelFormValidator(excelDataReader());
	}

	@Bean
	public ExcelDataReadService excelDataReadService() {
		return new ExcelDataReadService(excelDataReader(), excelFormValidator());
	}
}
