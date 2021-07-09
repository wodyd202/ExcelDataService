package com.ljy.excel.mapper;

import java.util.Collection;

import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;

public interface ExcelDataMapper {
	<T> Collection<T> mapFrom(Sheet sheet, ExcelReadMetaData metaData, Class<T> classes);
}
