package com.ljy.excel;

import com.ljy.excel.ExcelReadMetaData.ExcelReadMetaDataBuilder;

public class Fixture {
	public static ExcelReadMetaDataBuilder aExcelReadMetaData() {
		return ExcelReadMetaData.builder()
				.headerCellStartIdx(0)
				.headerCellEndIdx(5)
				.bodyCellStartRowIdx(1)
				.bodyCellEndRowIdx(10);
	}
}
