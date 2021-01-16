package com.ljy.excel.domain;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class FileInfo {
	private Resource resource;

	public String getFileName() {
		String originFileName = resource.getFilename();
		int fileExtentionDivideIndex = originFileName.indexOf('.');
		return originFileName.substring(0, fileExtentionDivideIndex);
	}

	public String getFileExtention() {
		String originFileName = resource.getFilename();
		int fileExtentionDivideIndex = originFileName.indexOf('.');
		return originFileName.substring(fileExtentionDivideIndex + 1, originFileName.length());
	}

	public Resource getResource() {
		return resource;
	}

	public FileInfo(MultipartFile file) {
		if (file == null) {
			throw new IllegalArgumentException("file not be null");
		}
		this.resource = file.getResource();
	}

	public FileInfo(Resource resource) {
		this.resource = resource;
	}

}
