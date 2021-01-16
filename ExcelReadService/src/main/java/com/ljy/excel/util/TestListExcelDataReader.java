package com.ljy.excel.util;

import java.util.ArrayList;
import java.util.Collection;

import com.ljy.excel.domain.ExcelDataExample;
import com.ljy.excel.service.AbstractExcelDataReader;

public class TestListExcelDataReader extends AbstractExcelDataReader<ExcelDataExample> {

	@Override
	protected Collection<ExcelDataExample> newCollectionInstance() {
		return new ArrayList<>();
	}

	@Override
	protected void afterAddIntoCollection(ExcelDataExample obj) {
		super.afterAddIntoCollection(obj);
	}
}
