package com.ljy.excel.validator;

import java.util.Objects;

import org.springframework.web.multipart.MultipartFile;

import com.ljy.excel.exception.EmptyExcelFileException;
import com.ljy.excel.exception.InvalidExcelExtentionException;

public class SimpleExcelFileValidator implements ExcelFileValidator{
	public enum ExcelExtention { XLSX, XLS }
	
	@Override
	public ExcelExtention validationAfterGetOrElseThrow(MultipartFile file) {
		if(isEmptyFile(file)) {
			throw new EmptyExcelFileException();
		}
		String originalFilename = file.getOriginalFilename();
		int excelExtentionStartIdx = originalFilename.lastIndexOf(".");
		if(isEmptyExtention(excelExtentionStartIdx)) {
			throw new InvalidExcelExtentionException();
		}
		String extention = getExtention(originalFilename, excelExtentionStartIdx);
		try {
			return ExcelExtention.valueOf(extention);
		}catch (IllegalArgumentException e) {
			throw new InvalidExcelExtentionException();
		}
	}

	private boolean isEmptyExtention(int excelExtentionStartIdx) {
		return excelExtentionStartIdx == -1;
	}

	private String getExtention(String originalFilename, int excelExtentionStartIdx) {
		return originalFilename.substring(excelExtentionStartIdx + 1, originalFilename.length()).toUpperCase();
	}

	private boolean isEmptyFile(MultipartFile file) {
		return Objects.isNull(file);
	}

}
