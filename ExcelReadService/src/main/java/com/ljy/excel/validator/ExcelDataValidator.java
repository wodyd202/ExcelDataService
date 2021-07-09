package com.ljy.excel.validator;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ljy.excel.annotation.ExcelColum;
import com.ljy.excel.annotation.ExcelData;
import com.ljy.excel.exception.DupExcelColumException;
import com.ljy.excel.exception.NotExcelDataException;

public class ExcelDataValidator {
	public void validation(Class<?> classes) {
		ExcelData excelDataAnnotation = classes.getAnnotation(ExcelData.class);
		if(isNotExcelData(excelDataAnnotation)) {
			throw new NotExcelDataException();
		}
		
		Field[] declaredFields = classes.getDeclaredFields();
		if(isEmptyFields(declaredFields)) {
			throw new NotExcelDataException();
		}
		
		Set<String> excelColumAnnotations = Stream.of(declaredFields)
			  .map(c->c.getAnnotation(ExcelColum.class).value())
			  .collect(Collectors.toSet());
		
		if(existDupExcelColum(declaredFields, excelColumAnnotations)) {
			throw new DupExcelColumException();
		}
	}

	private boolean existDupExcelColum(Field[] declaredFields, Set<String> excelColumAnnotations) {
		return declaredFields.length != excelColumAnnotations.size();
	}

	private boolean isNotExcelData(ExcelData excelDataAnnotation) {
		return Objects.isNull(excelDataAnnotation);
	}
	
	private boolean isEmptyFields(Field[] declaredFields) {
		return declaredFields.length == 0;
	}
	
}
