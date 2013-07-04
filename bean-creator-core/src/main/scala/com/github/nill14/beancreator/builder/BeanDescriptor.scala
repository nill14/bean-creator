package com.github.nill14.beancreator.builder

import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters.XmlAdapter
import java.util.ArrayList
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import scala.collection.JavaConversions
import com.github.nill14.beancreator.util.Binding._
import Adapter._


@XmlRootElement(name = "MutableBean")
@XmlAccessorType(XmlAccessType.FIELD)
case class BeanDescriptor (

	@xmlAttribute(name = "name", required=true) name: String,
	
	@xmlAttribute(name = "package")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) packageName: Option[String],
	
	@xmlElement
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlElement(name = "fields")
    @xmlJavaTypeAdapter(classOf[FieldListAdapter]) fields: Seq[FieldDescriptor] = Nil,
    
	@xmlElement(name = "methods")
    @xmlJavaTypeAdapter(classOf[MethodListAdapter]) methods: Seq[MethodDescriptor] = Nil) {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None, None, Nil)
	
}