package com.ljy.excel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import com.ljy.excel.exception.EmptyExcelFileException;
import com.ljy.excel.exception.InvalidExcelExtentionException;
import com.ljy.excel.validator.ExcelFileValidator;
import com.ljy.excel.validator.SimpleExcelFileValidator;

public class ExcelFileValidatorTest {
	ExcelFileValidator validator = new SimpleExcelFileValidator();

	@Test
	void isEmptyFileThenThrow() {
		assertThrows(EmptyExcelFileException.class, ()->{
			validator.validationAfterGetOrElseThrow(null);
		});
	}
	
	@Test
	void invalidExcelFileExtentionThenThrow() {
		assertThrows(InvalidExcelExtentionException.class, ()->{
			MockMultipartFile file = new MockMultipartFile("name", "originFileName.img", "contentType", new byte[] {});
			validator.validationAfterGetOrElseThrow(file);
		});
	}
	
	@Test
	void notExistOriginFileNameThenThrow() {
		assertThrows(InvalidExcelExtentionException.class, ()->{
			MockMultipartFile file = new MockMultipartFile("name", "", "contentType", new byte[] {});
			validator.validationAfterGetOrElseThrow(file);
		});
	}
	
	@Test
	void success() {
		assertDoesNotThrow(()->{
			MockMultipartFile file = new MockMultipartFile("name", "originFileName.xls", "contentType", new byte[] {});
			validator.validationAfterGetOrElseThrow(file);
		});
	}
}
