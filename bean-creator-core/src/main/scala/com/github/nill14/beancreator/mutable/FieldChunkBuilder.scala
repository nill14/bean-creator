package com.github.nill14.beancreator.mutable

import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.model._
import com.github.nill14.beancreator.util.Utils._
import com.github.nill14.beancreator.model.jaxb.JaxbBean
import com.github.nill14.beancreator.model.jaxb.JaxbField
import com.github.nill14.beancreator.tool.ImportResolver
import com.github.nill14.beancreator.builder.IFieldChunkBuilder

class FieldChunkBuilder(modifiers: Option[String], initVal: Boolean) extends IFieldChunkBuilder {

	def this(str: String, initVal: Boolean = true) = this(Some(str.trim), initVal)
	def this() = this(Some("public"), true)
	
	
	def build(w: IndentWriter, bean: IBean, field: IField, r: ImportResolver) {

		w.println 
		
		field.comment match {
			case Some(comment) => 
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
		
		if (initVal) {
			value(field) match {
				case Some(value) => { w print s" = ${value}" }
				case None 		 => 
			}		
		}
		
		w println ";"
	}
	
}