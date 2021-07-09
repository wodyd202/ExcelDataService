package com.ljy.excel.mapper;

import org.apache.poi.ss.usermodel.Cell;

public interface RowMapper<T> {
	T map(Cell cell, int idx);
}
