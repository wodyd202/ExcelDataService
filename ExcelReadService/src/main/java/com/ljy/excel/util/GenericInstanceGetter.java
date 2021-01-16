package com.ljy.excel.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericInstanceGetter {

	public Object newGenericInstance(Class<?> classType) {
		try {
			return getGenericInstanceType(classType).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Class<?> getGenericInstanceType(Class<?> target) {
		Class<?> classType = null;
		Type type = target.getGenericSuperclass();
		type = ((ParameterizedType) type).getActualTypeArguments()[0];
		try {
			classType = Class.forName(type.getTypeName());
		} catch (ClassNotFoundException e) {
		}
		return classType;
	}

	public Field[] getGenericInstanceFields(Class<?> target) {
		return getGenericInstanceType(target).getDeclaredFields();
	}
}
