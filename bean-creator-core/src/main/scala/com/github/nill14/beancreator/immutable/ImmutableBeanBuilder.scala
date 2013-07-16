package com.github.nill14.beancreator.immutable

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
import com.github.nill14.beancreator.mutable.FieldChunkBuilder
import com.github.nill14.beancreator.model.jaxb.JaxbBean
import com.github.nill14.beancreator.model.jaxb.JaxbBean
import com.github.nill14.beancreator.builder.IBuilderContext

class ImmutableBeanBuilder extends IBeanBuilder {
	
	
	def build(writer: Writer, bean: IBean, ctx: IBuilderContext) {
		val codeWriter = new JavaCodeWriter(writer, bean, ctx)
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
		w.incIndent
		
		for (field <- bean.fields) {
			(new FieldChunkBuilder("private final", false)).build(w, bean, field, r)
		}

		w.println
		w prntInc s"private ${bean.name}(Builder builder) {"
		
		for (field <- bean.fields) {
			//todo check immutability for each field
			w println s"this.${field.name} = builder.${field.name};"
		}
		
		for (field <- bean.fields) {
			//todo check validity for each field
//			w println s"this.${field.name} = builder.${field.name};"
		}
		
		w decPrnt "}"
		
		
		for (field <- bean.fields) yield {
			(new AccessorChunkBuilder).build(w, bean, field, r)
		}		
		
//		for {
//			m <-bean.methods
//		if "toString" equals m.name) {
//			(new ToStringChunkBulder).build(w, bean, m, resolver)
//		}
		
		r bookOut("Builder")
		w.println
		w prntInc s"public final Builder builder() {"
		w println s"return new Builder(this);"
		w decPrnt "}"
		
		val langBuilder = JavaFqn.key(s"org.apache.commons.lang3.builder.Builder<${bean.name}>");

		w.println
		w prntInc s"public static class Builder implements ${r ^ langBuilder} {"
		
		val b = bean.asInstanceOf[JaxbBean]
		val builderBean = b.copy(name = "Builder")
		
		for (field <- bean.fields) {
			(new FieldChunkBuilder("protected")).build(w, builderBean, field, r)
		}
		
		w.println
		w prntInc s"public Builder() {"
		w decPrnt "}"
		
		val bvar = Utils.varName(bean)
		w.println
		w prntInc s"private Builder(${bean.name} ${bvar}) {"
		for (field <- bean.fields) {
			//in case of collection appenders check mutability of a collection
			w println s"this.${field.name} = ${bvar}.${field.name};"
		}
		
		w decPrnt "}"
		
		for (field <- bean.fields) yield {
			(new MutatorChunkBuilder).build(w, builderBean, field, r)
		}	
		
		w.println
		w println "@Override"
		w prntInc s"public ${bean.name} build() {"
		w println s"return new ${bean.name}(this);"
		w decPrnt "}"
		
		w decPrnt "}"
		
		
		
		w.decIndent
		w.println 
		w println "}"
		
		codeWriter.complete
	}
	
}