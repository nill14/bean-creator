package com.github.nill14.beancreator.builder

import com.github.nill14.beancreator.model.ITypedElement
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.model.IField

trait IFieldChunkBuilder {
	def build(w: IndentWriter, bean: IBean, field: IField, r: ImportResolver)
}