package com.ljy.excel.controller;

import java.io.IOException;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ljy.excel.domain.BestGoods;
import com.ljy.excel.domain.ExcelDataReadMetaInfo;
import com.ljy.excel.domain.FileInfo;
import com.ljy.excel.service.ExcelDataReadService;

@RestController
@RequestMapping("test")
public class ExcelProcessController {

	private final ExcelDataReadService excelDataReadService;

	@PostMapping
	public ResponseEntity<Collection<BestGoods>> getExcelData(ExcelDataReadMetaInfoDTO metaInfoDTO, MultipartFile file)
		throws IOException, InvalidFormatException {
		FileInfo fileInfo = new FileInfo(file);
		ExcelDataReadMetaInfo metaInfo = new ExcelDataReadMetaInfo(
			metaInfoDTO.getStartDataRow(),
			metaInfoDTO.getEndDataRow(),
			metaInfoDTO.getStartDataCol(),
			metaInfoDTO.getEndDataCol()
		);
		Collection<BestGoods> testMenus = excelDataReadService.readData(fileInfo, metaInfo);
		return ResponseEntity.ok(testMenus);
	}

	public ExcelProcessController(ExcelDataReadService excelDataReadService) {
		this.excelDataReadService = excelDataReadService;
	}
}
