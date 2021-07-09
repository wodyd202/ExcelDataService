package com.ljy.excel.mapper;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.ExcelReadMetaData;
import com.ljy.excel.annotation.ExcelColum;

public class ListExcelDataMapper implements ExcelDataMapper {
	private ExcelColumDataBinder columDataBinder = new ExcelColumDataBinder();
	
	@Override
	public <T> Collection<T> mapFrom(Sheet sheet, ExcelReadMetaData metaData, Class<T> classes) {
		for (int i = metaData.getBodyCellStartRowIdx(); i < metaData.getBodyCellEndRowIdx(); i++) {
			setAccessibleAll(classes);
			T obj = newInstance(classes);
			for (int j = metaData.getHeaderCellStartIdx(); j < metaData.getHeaderCellEndIdx(); j++) {
				bindValueOfColumToObjField(getCell(sheet, i, j), obj, getCell(sheet, 0, j));
			}
		}
		return null;
	}

	private <T> void setAccessibleAll(Class<T> classes) {
		Stream.of(classes.getDeclaredFields())
				.map(c->{c.setAccessible(true); return c;});
	}
	
	private <T> T newInstance(Class<T> classes){
		try {
			return classes.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void bindValueOfColumToObjField(Cell focusCell, Object obj, Cell headCell) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (equalsFocusHeadColName(field, headCell)) {
				checkColumAndObjField_IfEqualsThenSet(field, focusCell, obj);
			}
		}
	}
	
	private boolean equalsFocusHeadColName(Field field, Cell headCell) {
		ExcelColum excelColum = field.getAnnotation(ExcelColum.class);
		return excelColum != null && excelColum.value().equals(headCell.getStringCellValue());
	}
	
	private void checkColumAndObjField_IfEqualsThenSet(Field field, Cell focusCell, Object obj){
		field.setAccessible(true);
		try {
			columDataBinder.putDataIntoObjAccording2CellType(focusCell, field, obj);
		} catch (NullPointerException e) {
		}catch(IllegalArgumentException e) {
		}catch(IllegalAccessException e) {
		}
	}
	
	private Cell getCell(Sheet sheet, int i, int j) {
		return sheet.getRow(i).getCell(j);
	}
}
