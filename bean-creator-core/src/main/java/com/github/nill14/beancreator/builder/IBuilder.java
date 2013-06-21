package com.github.nill14.beancreator.builder;

import java.io.PrintWriter;

public interface IBuilder {
	void build(PrintWriter writer, BeanDescriptor bean);
}
