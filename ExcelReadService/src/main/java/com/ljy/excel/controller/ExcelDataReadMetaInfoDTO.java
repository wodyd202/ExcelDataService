package com.ljy.excel.controller;

public class ExcelDataReadMetaInfoDTO {
	private int startDataRow;
	private int endDataRow;
	private int startDataCol;
	private int endDataCol;

	public int getStartDataRow() {
		return startDataRow;
	}

	public void setStartDataRow(int startDataRow) {
		this.startDataRow = startDataRow;
	}

	public int getEndDataRow() {
		return endDataRow;
	}

	public void setEndDataRow(int endDataRow) {
		this.endDataRow = endDataRow;
	}

	public int getStartDataCol() {
		return startDataCol;
	}

	public void setStartDataCol(int startDataCol) {
		this.startDataCol = startDataCol;
	}

	public int getEndDataCol() {
		return endDataCol;
	}

	public void setEndDataCol(int endDataCol) {
		this.endDataCol = endDataCol;
	}

}
