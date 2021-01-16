package com.ljy.excel.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public class DefaultExcelDataBodyValidator implements ExcelDataBodyValidator {

	private CellMargeChecker cellMargeCheck = new CellMargeChecker();
	private ExcelObjectChecker excelObjectChecker = new DefaultExcelObjectChecker();

	@Override
	public void valid(Class<?> excelBindClassType, Sheet sheet, ExcelDataReadMetaInfo metaInfo) {
		List<ExcelColum> excelColumList = excelObjectChecker.getExcelColums(excelBindClassType);
		for (int i = metaInfo.getStartDataRowIdx(); i < metaInfo.getEndDataRowIdx(); i++) {
			for (int j = metaInfo.getStartDataColIdx(); j < metaInfo.getEndDataColIdx(); j++) {
				if (isEmptyEssentialCell(excelColumList, sheet, i, j, metaInfo)) {
					throw new IllegalArgumentException("[" + (i + 1) + "] the required data for the first row does not exist.");
				}
				if (cellMargeCheck.isMerged(sheet, i, j)) {
					throw new IllegalArgumentException("Merged Excel data cells are not allowed.");
				}
			}
		}
	}

	private boolean isEmptyEssentialCell(List<ExcelColum> excelColumList, Sheet sheet, int rowIdx, int colIdx,
		ExcelDataReadMetaInfo metaInfo) {
		Row headRow = metaInfo.getHeadRow(sheet);
		Cell focusCell = null;
		try {
			focusCell = sheet.getRow(rowIdx).getCell(colIdx);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(
				"The data inside the ExcelDataReadMetaInfo is invalid or the necessary data does not exist.");
		}
		if (focusCell == null) {
			for (int i = 0; i < excelColumList.size(); i++) {
				ExcelColum excelColum = excelColumList.get(i);
				try {
					if (excelColum.isRequired() && headRow.getCell(colIdx).getStringCellValue().equals(excelColum.name())) {
						return true;
					}
				} catch (NullPointerException e) {
					return true;
				}
			}
		}
		return false;
	}
}
