package com.github.nill14.beancreator.jaxbreader

import java.io.InputStream

import com.github.nill14.beancreator.builder.BeanDescriptor

import javax.xml.bind.JAXBContext

class BeanXmlReader {

	def readXml(is: InputStream): BeanDescriptor = {
		val context = JAXBContext.newInstance(classOf[BeanDescriptor])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[BeanDescriptor]
	}
	
}
