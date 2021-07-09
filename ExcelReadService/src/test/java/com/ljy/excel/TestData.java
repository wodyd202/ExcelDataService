package com.ljy.excel;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelData;

@ExcelData
public class TestData {
	
	@ExcelColum("test_1")
	private String test_1;
	
	@ExcelColum("test_2")
	private String test_2;
}
