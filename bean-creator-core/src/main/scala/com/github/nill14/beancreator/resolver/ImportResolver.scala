package com.github.nill14.beancreator.resolver

import scala.collection._
import scala.collection.immutable.TreeSet
import scala.reflect.ClassTag

class ImportResolver(packageName: Option[String]) {

	val excludedImports = 
			(packageName.getOrElse(Nil) :: List("java.lang", "java.util")).toSet 
	
	val imports = new mutable.HashMap[String, String]
	val simpleNames = new mutable.HashSet[String]
	
	
	def ^ [T](implicit tag : ClassTag[T]): String = {
		resolve(tag.runtimeClass.getName)
	}	
	
	def resolve[T](implicit tag : ClassTag[T]): String = {
		resolve(tag.runtimeClass.getName)
	}		
	
	def resolve(fqn: String): String = {
		
		imports.get(fqn) match {
			case Some(resolvedType) => resolvedType
			case None => resolveType(fqn)
		}
	}		
			
	private def resolveType(fqn: String): String = {
		
		val i = fqn.lastIndexOf(".")
		
		if (i < 0 || i == fqn.length) fqn 
		else {
			val simpleName = fqn.substring(i+1)
			val packageName = fqn.substring(0, i)
			
			simpleNames += simpleName

			if (!excludedImports.contains(packageName)) {
				imports.put(fqn, simpleName)
			}
			
			simpleName
		}
	}		
	
		
	def collectImports: Seq[String] = imports.keySet.toArray.sorted
}