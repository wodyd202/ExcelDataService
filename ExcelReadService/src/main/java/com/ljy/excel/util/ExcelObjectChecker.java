package com.ljy.excel.util;

import java.util.List;

import com.ljy.excel.annotation.ExcelColum;

public interface ExcelObjectChecker {
	public void isExcelDataObject(Class<?> classType);
	List<ExcelColum> getExcelColums(Class<?> classType);
}
