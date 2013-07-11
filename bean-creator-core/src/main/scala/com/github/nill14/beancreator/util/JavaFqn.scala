package com.github.nill14.beancreator.util

import java.util.regex.Pattern

case class JavaFqn(
		val inputName: String,			
		val packageName: Option[String],
		val simpleName: String,
		val parametrizations: Seq[JavaFqn]) {
	
	/**
	 * Parameter-less fully classified name 
	 * or None if there is nothing to import
	 */
	lazy val importName: Option[String] = packageName match {
		case Some(prefix) => Some(s"$prefix.$simpleName")
		case None => None
	}

}

object JavaFqn {
	
	def key(fqn: String): JavaFqn = {
		
		val dot = fqn.lastIndexOf(".")
		if (dot == fqn.length) throw new Error(s"""Parse error: "$fqn"""")
		
		val packageName = dot match {
			case -1 => None
			case i => Some(fqn.substring(0, i))
		}
		
		val name = dot match {
			case -1 => fqn
			case i => fqn.substring(i+1, fqn.length)
		}
		
		if (name.endsWith(">")) {
			val par = name.indexOf("<")
			val len = name.length
				
			if (par < 0) throw new Error(s"""Parse error: "$fqn"""")
				
			val simpleName = name.substring(0, par)
			val params = name.substring(par+1, name.length-1).split(",")
				
			val parametrizations = params.map(key(_))
				
			new JavaFqn(fqn, packageName, simpleName, parametrizations)
		}
		else {
			new JavaFqn(fqn, packageName, name, Nil)
		}
	}
}