package com.ljy.excel.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ljy.excel.annotation.ExcelColum;

public class ExcelObjectInfoGetter {

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
}
