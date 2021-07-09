package com.ljy.excel.validator;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;

public interface ExcelFormValidator {
	void validate(Sheet sheet, ExcelReadMetaData metaData, Class<?> classes);
}
