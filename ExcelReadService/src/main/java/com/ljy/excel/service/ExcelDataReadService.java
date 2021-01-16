package com.ljy.excel.service;

import java.io.IOException;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ljy.excel.domain.ExcelDataReadMetaInfo;
import com.ljy.excel.domain.FileInfo;
import com.ljy.excel.util.DefaultExcelObjectChecker;
import com.ljy.excel.util.ExcelDataReadMetaInfoValidator;
import com.ljy.excel.util.ExcelFormValidator;
import com.ljy.excel.util.ExcelObjectChecker;
import com.ljy.excel.util.GenericInstanceGetter;
import com.ljy.excel.util.RequiredObjectChecker;

public class ExcelDataReadService implements RequiredObjectChecker {
	private ExcelDataReader<?> excelDataReader;
	private ExcelFormValidator excelFormValidator;
	private ExcelObjectChecker excelObjectChecker = new DefaultExcelObjectChecker();
	private GenericInstanceGetter genericInstanceGetter = new GenericInstanceGetter();
	private ExcelDataReadMetaInfoValidator excelDataReadMetaInfoValidator = new ExcelDataReadMetaInfoValidator();

	@SuppressWarnings("unchecked")
	public <T> Collection<T> readData(FileInfo fileInfo, ExcelDataReadMetaInfo metaInfo)
		throws IOException, InvalidFormatException {
		ExcelFileReader excelFileReader = new ExcelFileReader(fileInfo);
		checkIsNullObjects(excelDataReader, excelFormValidator);
		Workbook wb = excelFileReader.getWorkBook();
		Sheet sheet = excelFileReader.getWorkSheet(wb, 0);
		excelDataReadMetaInfoValidator.checkMetaInfo(metaInfo);
		excelObjectChecker.isExcelDataObject(genericInstanceGetter.getGenericInstanceType(excelDataReader.getClass()));
		excelFormValidator.valid(sheet, metaInfo);
		Collection<?> resultList = excelDataReader.readData(sheet, metaInfo);
		wb.close();
		return (Collection<T>) resultList;
	}

	public void setExcelDataReader(ExcelDataReader<?> excelDataReader) {
		this.excelDataReader = excelDataReader;
	}

	public void setExcelFormValidator(ExcelFormValidator excelFormValidator) {
		this.excelFormValidator = excelFormValidator;
	}

	public ExcelDataReadService(ExcelDataReader<?> excelDataReader, ExcelFormValidator excelFormValidator) {
		this.excelDataReader = excelDataReader;
		this.excelFormValidator = excelFormValidator;
	}

}
