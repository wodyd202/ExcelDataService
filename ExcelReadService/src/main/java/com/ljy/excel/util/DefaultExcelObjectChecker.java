package com.ljy.excel.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelDataObject;

public class DefaultExcelObjectChecker implements ExcelObjectChecker {

	private ExcelObjectInfoGetter excelObjGetter = new ExcelObjectInfoGetter();

	@Override
	public void isExcelDataObject(Class<?> classType) {
		ExcelDataObject checkAnnotation = classType.getAnnotation(ExcelDataObject.class);
		if (checkAnnotation == null) {
			throw new IllegalArgumentException("[" + classType.getName() + "] is not excel data object.");
		}
		checkDuplicateExcelColum(classType);
	}

	private void checkDuplicateExcelColum(Class<?> classType) {
		List<ExcelColum> excelColumList = excelObjGetter.getExcelColums(classType);
		Set<String> dupplicationCheckSet = excelColumList.stream().map(c -> c.name()).collect(Collectors.toSet());
		if (excelColumList.size() == 0) {
			throw new IllegalArgumentException("At least one excelColum is required");
		}
		if (excelColumList.size() != dupplicationCheckSet.size()) {
			throw new IllegalArgumentException("[" + classType.getName() + "] Duplicate values ​​exist.");
		}
	}

}
