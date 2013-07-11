package com.github.nill14.beancreator.mutable

import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.model._
import com.github.nill14.beancreator.util.Utils._
import com.github.nill14.beancreator.model.jaxb.JaxbBean
import com.github.nill14.beancreator.model.jaxb.JaxbField
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.builder.IFieldChunkBuilder

class FieldChunkBuilder(modifiers: Option[String]) extends IFieldChunkBuilder {

	def this(str: String) = this(Some(str.trim))
	def this() = this("public")
	
	
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
		
		val prefix = modifiers match {
			case Some(x) => w print s"$x "
			case None => 
		}

		w print s"${r ^ field} ${field.name}"
		
		value(field) match {
			case Some(value) => { w println s" = ${value};" }
			case None 		 => { w println ";" }
		}		
	}
	
}