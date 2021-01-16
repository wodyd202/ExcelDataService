package com.ljy.excel.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelDataObject;

public class DefaultExcelObjectChecker implements ExcelObjectChecker {

	@Override
	public void isExcelDataObject(Class<?> classType) {
		ExcelDataObject checkAnnotation = classType.getAnnotation(ExcelDataObject.class);
		if (checkAnnotation == null) {
			throw new IllegalArgumentException("[" + classType.getName() + "] is not excel data object.");
		}
		checkDuplicateAnnotationName(classType);
	}

	@Override
	public List<ExcelColum> getExcelColums(Class<?> classType) {
		ArrayList<ExcelColum> excelColumList = new ArrayList<>();
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			ExcelColum excelColum = field.getAnnotation(ExcelColum.class);
			if (excelColum != null) {
				excelColumList.add(excelColum);
			}
		}
		return excelColumList;
	}

	private void checkDuplicateAnnotationName(Class<?> classType) {
		List<ExcelColum> excelColumList = getExcelColums(classType);
		Set<String> dupplicationCheckSet = excelColumList.stream().map(c -> c.name()).collect(Collectors.toSet());
		if (excelColumList.size() == 0) {
			throw new IllegalArgumentException("At least one excelColum is required");
		}
		if (excelColumList.size() != dupplicationCheckSet.size()) {
			throw new IllegalArgumentException("[" + classType.getName() + "] Duplicate values ​​exist.");
		}
	}

}
