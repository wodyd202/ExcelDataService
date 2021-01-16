package com.ljy.excel.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public class DefaultExcelHeaderValidator implements ExcelHeaderValidator {

	private CellMargeChecker cellMargeCheck = new CellMargeChecker();
	private ExcelObjectChecker excelObjectChecker = new DefaultExcelObjectChecker();

	@Override
	public void valid(Class<?> excelBindClassType, Sheet sheet, ExcelDataReadMetaInfo metaInfo) {
		Set<String> excelHeader = new HashSet<>();
		int excelHeaderCheckCount = 0;
		for (int i = metaInfo.getStartDataColIdx(); i < metaInfo.getEndDataColIdx(); i++) {
			Row headerRow = metaInfo.getHeadRow(sheet);
			Cell focusCell = headerRow.getCell(i);
			cellTypeCheck(sheet, headerRow.getRowNum(), i);
			excelHeader.add(focusCell.getStringCellValue());
			excelHeaderCheckCount++;
			if (checkDuplicateExcelColums(excelHeaderCheckCount, excelHeader)) {
				throw new IllegalArgumentException("There is a duplicate excel header");
			}
		}
		List<String> excelColumList = excelObjectChecker.getExcelColums(excelBindClassType).stream()
			.map(excelColum -> excelColum.name()).collect(Collectors.toList());
		excelHeader.forEach(fileIntoExcelColum -> {
			if (!excelColumList.contains(fileIntoExcelColum)) {
				throw new IllegalArgumentException("[" + fileIntoExcelColum + "] Excel column does not exist");
			}
		});
	}

	private boolean checkDuplicateExcelColums(int excelHeaderCheckCount, Set<String> excelHeader) {
		return excelHeaderCheckCount != excelHeader.size();
	}

	@SuppressWarnings("deprecation")
	private void cellTypeCheck(Sheet sheet, int rowIdx, int colIdx) {
		Cell focusCell = sheet.getRow(rowIdx).getCell(colIdx);
		if (cellMargeCheck.isMerged(sheet, rowIdx, colIdx)) {
			throw new IllegalArgumentException("Wrong excel header. please check excel header again");
		}
		if (focusCell == null) {
			throw new IllegalArgumentException("[" + colIdx + "] Header columns do not allow null values");
		}
		if (focusCell.getCellTypeEnum() == CellType.BLANK) {
			throw new IllegalArgumentException("Header column must not be null");
		}
		if (focusCell.getCellTypeEnum() != CellType.STRING) {
			throw new IllegalArgumentException("Header column must be of String type");
		}
	}
}
