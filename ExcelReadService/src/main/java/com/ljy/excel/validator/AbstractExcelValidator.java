package com.ljy.excel.validator;

import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ljy.excel.ExcelReadMetaData;
import com.ljy.excel.exception.InvalidExcelHeaderException;

abstract public class AbstractExcelValidator {
	abstract public void validate(Sheet sheet, ExcelReadMetaData metaData, Class<?> classes);

	// 해당 cell이 다른 cell과 병합 되어있는지 확인하는 로직
	protected boolean isMergedCell(Sheet sheet, int rowIdx, int colIdx) {
		for (int i = 0; i < sheet.getNumMergedRegions(); ++i) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			if (rowIdx >= range.getFirstRow() && rowIdx <= range.getLastRow() && colIdx >= range.getFirstColumn()
					&& colIdx <= range.getLastColumn()) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	protected void cellTypeValidation(Sheet sheet, int rowIdx, int colIdx) {
		Cell focusCell = sheet.getRow(rowIdx).getCell(colIdx);
		if (Objects.isNull(focusCell)) {
			throw new InvalidExcelHeaderException();
		}
		if (focusCell.getCellTypeEnum().equals(CellType.BLANK)) {
			throw new InvalidExcelHeaderException();
		}
	}
}
