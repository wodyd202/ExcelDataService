package com.ljy.excel.util;

import java.util.ArrayList;
import java.util.Collection;

import com.ljy.excel.domain.TestMenu;
import com.ljy.excel.service.AbstractExcelDataReader;

public class TestListExcelDataReader extends AbstractExcelDataReader<TestMenu> {

	@Override
	protected Collection<TestMenu> newCollectionInstance() {
		return new ArrayList<>();
	}

	@Override
	protected void afterAddIntoCollection(TestMenu obj) {
		super.afterAddIntoCollection(obj);
	}
}
