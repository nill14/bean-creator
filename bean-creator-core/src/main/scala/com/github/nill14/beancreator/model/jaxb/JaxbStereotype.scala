package com.github.nill14.beancreator.model.jaxb

import com.github.nill14.beancreator.model.jaxb.JaxbAdapter._
import com.github.nill14.beancreator.util.Binding._
import JaxbAdapter._
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.XmlAccessorType
import com.github.nill14.beancreator.model._


@XmlRootElement(name = "stereotype")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbStereotype 
(
	@xmlAttribute(name = "name", required=true) name: String,
	
	@xmlAttribute(name = "package")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) packageName: Option[String]
	
    
) extends IStereotype {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None)
	
}