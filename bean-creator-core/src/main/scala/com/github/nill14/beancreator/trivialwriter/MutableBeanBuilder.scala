package com.github.nill14.beancreator.trivialwriter

import com.github.nill14.beancreator.builder.IBuilder
import com.github.nill14.beancreator.model.MutableBean
import java.io.Writer
import com.github.nill14.beancreator.builder.BeanDescriptor
import java.io.PrintWriter
import com.github.nill14.beancreator.builder.FieldDescriptor
import scala.collection.generic.MutableSetFactory
import scala.collection._

class MutableBeanBuilder extends IBuilder {
	
	
	def build(writer: PrintWriter, bean: BeanDescriptor) {
		writer println "/* DO NOT MODIFY THIS FILE!"
		writer println "The content is generated. Your changes may be lost. */"
		writer println ""
		
		if (bean.packageName != null) 
			writer println s"package ${bean.packageName};"
		
		for (imp <- collectImports(bean)) {
			writer println s"import ${imp};"
		}

		writer println s"/**"
		writer println s" * ${bean.comment}"
		writer println s" */"
		writer println s"public class ${bean.name} {"
		
		
		for (field <- bean.fields) {
			writer println ""
			writer println s"	/**"
			writer println s"	 * ${field.comment}"
			writer println s"	 */"
			field.defaultValue match {
				case Nil => { writer println s"	public ${typer(field)} ${field.name} = ${field.defaultValue};" }
				case _ => 	 { writer println s"	public ${typer(field)} ${field.name};" }
			}
			
		}
		
		for (field <- bean.fields) {
			writer println ""
			writer println s"	/**"
			writer println s"	 * ${field.comment}"
			writer println s"	 * "
			writer println s"	 * @param ${field.name} ${field.comment}"
			writer println s"	 */"
			writer println s"	public void ${setter(field)}(${typer(field)} ${field.name}) {" 
			writer println s"		this.${field.name} = ${field.name};"
			writer println s"	}"
			
			writer println ""
			writer println s"	/**"
			writer println s"	 * ${field.comment}"
			writer println s"	 * "
			writer println s"	 * @return ${field.comment}"
			writer println s"	 */"
			writer println s"	public ${typer(field)} ${getter(field)}() {" 
			writer println s"		return ${field.name};"
			writer println s"	}"
		}		
		
		writer println s"}"
		
	}

	def setter(field: FieldDescriptor) = capName("set", field.name)
	
	def getter(field: FieldDescriptor) = {
		if (classOf[Boolean] == field.classType)  
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
	
	def typer(field: FieldDescriptor) = field.classType.getSimpleName
	
	def importer(field: FieldDescriptor) = ""
		
	def collectImports(bean: BeanDescriptor) = {
		val excluded = 
			({if (bean.packageName != null) bean.packageName else Nil } :: List("java.lang", "java.util")).toSet 
			
		val imports = new mutable.HashSet[String]
		val simpleNames = new mutable.HashSet[String]
//		val typeResolver = new mutable.HashMap[Class[Any], String]
		
		val types = (bean.fields filter {_.classType.getPackage != null} map {_.classType}).toSet
		
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