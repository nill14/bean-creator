package com.github.nill14.beancreator.builder

import scala.annotation.target.field
 
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._
import com.github.nill14.beancreator.util.Binding._

class StringOptionAdapter extends OptionAdapter[String](null, "")

class OptionAdapter[A](nones: A*) extends XmlAdapter[A, Option[A]] {
	def marshal(v: Option[A]): A = v.getOrElse(nones(0))
	def unmarshal(v: A) = if (nones contains v) None else Some(v)
}


@XmlType(propOrder = Array( "name", "fieldType", "comment" ))
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
case class FieldDescriptor(
	
	@xmlAttribute(required=true) 	
	name: String,
	
	@xmlElement
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter])
	comment: Option[String],
	
	@xmlAttribute(name="type") 
	fieldType: String,
	
	@XmlValue
	defaultValue: String) {
	
	// only needed and accessed by JAXB
	private def this() = this("", null, "int", "0")

	
	def clazz: Class[_] = fieldType match {
		case "int" => classOf[Int]
		case "double" => classOf[Double]
		case className => Class.forName(className)
	}
	
	def value: Any = null
	
}