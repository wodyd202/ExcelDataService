package com.ljy.excel.validator;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;

public class SimpleExcelFormValidator implements ExcelFormValidator {
	private SimpleExcelHeaderValidator headerValidator;
	private SimpleExcelBodyValidator bodyValidator;
	
	@Override
	public void validate(Sheet sheet, ExcelReadMetaData metaData, Class<?> classes) {
		headerValidator.validate(sheet, metaData, classes);
		bodyValidator.validate(sheet, metaData, classes);
	}
}
