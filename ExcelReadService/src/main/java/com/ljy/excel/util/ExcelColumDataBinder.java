package com.ljy.excel.util;

import java.lang.reflect.Field;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.domain.SupportExcelType;

public class ExcelColumDataBinder {

	@SuppressWarnings({ "deprecation" })
	public void putDataIntoObjAccording2CellType(Cell cell, Field field, Object obj)
		throws IllegalArgumentException, IllegalAccessException {
		CellType cellType = cell.getCellTypeEnum();
		if (isStringType(cellType)) {
			if (isStringType(field)) {
				field.set(obj, cell.getStringCellValue());
			} else {
				throw new IllegalArgumentException("[" + cell.getStringCellValue() + "] value type miss");
			}
		} else if (isNumberType(cellType)) {
			if (isDateType(cell)) {
				if (isDateType(field)) {
					field.set(obj, cell.getDateCellValue());
				} else {
					throw new IllegalArgumentException("[" + cell.getNumericCellValue() + "] value type miss");
				}
			} else {
				if (isNumberType(field)) {
					field.setInt(obj, (int) cell.getNumericCellValue());
				} else if (isDoubleType(field)) {
					field.setDouble(obj, cell.getNumericCellValue());
				} else {
					throw new IllegalArgumentException("[" + cell.getNumericCellValue() + "] value type miss");
				}
			}
		} else {
			ExcelColum excelColum = field.getAnnotation(ExcelColum.class);
			if (excelColum != null && excelColum.isRequired()) {
				throw new NullPointerException("[" + excelColum.name() + "] value is required");
			}
		}
	}

	private boolean isDateType(Cell cell) {
		return DateUtil.isCellDateFormatted(cell);
	}

	private boolean isNumberType(CellType cellType) {
		return cellType == CellType.NUMERIC;
	}

	private boolean isStringType(CellType cellType) {
		return cellType == CellType.STRING;
	}

	private boolean isStringType(Field field) {
		return field.getType().toString().toUpperCase().contains(SupportExcelType.STRING.toString());
	}

	private boolean isNumberType(Field field) {
		return field.getType().toString().toUpperCase().contains(SupportExcelType.INT.toString());
	}

	private boolean isDoubleType(Field field) {
		return field.getType().toString().toUpperCase().contains(SupportExcelType.DOUBLE.toString());
	}

	private boolean isDateType(Field field) {
		return field.getType().toString().toUpperCase().contains(SupportExcelType.DATE.toString());
	}

}
