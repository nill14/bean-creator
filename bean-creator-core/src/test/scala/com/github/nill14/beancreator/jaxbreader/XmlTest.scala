package com.github.nill14.beancreator.jaxbreader

import com.github.nill14.beancreator.trivialwriter.MutableBeanBuilder
import java.io.BufferedInputStream
import java.io.PrintWriter
import java.io.FileInputStream

object XmlTest extends App {
 
  	val FOO_XML = "src/test/resources/MyBean1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val bean = (new BeanXmlReader).readXml(is)	
  	
  	val w: PrintWriter = new PrintWriter(System.out)
  	val builder = new MutableBeanBuilder
  	builder.build(w, bean)
  	
  	w.close
}