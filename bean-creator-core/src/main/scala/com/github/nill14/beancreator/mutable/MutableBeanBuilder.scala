package com.github.nill14.beancreator.mutable

import java.io.Writer

import com.github.nill14.beancreator.builder.IBeanBuilder
import com.github.nill14.beancreator.model._
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.util.JavaCodeWriter

class MutableBeanBuilder extends IBeanBuilder {
	
	
	def build(writer: Writer, bean: IBean) {
		val codeWriter = new JavaCodeWriter(bean, writer)
		val resolver = codeWriter.resolver
		val w = codeWriter.writer
		

		bean.comment match {
			case Some(comment) => 
				w println s"/**"
				w println s" * $comment"
				w println s" */"
			case _ =>
		}
		
		w println s"public class ${bean.name} {"
		w.println 
		w.incIndent
		
		for (field <- bean.fields) {
			(new FieldChunkBuilder).build(w, bean, field, resolver)
		}
		
		for (field <- bean.fields) {
			w.println 

			(new SetterChunkBuilder).build(w, bean, field, resolver)
			
			w.println 
			
			(new GetterChunkBuilder).build(w, bean, field, resolver)
		}		
		
		for {
			m <-bean.methods
			if "toString" equals m.name 
		} {
			(new ToStringChunkBulder).build(w, bean, m, resolver)
		}

		w.decIndent
		w.println 
		w println "}"
		
		codeWriter.complete
	}
	
}