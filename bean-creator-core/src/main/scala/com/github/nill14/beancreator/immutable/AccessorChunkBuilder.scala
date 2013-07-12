package com.github.nill14.beancreator.immutable

import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.util.Utils
import com.github.nill14.beancreator.model.IField
import java.beans.BeanDescriptor
import com.github.nill14.beancreator.builder.IFieldChunkBuilder
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.model.IBean

class AccessorChunkBuilder extends IFieldChunkBuilder {

	def build(w: IndentWriter, bean: IBean, field: IField, resolver: ImportResolver) {
		
		w.println 
		
		field.comment match {
			case Some(comment) => 
				w println s"/**"
				w println s" * $comment"
				w println s" * "
				w println s" * @return $comment"
				w println s" */"
			case None =>
		}			
		
		w prntInc s"public ${resolver ^ field} ${field.name}() {" 
		w println s"return ${field.name};"
		w decPrnt s"}"		
	}
	
}