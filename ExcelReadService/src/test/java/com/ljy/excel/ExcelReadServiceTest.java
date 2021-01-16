package com.ljy.excel;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;
import com.ljy.excel.domain.FileInfo;
import com.ljy.excel.domain.ExcelDataExample;
import com.ljy.excel.service.ExcelDataReader;
import com.ljy.excel.service.ExcelFileReader;
import com.ljy.excel.util.DefaultExcelHeaderValidator;
import com.ljy.excel.util.ExcelHeaderValidator;
import com.ljy.excel.util.TestListExcelDataReader;

class ExcelReadServiceTest {

	@Test
	public void readExcelHeader_margeCell_1() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
			assertExcelTestWrongExcelHeaderForm("menu_margeCell_1.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelHeader_margeCell_2() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
			assertExcelTestWrongExcelHeaderForm("menu_margeCell_2.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelHeader_empty_cell() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
			assertExcelTestWrongExcelHeaderForm("menu_empty_cell.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelHeader_duplicate_cell() throws IOException {
		assertThrows(IllegalArgumentException.class, () -> {
			ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
			assertExcelTestWrongExcelHeaderForm("menu_duplicate_cell.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelHeader_success() throws IOException, InvalidFormatException {
		ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 5);
		assertExcelTestWrongExcelHeaderForm("menu_success.xlsx", excelDataReadMetaInfo);
	}

	@Test
	public void readExcelBody_success() throws IOException, InvalidFormatException {
		ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
		assertExcelBodyDataReadTest("body_deference_type.xlsx", excelDataReadMetaInfo);
	}

	@Test
	public void readExcelBody_cell_marge_1() throws IOException {
		ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 2, 0, 9);
		assertThrows(IllegalArgumentException.class, () -> {
			assertExcelBodyDataReadTest("body_marge_cell_1.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelBody_cell_marge_2() throws IOException {
		ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 4, 0, 9);
		assertThrows(IllegalArgumentException.class, () -> {
			assertExcelBodyDataReadTest("body_marge_cell_2.xlsx", excelDataReadMetaInfo);
		});
	}

	@Test
	public void readExcelBody_not_contain_required_value() throws IOException {
		ExcelDataReadMetaInfo excelDataReadMetaInfo = new ExcelDataReadMetaInfo(1, 3, 0, 9);
		assertThrows(IllegalArgumentException.class, () -> {
			assertExcelBodyDataReadTest("body_not_contain_required_value.xlsx", excelDataReadMetaInfo);
		});
	}

	private void assertExcelBodyDataReadTest(String fileName, ExcelDataReadMetaInfo excelDataReadMetaInfo)
		throws IOException, InvalidFormatException {
		Resource resource = new ClassPathResource(fileName);
		FileInfo fileInfo = new FileInfo(resource);
		ExcelFileReader excelFileReader = getExcelFileReader(fileInfo);
		Workbook workBook = excelFileReader.getWorkBook();

		ExcelDataReader<ExcelDataExample> excelDataReader = new TestListExcelDataReader();
		excelDataReader.readData(excelFileReader.getWorkSheet(workBook, 0), excelDataReadMetaInfo);
	}

	private void assertExcelTestWrongExcelHeaderForm(String fileName, ExcelDataReadMetaInfo excelDataReadMetaInfo)
		throws IOException, InvalidFormatException {
		Resource resource = new ClassPathResource(fileName);
		FileInfo fileInfo = new FileInfo(resource);
		ExcelFileReader excelFileReader = getExcelFileReader(fileInfo);
		Workbook workBook = excelFileReader.getWorkBook();

		ExcelHeaderValidator excelHeaderValidator = new DefaultExcelHeaderValidator();
		excelHeaderValidator.valid(ExcelDataExample.class, excelFileReader.getWorkSheet(workBook, 0), excelDataReadMetaInfo);
	}

	private ExcelFileReader getExcelFileReader(FileInfo fileInfo) {
		return new ExcelFileReader(fileInfo);
	}
}
