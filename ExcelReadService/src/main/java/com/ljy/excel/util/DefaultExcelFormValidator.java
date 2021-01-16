package com.ljy.excel.util;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public class DefaultExcelFormValidator implements ExcelFormValidator, RequiredObjectChecker {
	private Class<?> excelBindClassType;
	private ExcelHeaderValidator excelHeaderValidator = new DefaultExcelHeaderValidator();
	private ExcelDataBodyValidator excelDataBodyValidator = new DefaultExcelDataBodyValidator();

	@Override
	public void valid(Sheet sheet, ExcelDataReadMetaInfo metaInfo) {
		checkIsNullObjects(excelHeaderValidator, excelDataBodyValidator);
		excelHeaderValidator.valid(excelBindClassType, sheet, metaInfo);
		excelDataBodyValidator.valid(excelBindClassType, sheet, metaInfo);
	}

	public void setExcelDataBodyValidator(ExcelDataBodyValidator excelDataBodyValidator) {
		this.excelDataBodyValidator = excelDataBodyValidator;
	}

	public void setExcelHeaderValidator(ExcelHeaderValidator excelHeaderValidator) {
		this.excelHeaderValidator = excelHeaderValidator;
	}

	public DefaultExcelFormValidator(Class<?> excelBindClassType) {
		this.excelBindClassType = excelBindClassType;
	}
}