package com.github.nill14.beancreator.immutable

import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.model.IField
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.builder.IFieldChunkBuilder


class MutatorChunkBuilder extends IFieldChunkBuilder {

	def build(w: IndentWriter, bean: IBean, field: IField, resolver: ImportResolver) {
		
		w.println 

		field.comment match {
			case Some(comment) => 
				w println s"/**"
				w println s" * $comment"
				w println s" * "
				w println s" * @param ${field.name} $comment"
				w println s" */"
			case None =>
		}

		w prntInc s"public ${bean.name} ${field.name}(${resolver ^ field} ${field.name}) {" 
		w println s"this.${field.name} = ${field.name};"
		w println s"return this;"
		w decPrnt s"}"
	}
	
}