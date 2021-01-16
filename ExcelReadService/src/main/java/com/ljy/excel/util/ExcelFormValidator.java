package com.ljy.excel.util;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public interface ExcelFormValidator {
	public void valid(Sheet sheet, ExcelDataReadMetaInfo metaInfo);
}
