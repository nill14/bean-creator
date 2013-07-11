package com.github.nill14.beancreator.util

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import com.github.nill14.beancreator.tool.ImportResolver
import org.scalatest.junit.JUnitRunner
 
@RunWith(classOf[JUnitRunner])
class JavaFqnSuite extends FunSuite {
 
  test("org.apache.commons.lang3.builder.Builder<String>") {
 
  	val fqn = "org.apache.commons.lang3.builder.Builder<String>"
  	val langBuilder = JavaFqn.key(fqn)
  	
  	val res = JavaFqn(fqn, Some("org.apache.commons.lang3.builder"),
  			"Builder", List(JavaFqn("String", None, "String", Nil)))
  	
    assert(res === langBuilder)
  }
 
  test("Book out Builder") {
	  val fqn = "org.apache.commons.lang3.builder.Builder<String>"
	  val r = new ImportResolver(None)
	  r bookOut "Builder"
	  	
	  val langBuilder = JavaFqn.key(fqn)
	  
  	  val res = r ^ langBuilder
  	  
  	  assert (fqn === res)
  }
}