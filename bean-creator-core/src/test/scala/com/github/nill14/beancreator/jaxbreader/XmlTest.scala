package com.github.nill14.beancreator.jaxbreader

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.PrintWriter
import com.github.nill14.beancreator.immutable.ImmutableBeanBuilder
import scala.collection._
import com.github.nill14.beancreator.builder.IBeanBuilder
import com.github.nill14.beancreator.mutable.MutableBeanBuilder
import com.github.nill14.beancreator.model.BuilderContext

object XmlTest extends App {
 
  	val FOO_XML = "src/test/resources/MyBean1.xml";
  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
  	
  	val model = (new ModelXmlReader).readXml(is)	
  	
//  	val w = DefIndentWriter.create(new PrintWriter(System.out))
  	val w = new PrintWriter(System.out)
  	
  	val stereotypes = new mutable.HashMap[String, IBeanBuilder]
  	stereotypes += (("immutable", new ImmutableBeanBuilder))
  	stereotypes += (("mutable", new MutableBeanBuilder))
  			
  	for {s <- model.stereotypes
  		if stereotypes contains s.name
  		val builder = (stereotypes get s.name).get
  		bean <- model.beans
  		ctx = new BuilderContext(s)
  	} {
  		builder.build(w, bean, ctx)
  	
  		for (field <- bean.fields) w.println(field.name)
  		for (method <- bean.methods) w.println(method.name)
  	}
  	

  	w.close
}