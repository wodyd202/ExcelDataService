package com.ljy.excel.util;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public interface ExcelHeaderValidator {
	void valid(Class<?> excelBindClassType, Sheet sheet, ExcelDataReadMetaInfo metaInfo);
}