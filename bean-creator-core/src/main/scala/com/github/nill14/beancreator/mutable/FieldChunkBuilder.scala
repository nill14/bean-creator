package com.github.nill14.beancreator.mutable

import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.model._
import com.github.nill14.beancreator.util.Utils._
import com.github.nill14.beancreator.model.jaxb.JaxbBean
import com.github.nill14.beancreator.model.jaxb.JaxbField
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.builder.IFieldChunkBuilder

class FieldChunkBuilder extends IFieldChunkBuilder {

	def build(w: IndentWriter, bean: IBean, field: IField, r: ImportResolver) {
		w.println 

		
		field.comment match {
			case Some(comment) => 
				w.println
				w println s"/**"
				w println s" * $comment"
				w println s" */"
			case _ =>
		}

		value(field) match {
			case Some(value) => { w println s"public ${r ^ field} ${field.name} = ${value};" }
			case None => { w println s"public ${r ^ field} ${field.name};" }
		}		
	}
	
}