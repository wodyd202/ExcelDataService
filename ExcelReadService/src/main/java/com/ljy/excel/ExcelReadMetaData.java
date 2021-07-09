package com.ljy.excel;

import com.ljy.excel.exception.InvalidExcelReadMetaDataException;

public class ExcelReadMetaData {
	private int headerCellStartIdx;
	private int headerCellEndIdx;
	private int bodyCellStartRowIdx;
	private int bodyCellEndRowIdx;

	ExcelReadMetaData(int headerCellStartIdx, int headerCellEndIdx, int bodyCellStartRowIdx, int bodyCellEndRowIdx) {
		this.headerCellStartIdx = headerCellStartIdx;
		this.headerCellEndIdx = headerCellEndIdx;
		this.bodyCellStartRowIdx = bodyCellStartRowIdx;
		this.bodyCellEndRowIdx = bodyCellEndRowIdx;
	}

	public int getHeaderCellStartIdx() {
		return headerCellStartIdx;
	}

	public int getHeaderCellEndIdx() {
		return headerCellEndIdx;
	}

	public int getBodyCellStartRowIdx() {
		return bodyCellStartRowIdx;
	}

	public int getBodyCellEndRowIdx() {
		return bodyCellEndRowIdx;
	}

	public static ExcelReadMetaDataBuilder builder() {
		return new ExcelReadMetaDataBuilder();
	}

	public void validation() {
		headerCellValidation();
		bodyCellValidation();
	}

	private void headerCellValidation() {
		if(headerCellStartIdx < 0 || headerCellEndIdx < 0) {
			throw new InvalidExcelReadMetaDataException();
		}
		if(headerCellEndIdx < headerCellStartIdx) {
			throw new InvalidExcelReadMetaDataException();
		}
	}
	
	private void bodyCellValidation() {
		if(bodyCellStartRowIdx < 0 || bodyCellEndRowIdx < 0) {
			throw new InvalidExcelReadMetaDataException();
		}
		if(bodyCellEndRowIdx < bodyCellStartRowIdx) {
			throw new InvalidExcelReadMetaDataException();
		}
	}
	
	public static class ExcelReadMetaDataBuilder {
		private int headerCellStartIdx;
		private int headerCellEndIdx;
		private int bodyCellStartRowIdx;
		private int bodyCellEndRowIdx;

		public ExcelReadMetaDataBuilder headerCellStartIdx(int headerCellStartIdx) {
			this.headerCellStartIdx = headerCellStartIdx;
			return this;
		}

		public ExcelReadMetaDataBuilder headerCellEndIdx(int headerCellEndIdx) {
			this.headerCellEndIdx = headerCellEndIdx;
			return this;
		}

		public ExcelReadMetaDataBuilder bodyCellStartRowIdx(int bodyCellStartRowIdx) {
			this.bodyCellStartRowIdx = bodyCellStartRowIdx;
			return this;
		}

		public ExcelReadMetaDataBuilder bodyCellEndRowIdx(int bodyCellEndRowIdx) {
			this.bodyCellEndRowIdx = bodyCellEndRowIdx;
			return this;
		}

		public ExcelReadMetaData build() {
			return new ExcelReadMetaData(headerCellStartIdx, headerCellEndIdx, bodyCellStartRowIdx, bodyCellEndRowIdx);
		}
	}

}
