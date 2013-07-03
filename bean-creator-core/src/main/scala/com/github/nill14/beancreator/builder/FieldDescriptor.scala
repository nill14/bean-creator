package com.github.nill14.beancreator.builder

import scala.annotation.target.field
 
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._
import com.github.nill14.beancreator.util.Binding._


@XmlType(propOrder = Array( "name", "fieldType", "comment", "defaultValue"))
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
case class FieldDescriptor(
	
	@xmlAttribute(required=true) name: String,
	
	@xmlAttribute(name="type", required=true) fieldType: String,

	@xmlAttribute
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlAttribute(name="value")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) defaultValue: Option[String]) {
	
	// only needed and accessed by JAXB
	private def this() = this(null, null, None, None)

	
	def clazz: Class[_] = fieldType match {
		case "int" => classOf[Int]
		case "double" => classOf[Double]
		case className => Class.forName(className)
	}
	
	def value: Any = null
	
}