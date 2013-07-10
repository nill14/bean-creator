package com.github.nill14.beancreator.jaxbreader

import java.io.InputStream
import javax.xml.bind.JAXBContext
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.model.jaxb.JaxbBean

class BeanXmlReader {

	def readXml(is: InputStream): IBean = {
		val context = JAXBContext.newInstance(classOf[JaxbBean])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[JaxbBean]
	}
	
}
