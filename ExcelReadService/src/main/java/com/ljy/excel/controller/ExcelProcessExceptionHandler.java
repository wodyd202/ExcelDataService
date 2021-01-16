package com.ljy.excel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcelProcessExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgumentException(IllegalArgumentException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
