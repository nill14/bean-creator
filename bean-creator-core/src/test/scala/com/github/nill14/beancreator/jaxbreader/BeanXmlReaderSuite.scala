package com.github.nill14.beancreator.trivialwriter

import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
 
@RunWith(classOf[JUnitRunner])
class BeanXmlReaderSuite extends FunSuite {
 
//  test("Produce a simple xml bean") {
// 
//  	val FOO_XML = "src/test/resources/MyBean1.xml";
//  	val is = new BufferedInputStream(new FileInputStream(FOO_XML))
//  	
//  	val bean = (new BeanXmlReader).readXml(is)	
//  	
//  	val w: PrintWriter = new PrintWriter(System.out)
//  	val builder = new MutableBeanBuilder
//  	builder.build(w, bean)
//  	
//  	w.close
//  }  
 
  test("pop is invoked on an empty stack") {
 
    val emptyStack = new Stack[Int]
    intercept[NoSuchElementException] {
      emptyStack.pop()
    }
    assert(emptyStack.isEmpty)
  }
}