package com.github.nill14.beancreator.trivialwriter

import com.github.nill14.beancreator.builder.BeanDescriptor
import com.github.nill14.beancreator.builder.IChunkBuilder
import com.github.nill14.beancreator.builder.MethodDescriptor
import com.github.nill14.beancreator.resolver.ImportResolver
import com.github.nill14.beancreator.util.IndentWriter
import javax.xml.bind.annotation.XmlAccessorOrder
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import com.github.nill14.beancreator.resolver.ImportResolver

class ToStringChunkBulder extends IChunkBuilder {


	def build(w: IndentWriter, bean: BeanDescriptor, chunk: Object, resolver: ImportResolver) {
		val m = chunk.asInstanceOf[MethodDescriptor]
		implicit val r = resolver
		
		w.println
			
		m.comment match {
			case Some(comment) => 
				w println s"/**"
				w println s" * $comment"
				w println s" */"
			case None =>
		}			
			
		w println s"@Override"
		w prntInc s"public String toString() {" 
		
		if (m.items.size == 0) {
			w println s"${r.^[ToStringBuilder]} = new ${r.^[ToStringBuilder]}(this, ${r.^[ToStringStyle]}.SHORT_PREFIX_STYLE);"
			w println "return b.build();"
		}
		else {
			w println s"${r.^[ToStringBuilder]} b = new ${r.^[ToStringBuilder]}(this, ${r.^[ToStringStyle]}.SHORT_PREFIX_STYLE);"
			
			var first = true
			for (item <- m.items) {
				if (first) {
					w println s"return b${itemStr(item)}"
					first = false
					w.incIndent
				} else {
					w println itemStr(item)
				}
			}
			
			
			w println ".build();"
			w.decIndent
		}
		
		w decPrnt s"}"
		
	}
	
	private def chunk(name: String, value: String) = s""".append("${name}", ${value})"""
	private def chunk(value: String) = s""".append(${value})"""

	private def itemStr(item: String)(implicit r: ImportResolver) = item match {
		case "@identity" => chunk(s"${r.^[Integer]}.toHexString(System.identityHashCode(this))")
		case "@fqn" => chunk("fqn", "this.getClass().getName()")
		case "@className" => chunk("this.getClass().getSimpleName()")
		case str => chunk(str, str)
	}

}