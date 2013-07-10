package com.github.nill14.beancreator.builder

import com.github.nill14.beancreator.model.ITypedElement
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.model.IMethod

trait IMethodChunkBuilder {
	def build(w: IndentWriter, bean: IBean, m: IMethod, r: ImportResolver)
}