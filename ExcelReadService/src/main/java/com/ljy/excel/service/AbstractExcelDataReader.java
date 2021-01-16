package com.ljy.excel.service;

import java.lang.reflect.Field;
import java.util.Collection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.domain.ExcelDataReadMetaInfo;
import com.ljy.excel.util.ExcelColumDataBinder;
import com.ljy.excel.util.GenericInstanceGetter;
import com.ljy.excel.util.RequiredObjectChecker;

abstract public class AbstractExcelDataReader<T> implements RequiredObjectChecker, ExcelDataReader<T> {
	abstract protected Collection<T> newCollectionInstance();

	private ExcelColumDataBinder columDataBinder = new ExcelColumDataBinder();
	private GenericInstanceGetter genericInstanceGetter = new GenericInstanceGetter();

	protected void beforeAddIntoCollection(T obj) {
	}

	protected void afterAddIntoCollection(T obj) {
	}

	@Override
	public Collection<T> readData(Sheet sheet, ExcelDataReadMetaInfo metaInfo) {
		Collection<T> collection = newCollectionInstance();
		loopToExcelDataRowsAfterAddDataIntoCollection(sheet, collection, metaInfo);
		return collection;
	}

	@SuppressWarnings("unchecked")
	private void loopToExcelDataRowsAfterAddDataIntoCollection(Sheet sheet, Collection<T> collection,
		ExcelDataReadMetaInfo metaInfo) {
		for (int i = metaInfo.getStartDataRowIdx(); i < metaInfo.getEndDataRowIdx(); i++) {
			Object excelDataToObj = genericInstanceGetter.newGenericInstance(this.getClass());
			loopToExcelDataColumsIntoFocusRow(excelDataToObj, metaInfo, sheet.getRow(i), metaInfo.getHeadRow(sheet));
			beforeAddIntoCollection((T) excelDataToObj);
			collection.add((T) excelDataToObj);
			afterAddIntoCollection((T) excelDataToObj);
		}
	}

	private void loopToExcelDataColumsIntoFocusRow(Object obj, ExcelDataReadMetaInfo metaInfo, Row row, Row firstRow) {
		for (int i = metaInfo.getStartDataColIdx(); i < metaInfo.getEndDataColIdx(); i++) {
			bindValueOfColumToObjField(row.getCell(i), obj, firstRow.getCell(i).getStringCellValue());
		}
	}

	private void bindValueOfColumToObjField(Cell focusCell, Object obj, String focusHeadColName) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (hasExcelAnnotationAndIsNameEqualsFocusHeadColName(field, focusHeadColName)) {
				checkColumAndObjField_IfEqualsThenSet(field, focusCell, obj);
			}
		}
	}

	private boolean hasExcelAnnotationAndIsNameEqualsFocusHeadColName(Field field, String focusCol) {
		ExcelColum excelColum = field.getAnnotation(ExcelColum.class);
		return excelColum != null && excelColum.name().equals(focusCol);
	}

	private void checkColumAndObjField_IfEqualsThenSet(Field field, Cell focusCell, Object obj) {
		try {
			checkExcelColTypeAndFieldTypeIfEqualsThenSet(field, focusCell, obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void checkExcelColTypeAndFieldTypeIfEqualsThenSet(Field field, Cell cell, Object obj)
		throws IllegalArgumentException, IllegalAccessException {
		field.setAccessible(true);
		try {
			columDataBinder.putDataIntoObjAccording2CellType(cell, field, obj);
		} catch (NullPointerException e) {
			ExcelColum excelColum = field.getAnnotation(ExcelColum.class);
			if (excelColum != null && excelColum.isRequired()) {
				throw new IllegalArgumentException("[" + excelColum.name() + "] value is required");
			}
		}
	}
}
