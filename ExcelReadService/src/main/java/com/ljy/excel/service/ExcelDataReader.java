package com.ljy.excel.service;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public interface ExcelDataReader<T> {
	Collection<T> readData(Sheet sheet, ExcelDataReadMetaInfo metaInfo);
}
