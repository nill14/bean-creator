package com.github.nill14.beancreator.builder;

import java.io.Writer;

import com.github.nill14.beancreator.model.MutableBean;

public interface IBuilder {
	void build(Writer writer, MutableBean bean);
}
