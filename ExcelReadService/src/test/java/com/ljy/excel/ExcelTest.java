package com.ljy.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import com.ljy.excel.validator.SimpleExcelFileValidator.ExcelExtention;

abstract public class ExcelTest {
	protected Workbook getWorkBook(String resourceName, ExcelExtention excelExtention) {
		try {
			Workbook wb = null;
			if (excelExtention.equals(ExcelExtention.XLSX)) {
				OPCPackage opcPackage = OPCPackage.open(new ClassPathResource(resourceName).getInputStream());
				wb = new XSSFWorkbook(opcPackage);
			} else {
				POIFSFileSystem fs = new POIFSFileSystem(new ClassPathResource(resourceName).getInputStream());
				wb = new HSSFWorkbook(fs);
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
}
