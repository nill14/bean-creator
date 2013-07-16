package com.github.nill14.beancreator.jaxbreader

import java.io.InputStream
import javax.xml.bind.JAXBContext
import com.github.nill14.beancreator.model.IModel
import com.github.nill14.beancreator.model.jaxb.JaxbModel

class ModelXmlReader {

	def readXml(is: InputStream): IModel = {
		val context = JAXBContext.newInstance(classOf[JaxbModel])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[IModel]
	}
	
}
