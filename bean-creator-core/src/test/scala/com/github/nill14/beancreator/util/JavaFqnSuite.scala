package com.github.nill14.beancreator.template

import org.scalatest.FunSuite
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.github.nill14.beancreator.util.JavaFqn
 
@RunWith(classOf[JUnitRunner])
class JavaFqnSuite extends FunSuite {
 
  test("org.apache.commons.lang3.builder.Builder<String>") {
 
  	val fqn = "org.apache.commons.lang3.builder.Builder<String>"
  	val langBuilder = JavaFqn.key(fqn)
  	
  	val res = JavaFqn(fqn, Some("org.apache.commons.lang3.builder"),
  			"Builder", List(JavaFqn("String", None, "String", Nil)))
  	
    assert(res === langBuilder)
  }
 
//  test("pop is invoked on an empty stack") {
// 
//    val emptyStack = new Stack[Int]
//    intercept[NoSuchElementException] {
//      emptyStack.pop()
//    }
//    assert(emptyStack.isEmpty)
//  }
}