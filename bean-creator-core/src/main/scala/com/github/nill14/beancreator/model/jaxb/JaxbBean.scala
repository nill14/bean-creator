package com.github.nill14.beancreator.model.jaxb

import com.github.nill14.beancreator.model.jaxb.JaxbAdapter._
import com.github.nill14.beancreator.util.Binding._
import JaxbAdapter._
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.XmlAccessorType
import com.github.nill14.beancreator.model.IBean


@XmlRootElement(name = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbBean 
(

	@xmlAttribute(name = "name", required=true) name: String,
	
	@xmlAttribute(name = "package")
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) packageName: Option[String],
	
	@xmlElement
	@xmlJavaTypeAdapter(classOf[StringOptionAdapter]) comment: Option[String],
	
	@xmlElement(name = "fields")
    @xmlJavaTypeAdapter(classOf[FieldsAdapter]) fields: Seq[JaxbField],
    
	@xmlElement(name = "methods")
    @xmlJavaTypeAdapter(classOf[MethodsAdapter]) methods: Seq[JaxbMethod]
    
) extends IBean {
	
	// only needed and accessed by JAXB
	private def this() = this(null, None, None, Nil, Nil)
	
}