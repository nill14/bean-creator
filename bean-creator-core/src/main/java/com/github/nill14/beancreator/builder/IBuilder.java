package com.github.nill14.beancreator.builder;

import com.github.nill14.beancreator.util.IndentWriter;

public interface IBuilder {
	void build(IndentWriter writer, BeanDescriptor bean);
}
