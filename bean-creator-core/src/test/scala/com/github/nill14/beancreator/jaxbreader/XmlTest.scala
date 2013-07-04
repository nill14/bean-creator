package com.github.nill14.beancreator.jaxbreader

import com.github.nill14.beancreator.trivialwriter.MutableBeanBuilder
import java.io.BufferedInputStream
import java.io.PrintWriter
import java.io.FileInputStream
import com.github.nill14.beancreator.util.IndentWriter
import com.github.nill14.beancreator.util.DefIndentWriter

object XmlTest extends App {
 
  	val FOO_XML = "src/test/resources/MyBean1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val bean = (new BeanXmlReader).readXml(is)	
  	
  	val w = DefIndentWriter.create(new PrintWriter(System.out))
  	val builder = new MutableBeanBuilder
  	builder.build(w, bean)
  	
  	
  	for (field <- bean.fields) w.println(field.name)
  	for (method <- bean.methods) w.println(method.name)

  	w.close
}