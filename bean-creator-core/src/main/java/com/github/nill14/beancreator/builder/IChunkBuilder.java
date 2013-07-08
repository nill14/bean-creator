package com.github.nill14.beancreator.builder;

import com.github.nill14.beancreator.resolver.ImportResolver;
import com.github.nill14.beancreator.util.IndentWriter;

public interface IChunkBuilder {
	void build(IndentWriter writer, BeanDescriptor bean, Object chunk, ImportResolver resolver);
}
