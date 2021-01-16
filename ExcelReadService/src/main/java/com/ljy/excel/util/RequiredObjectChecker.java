package com.ljy.excel.util;

public interface RequiredObjectChecker {
	default public void checkIsNullObjects(Object... objects) {
		for (Object obj : objects) {
			if (obj == null) {
				throw new IllegalArgumentException("");
			}
		}
	}
}
