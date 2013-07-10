package com.github.nill14.beancreator.trivialwriter

import java.io.PrintWriter
import scala.collection.mutable
import com.github.nill14.beancreator.model._
import javax.xml.bind.annotation.XmlAccessorOrder
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.util.Utils
import com.github.nill14.beancreator.util.JavaCodeWriter
import com.github.nill14.beancreator.util.JavaFqn
import java.io.Writer
import com.github.nill14.beancreator.builder.IBeanBuilder
import com.github.nill14.beancreator.model.jaxb.JaxbBean

class ImmutableBeanBuilder extends IBeanBuilder {
	
	
	def build(writer: Writer, bean: IBean) {
		val codeWriter = new JavaCodeWriter(bean, writer)
		val r = codeWriter.resolver
		val w = codeWriter.writer
		

		bean.comment match {
			case Some(comment) => 
				w println s"/**"
				w println s" * $comment"
				w println s" */"
			case _ =>
		}
		
		w println s"public final class ${bean.name} {"
		w.println 
		w.incIndent
		
//		for (field <- bean.fields) {
//			(new FieldChunkBuilder).build(w, bean, field, resolver)
//		}
//		
//		for (field <- bean.fields) yield {
//			w.println 
//
//			(new SetterChunkBuilder).build(w, bean, field, resolver)
//			
//			w.println 
//			
//			(new GetterChunkBuilder).build(w, bean, field, resolver)
//		}		
//		
//		for {
//			m <-bean.methods
//		if "toString" equals m.name) {
//			(new ToStringChunkBulder).build(w, bean, m, resolver)
//		}
		
		w prntInc s"public static final ${bean.name}.Builder builder() {"
		w println s"return new ${bean.name}.Builder();"
		w decPrnt "}"
		
		val langBuilder = JavaFqn.key("org.apache.commons.lang3.builder.Builder<String>");

		w prntInc s"public static class Builder implements ${r ^ langBuilder} {"
		
		
		w decPrnt "}"
		
		
		
		w.decIndent
		w.println 
		w println "}"
		
		codeWriter.complete
	}
	
}