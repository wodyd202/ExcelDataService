package com.ljy.excel;

import java.io.IOException;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ljy.excel.mapper.ExcelDataMapper;
import com.ljy.excel.validator.ExcelDataValidator;
import com.ljy.excel.validator.ExcelFileValidator;
import com.ljy.excel.validator.ExcelFormValidator;
import com.ljy.excel.validator.SimpleExcelFileValidator.ExcelExtention;

public class ExcelDataReader {
	private ExcelReadMetaData metaData;
	private ExcelFileValidator excelFilevalidator;
	private ExcelDataValidator excelDataValidator;
	private ExcelFormValidator excelFormValdiator;
	private ExcelDataMapper mapper;
	
	public ExcelDataReader(ExcelReadMetaData metaData) {
		this.metaData = metaData;
	}

	public <T> Collection<T> read(final MultipartFile excelFile, final Class<T> classes) {
		metaData.validation();
		excelDataValidator.validation(classes);
		ExcelExtention excelExtention = excelFilevalidator.validationAfterGetOrElseThrow(excelFile);
		Workbook wb = getWorkBook(excelFile, excelExtention);
		Sheet sheet = wb.getSheetAt(0);
		excelFormValdiator.validate(sheet, metaData, classes);
		Collection<T> map = mapper.mapFrom(sheet, metaData, classes);
		closeWorkBook(wb);
		return map;
	}

	public Workbook getWorkBook(MultipartFile excelFile, ExcelExtention excelExtention) {
		try {
			Workbook wb = null;
			if(excelExtention.equals(ExcelExtention.XLSX)) {
				OPCPackage opcPackage = OPCPackage.open(excelFile.getResource().getInputStream());
				wb = new XSSFWorkbook(opcPackage);
			}else {
				POIFSFileSystem fs = new POIFSFileSystem(excelFile.getResource().getInputStream());
				wb = new HSSFWorkbook(fs);
			}
			return wb;
		}catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	private void closeWorkBook(Workbook wb) {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
