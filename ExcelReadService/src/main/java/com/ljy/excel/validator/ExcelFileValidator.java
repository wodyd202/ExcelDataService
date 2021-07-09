package com.ljy.excel.validator;

import org.springframework.web.multipart.MultipartFile;

import com.ljy.excel.validator.SimpleExcelFileValidator.ExcelExtention;

public interface ExcelFileValidator {
	ExcelExtention validationAfterGetOrElseThrow(MultipartFile file);
}
