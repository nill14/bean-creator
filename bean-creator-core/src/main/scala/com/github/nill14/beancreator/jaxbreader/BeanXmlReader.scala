package com.github.nill14.beancreator.jaxbreader

import javax.xml.bind.JAXBContext
import com.github.nill14.beancreator.model.MutableBean
import java.io.InputStream

class BeanXmlReader {

	def readXml(is: InputStream) = {
		val context = JAXBContext.newInstance(classOf[MutableBean])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[MutableBean]
	}
	
}
