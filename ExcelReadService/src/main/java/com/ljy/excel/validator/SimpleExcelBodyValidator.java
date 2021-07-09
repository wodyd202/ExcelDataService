package com.ljy.excel.validator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;
import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.exception.EmptyRequiarCellException;

public class SimpleExcelBodyValidator extends AbstractExcelValidator {

	@Override
	public void validate(Sheet sheet, ExcelReadMetaData metaData, Class<?> classes) {
		List<ExcelColum> excelColumList = getExcelColumAnnotations(classes);

		for (int i = metaData.getBodyCellStartRowIdx(); i < metaData.getBodyCellEndRowIdx(); i++) {
			for (int j = metaData.getHeaderCellStartIdx(); j < metaData.getHeaderCellEndIdx(); j++) {
				isMergedCell(sheet, i, j);
				if (isEmptyRequiarCell(excelColumList, sheet, i, j, metaData)) {
					throw new EmptyRequiarCellException();
				}
			}
		}
	}

	private List<ExcelColum> getExcelColumAnnotations(Class<?> classes) {
		return Stream.of(classes.getDeclaredFields())
				.map(c -> c.getAnnotation(ExcelColum.class))
				.collect(Collectors.toList());
	}

	private boolean isEmptyRequiarCell(List<ExcelColum> excelColumList, Sheet sheet, int rowIdx, int colIdx,
			ExcelReadMetaData metaData) {
		Row cellRow = sheet.getRow(rowIdx);
		Cell focusCell = getCell(sheet, rowIdx, colIdx);
		
		if (isEmptyCell(focusCell)) {
			return isEmptyRequiarValue(excelColumList, colIdx, cellRow);
		}
		return false;
	}

	private Cell getCell(Sheet sheet, int rowIdx, int colIdx) {
		return sheet.getRow(rowIdx).getCell(colIdx);
	}

	private boolean isEmptyCell(Cell focusCell) {
		return Objects.isNull(focusCell);
	}
	
	private boolean isEmptyRequiarValue(List<ExcelColum> excelColumList, int colIdx, Row cellRow) {
		for (int i = 0; i < excelColumList.size(); i++) {
			ExcelColum excelColum = excelColumList.get(i);
			try {
				if (excelColum.isRequired() && cellRow.getCell(colIdx).getStringCellValue().equals(excelColum.value())) {
					return true;
				}
			} catch (Exception e) {
				return true;
			}
		}
		return false;
	}
}
