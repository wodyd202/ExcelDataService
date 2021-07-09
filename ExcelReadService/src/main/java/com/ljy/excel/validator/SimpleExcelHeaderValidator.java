package com.ljy.excel.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;
import com.ljy.excel.exception.InvalidExcelHeaderException;

public class SimpleExcelHeaderValidator extends AbstractExcelValidator {

	@Override
	public void validate(Sheet sheet, ExcelReadMetaData metaData, Class<?> classes) {
		Row headerRow = sheet.getRow(0);
		List<String> cellList = new ArrayList<>();
		for (int i = metaData.getHeaderCellStartIdx(); i < metaData.getHeaderCellEndIdx(); i++) {
			Cell focusCell = headerRow.getCell(i);
			cellValidation(sheet, headerRow, i);
			String cellValue = focusCell.getStringCellValue();
			validationDupCellName(cellList, cellValue);
		}
	}

	private void cellValidation(Sheet sheet, Row headerRow, int i) {
		if(isMergedCell(sheet, headerRow.getRowNum(), i)) {
			throw new InvalidExcelHeaderException();
		}
		cellTypeValidation(sheet, headerRow.getRowNum(), i);
	}

	private void validationDupCellName(List<String> cellList, String cellValue) {
		if(cellList.contains(cellValue)) {
			throw new InvalidExcelHeaderException();
		}else {
			cellList.add(cellValue);
		}
	}

}
