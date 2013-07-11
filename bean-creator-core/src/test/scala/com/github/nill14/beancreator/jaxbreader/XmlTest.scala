package com.github.nill14.beancreator.jaxbreader

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.PrintWriter
import com.github.nill14.beancreator.immutable.ImmutableBeanBuilder

object XmlTest extends App {
 
  	val FOO_XML = "src/test/resources/MyBean1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val bean = (new BeanXmlReader).readXml(is)	
  	
//  	val w = DefIndentWriter.create(new PrintWriter(System.out))
  	val w = new PrintWriter(System.out)
  	val builder = new ImmutableBeanBuilder
  	builder.build(w, bean)
  	
  	
  	for (field <- bean.fields) w.println(field.name)
  	for (method <- bean.methods) w.println(method.name)

  	w.close
}