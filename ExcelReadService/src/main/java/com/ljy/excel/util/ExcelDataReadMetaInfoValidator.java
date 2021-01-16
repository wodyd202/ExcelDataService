package com.ljy.excel.util;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;

public class ExcelDataReadMetaInfoValidator {
	public void checkMetaInfo(ExcelDataReadMetaInfo metaInfo) {
		if (isEndDataColIdxLessThenEqualsStartDataColIdx(metaInfo)) {
			throw new IllegalArgumentException("read start column must be larger than the read end column.");
		}
		if (isStartDataRowIdxLessThenEqualsEndDataRowIdx(metaInfo)) {
			throw new IllegalArgumentException(
				"read start row must be larger than the read end row And start row must be Greater than Zero");
		}
	}

	private boolean isEndDataColIdxLessThenEqualsStartDataColIdx(ExcelDataReadMetaInfo metaInfo) {
		return metaInfo.getStartDataColIdx() < 0 || metaInfo.getStartDataColIdx() >= metaInfo.getEndDataColIdx();
	}

	private boolean isStartDataRowIdxLessThenEqualsEndDataRowIdx(ExcelDataReadMetaInfo metaInfo) {
		return metaInfo.getStartDataRowIdx() <= 0 || metaInfo.getStartDataRowIdx() >= metaInfo.getEndDataRowIdx();
	}
}
