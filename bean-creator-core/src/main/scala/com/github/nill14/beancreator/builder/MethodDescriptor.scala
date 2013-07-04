package com.github.nill14.beancreator.builder

import scala.annotation.target.field
 
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._
import com.github.nill14.beancreator.util.Binding._


@XmlType(propOrder = Array( "name", "items", "methodType", "comment"))
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "method")
@XmlAccessorType(XmlAccessType.FIELD)
case class MethodDescriptor(
	
	@xmlAttribute(required=true) name: String,
	
	@xmlAttribute(name="type") 
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) methodType: Option[String],

	@xmlAttribute
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlAttribute(name="items")
	@xmlJavaTypeAdapter(classOf[CommaSeparatedAdapter]) items: Seq[String] = Nil) {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None, None)

	
//	def clazz: Class[_] = methodType match {
//		case "int" => classOf[Int]
//		case "double" => classOf[Double]
//		case className => Class.forName(className)
//	}
	
	
}