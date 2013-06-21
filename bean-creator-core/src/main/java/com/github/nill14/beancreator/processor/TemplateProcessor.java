package com.github.nill14.beancreator.processor;

import java.lang.reflect.Field;

public class TemplateProcessor {

	public void readTemplateBean(Class<?> templateClazz) {
		for (Field f : templateClazz.getFields()) {
			f.getName();
		}
	}
	
}
