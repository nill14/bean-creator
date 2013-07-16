package com.github.nill14.beancreator.model.jaxb

import com.github.nill14.beancreator.model.jaxb.JaxbAdapter._
import com.github.nill14.beancreator.util.Binding._
import JaxbAdapter._
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.XmlAccessorType
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.model.IModel


@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
case class JaxbModel 
(
		
	@xmlElement(name = "beans")
    @xmlJavaTypeAdapter(classOf[BeansAdapter]) beans: Seq[JaxbBean],
    
	@xmlElement(name = "stereotypes")
    @xmlJavaTypeAdapter(classOf[StereotypesAdapter]) stereotypes: Seq[JaxbStereotype]
    
) extends IModel {
	
	// only needed and accessed by JAXB
	private def this() = this(Nil, Nil)
	
}