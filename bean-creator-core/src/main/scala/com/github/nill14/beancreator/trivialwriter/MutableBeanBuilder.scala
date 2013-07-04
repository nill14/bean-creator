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

class MutableBeanBuilder extends IBuilder {
	
	
	def build(writer: IndentWriter, bean: BeanDescriptor) {
		writer println "/* DO NOT MODIFY THIS FILE!"
		writer println "The content is generated. Your changes may be lost. */"
		writer println 
		
		bean.packageName match {
			case Some(x) => writer println s"package ${x};"
			case _ =>
		}
		
		for (imp <- collectImports(bean)) {
			writer println s"import ${imp};"
		}

		bean.comment match {
			case Some(comment) => 
				writer println s"/**"
				writer println s" * $comment"
				writer println s" */"
			case _ =>
		}
		
		writer println s"public class ${bean.name} {"
		writer println ""
		
		for (field <- bean.fields) {
			
			field.comment match {
				case Some(comment) => 
					writer println ""
					writer println s"	/**"
					writer println s"	 * $comment"
					writer println s"	 */"
				case _ =>
			}

			field.value match {
				case Nil => { writer println s"	public ${typer(field)} ${field.name} = ${field.defaultValue};" }
				case _ => 	 { writer println s"	public ${typer(field)} ${field.name};" }
			}
			
		}
		
		for (field <- bean.fields) {
			writer println ""

			field.comment match {
				case Some(comment) => 
					writer println s"	/**"
					writer println s"	 * $comment"
					writer println s"	 * "
					writer println s"	 * @param ${field.name} $comment"
					writer println s"	 */"
				case None =>
			}

			writer println s"	public void ${setter(field)}(${typer(field)} ${field.name}) {" 
			writer println s"		this.${field.name} = ${field.name};"
			writer println s"	}"
			
			writer println ""
			
			field.comment match {
				case Some(comment) => 
					writer println s"	/**"
					writer println s"	 * $comment"
					writer println s"	 * "
					writer println s"	 * @return $comment"
					writer println s"	 */"
				case None =>
			}			
			
			writer println s"	public ${typer(field)} ${getter(field)}() {" 
			writer println s"		return ${field.name};"
			writer println s"	}"
		}		
		
		writer println ""
		writer println s"}"
		
	}

	def setter(field: FieldDescriptor) = capName("set", field.name)
	
	def getter(field: FieldDescriptor) = {
		if (classOf[Boolean] == field.clazz)  
			capName("is", field.name)
		else capName("get", field.name)
	}
	
	def capName(prefix: String, name: String) = {
		val b = new StringBuilder
		b append prefix
		b append name.charAt(0).toUpper
		if (name.length > 1) b append name.substring(1)
		b.toString
	}
	
	def typer(field: FieldDescriptor) = field.clazz.getSimpleName
	
	def importer(field: FieldDescriptor) = ""
		
	def collectImports(bean: BeanDescriptor) = {
		val excluded = 
			({if (bean.packageName != null) bean.packageName else Nil } :: List("java.lang", "java.util")).toSet 
			
		val imports = new mutable.HashSet[String]
		val simpleNames = new mutable.HashSet[String]
//		val typeResolver = new mutable.HashMap[Class[Any], String]
		
		val types = (bean.fields filter {_.clazz.getPackage != null} map {_.clazz}).toSet
		
		for {t <- types
			packageName = t.getPackage.getName
			fullName = t.getName
			simpleName = t.getSimpleName			
		} {
//			if (simpleNames contains simpleName) {
//				//avoid name clash
//				typeResolver += (t, fullName)
//			}
//			else {
				if (!excluded.contains(packageName)) {
					imports += fullName
					simpleNames += simpleName
//					typeResolver += (t, fullName)
				}
//				else {
//					typeResolver += (t, fullName)
//				}
//			}
		}
		
		imports
	}
	
	
	
}