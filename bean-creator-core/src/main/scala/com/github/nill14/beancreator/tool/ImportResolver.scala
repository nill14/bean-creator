package com.github.nill14.beancreator.tool

import scala.collection.Seq
import scala.collection.mutable
import scala.reflect.ClassTag

import com.github.nill14.beancreator.model.ITypedElement
import com.github.nill14.beancreator.util.JavaFqn

class ImportResolver(packageName: Option[String]) {

	val excludedImports = 
			(packageName.getOrElse(Nil) :: List("java.lang", "java.util")).toSet 
	
	val imports = new mutable.HashMap[String, String]
	val simpleNames = new mutable.HashSet[String]
	
	def ^ (fqn: JavaFqn): String = resolve(fqn)
	
	def ^ (elem: ITypedElement): String = elem.classType match {
		case None => ^[String]
		case Some(fqn) => resolve(JavaFqn.key(fqn))
	}
	
	def ^ [T](implicit tag : ClassTag[T]): String = resolve(JavaFqn.key(tag.runtimeClass.getName))
	
	
	def resolve(fqn: JavaFqn): String = {
		
		val simpleName = fqn.importName match {
			case None => fqn.inputName
			case Some(x) => imports.get(x) match {
				case Some(resolvedType) => resolvedType
				case None => resolveSimpleType(fqn)
			}
		}
		
		fqn.parametrizations match {
			case Nil => simpleName
			case xs: Seq[JavaFqn] => s"${simpleName}${xs.map(resolve(_)).mkString("<", ", ", ">")}"
		}
	}		
			
	/**
	 * Ignore parametrizations
	 */
	private def resolveSimpleType(fqn: JavaFqn): String = {
		
		if (simpleNames contains fqn.simpleName) {
			fqn.inputName
		}
		else {
			simpleNames += fqn.simpleName
			imports.put(fqn.importName.get, fqn.simpleName)
			fqn.simpleName 
		}
	}		
	
		
	def collectImports: Seq[String] = imports.keySet.toArray.sorted
}