package com.ljy.excel.service;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ljy.excel.domain.FileInfo;

public class ExcelFileReader {
	private final FileInfo fileInfo;

	public Workbook getWorkBook() throws IOException, InvalidFormatException {
		Workbook wb = null;
		OPCPackage opcPackage = OPCPackage.open(fileInfo.getResource().getInputStream());
		wb = new XSSFWorkbook(opcPackage);
		return wb;
	}

	public Sheet getWorkSheet(Workbook wb, int sheetNum) {
		return wb.getSheetAt(sheetNum);
	}

	private void checkFileExtention(FileInfo fileInfo) {
		if (fileInfo == null) {
			throw new IllegalArgumentException("excel file not be null.");
		}
		if (isNotExcelFile(fileInfo)) {
			throw new IllegalArgumentException("Not an excel file format.");
		}
	}

	private boolean isNotExcelFile(FileInfo fileInfo) {
		if (fileInfo.getResource() == null) {
			throw new IllegalArgumentException("Not an excel file format.");
		}
		return !fileInfo.getFileExtention().toUpperCase().equals("XLSX");
	}

	public ExcelFileReader(FileInfo fileInfo) {
		checkFileExtention(fileInfo);
		this.fileInfo = fileInfo;
	}

}
