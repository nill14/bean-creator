package com.github.nill14.beancreator.trivialwriter

import java.io.PrintWriter
import scala.collection.mutable
import com.github.nill14.beancreator.builder.BeanDescriptor
import com.github.nill14.beancreator.builder.FieldDescriptor
import com.github.nill14.beancreator.builder.IBuilder
import javax.xml.bind.annotation.XmlAccessorOrder
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.util.Utils
import com.github.nill14.beancreator.resolver.ImportResolver
import com.github.nill14.beancreator.util.JavaCodeWriter

class MutableBeanBuilder extends IBuilder {
	
	
	def build(writer: IndentWriter, bean: BeanDescriptor) {
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