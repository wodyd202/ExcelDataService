package com.ljy.excel;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

import com.ljy.excel.validator.AbstractExcelValidator;
import com.ljy.excel.validator.SimpleExcelFileValidator.ExcelExtention;
import com.ljy.excel.validator.SimpleExcelHeaderValidator;

public class ExcelHeaderTest extends ExcelTest {

	@Test
	void test() {
		Workbook workBook = getWorkBook("menu_success.xlsx", ExcelExtention.XLSX);
		AbstractExcelValidator headerValidator = new SimpleExcelHeaderValidator();
		ExcelReadMetaData metaData = Fixture.aExcelReadMetaData().build();
		headerValidator.validate(workBook.getSheetAt(0), metaData, TestData.class);
	}

}
