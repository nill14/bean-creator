package com.github.nill14.beancreator.jaxbreader

import java.io.BufferedInputStream
import java.io.PrintWriter
import java.io.FileInputStream
import javax.xml.bind.JAXBContext
import com.github.nill14.beancreator.model._
import com.github.nill14.beancreator.model.jaxb.JaxbField
import com.github.nill14.beancreator.mutable.MutableBeanBuilder

object XmlFieldTest extends App {
 
  	val FOO_XML = "src/test/resources/MyField1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val bean = {
		val context = JAXBContext.newInstance(classOf[JaxbField])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[JaxbField]
  	}
  	
  	val w: PrintWriter = new PrintWriter(System.out)
  	val builder = new MutableBeanBuilder
  	w println s"name=${bean.name} type=${bean.classType}"
  	
  	w.close
}