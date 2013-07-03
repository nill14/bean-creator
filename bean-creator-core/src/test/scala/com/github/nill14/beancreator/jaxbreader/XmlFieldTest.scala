package com.github.nill14.beancreator.jaxbreader

import com.github.nill14.beancreator.trivialwriter.MutableBeanBuilder
import java.io.BufferedInputStream
import java.io.PrintWriter
import java.io.FileInputStream
import javax.xml.bind.JAXBContext
import com.github.nill14.beancreator.builder.BeanDescriptor
import com.github.nill14.beancreator.builder.FieldDescriptor

object XmlFieldTest extends App {
 
  	val FOO_XML = "src/test/resources/MyField1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val bean = {
		val context = JAXBContext.newInstance(classOf[FieldDescriptor])
		val unmarshaller = context.createUnmarshaller
		unmarshaller.unmarshal(is).asInstanceOf[FieldDescriptor]
  	}
  	
  	val w: PrintWriter = new PrintWriter(System.out)
  	val builder = new MutableBeanBuilder
  	w println s"name=${bean.name} type=${bean.fieldType}"
  	
  	w.close
}