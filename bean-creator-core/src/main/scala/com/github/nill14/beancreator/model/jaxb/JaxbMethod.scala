package com.github.nill14.beancreator.model.jaxb

import scala.annotation.target.field
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._
import com.github.nill14.beancreator.util.Binding._
import com.github.nill14.beancreator.model.IMethod


@XmlType(propOrder = Array( "name", "members", "classType", "comment"))
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "method")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbMethod (
	
	@xmlAttribute(required=true) name: String,
	
	@xmlAttribute(name="type") 
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) classType: Option[String],

	@xmlAttribute
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlAttribute(name="members")
	@xmlJavaTypeAdapter(classOf[CommaSeparatedAdapter]) members: Seq[String] = Nil) extends IMethod {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None, None)

	
//	def clazz: Class[_] = methodType match {
//		case "int" => classOf[Int]
//		case "double" => classOf[Double]
//		case className => Class.forName(className)
//	}
	
	
}