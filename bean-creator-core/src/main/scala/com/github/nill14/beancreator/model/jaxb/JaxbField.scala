package com.github.nill14.beancreator.model.jaxb

import scala.annotation.target.field
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._
import com.github.nill14.beancreator.util.Binding._
import com.github.nill14.beancreator.model.IField


@XmlType(propOrder = Array( "name", "classType", "comment", "defaultValue"))
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbField (
	
	@xmlAttribute(required=true) name: String,
	
	@xmlAttribute(name="type")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) classType: Option[String],

	@xmlAttribute
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlAttribute(name="value")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) defaultValue: Option[String]) extends IField {
	
	// only needed and accessed by JAXB
	private def this() = this(null, null, None, None)

}