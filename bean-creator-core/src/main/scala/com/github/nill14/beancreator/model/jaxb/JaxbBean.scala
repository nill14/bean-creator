package com.github.nill14.beancreator.model.jaxb

import com.github.nill14.beancreator.model.jaxb.JaxbAdapter._
import com.github.nill14.beancreator.util.Binding._
import JaxbAdapter._
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.XmlAccessorType
import com.github.nill14.beancreator.model.IBean


@XmlRootElement(name = "MutableBean")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbBean (

	@xmlAttribute(name = "name", required=true) name: String,
	
	@xmlAttribute(name = "package")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) packageName: Option[String],
	
	@xmlElement
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlElement(name = "fields")
    @xmlJavaTypeAdapter(classOf[FieldListAdapter]) fields: Seq[JaxbField] = Nil,
    
	@xmlElement(name = "methods")
    @xmlJavaTypeAdapter(classOf[MethodListAdapter]) methods: Seq[JaxbMethod] = Nil) extends IBean {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None, None, Nil)
	
}