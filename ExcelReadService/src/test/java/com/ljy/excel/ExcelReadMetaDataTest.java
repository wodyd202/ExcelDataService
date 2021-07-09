package com.ljy.excel;

import static com.ljy.excel.Fixture.aExcelReadMetaData;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ljy.excel.exception.InvalidExcelReadMetaDataException;

public class ExcelReadMetaDataTest {
	
	@Test
	void invalidBodyCellIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.bodyCellStartRowIdx(10)
									.bodyCellEndRowIdx(5)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}
	
	@Test
	void invalidBodyCellEndIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.bodyCellEndRowIdx(-1)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}

	@Test
	void invalidBodyCellStartIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.bodyCellStartRowIdx(-1)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}
	
	@Test
	void invalidHeaderCellIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.headerCellStartIdx(10)
									.headerCellEndIdx(5)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}
	
	@Test
	void invalidHeaderCellEndIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.headerCellEndIdx(-1)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}
	
	@Test
	void invalidHeaderCellStartIdx() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
									.headerCellStartIdx(-1)
									.build();
		assertThrows(InvalidExcelReadMetaDataException.class, ()->{
			metaData.validation();
		});
	}
	
	@Test
	void success() {
		ExcelReadMetaData metaData = aExcelReadMetaData()
							.headerCellStartIdx(0)
							.headerCellEndIdx(5)
							.bodyCellStartRowIdx(1)
							.bodyCellEndRowIdx(10)
							.build();
		assertDoesNotThrow(()->{
			metaData.validation();
		});
	}
}
