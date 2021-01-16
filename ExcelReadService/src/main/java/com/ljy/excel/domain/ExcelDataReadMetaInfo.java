package com.ljy.excel.domain;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelDataReadMetaInfo {
	private int startDataRow;
	private int endDataRow;
	private int startDataCol;
	private int endDataCol;

	public int getStartDataRowIdx() {
		return startDataRow;
	}

	public int getEndDataRowIdx() {
		return endDataRow;
	}

	public Row getHeadRow(Sheet sheet) {
		return sheet.getRow(startDataRow - 1);
	}

	public Row getStartRow(Sheet sheet) {
		return sheet.getRow(startDataRow);
	}

	public Row getEndRow(Sheet sheet) {
		return sheet.getRow(endDataRow);
	}

	public int getStartDataColIdx() {
		return startDataCol;
	}

	public int getEndDataColIdx() {
		return endDataCol;
	}

	public ExcelDataReadMetaInfo(int startRow, int endRow, int startCol, int endCol) {
		this.startDataRow = startRow;
		this.endDataRow = endRow;
		this.startDataCol = startCol;
		this.endDataCol = endCol;
	}

}
