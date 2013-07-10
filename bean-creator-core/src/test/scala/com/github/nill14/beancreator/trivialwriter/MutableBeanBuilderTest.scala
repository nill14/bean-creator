package com.github.nill14.beancreator.trivialwriter

import java.io.PrintWriter
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import com.github.nill14.beancreator.model._
import org.scalatest.junit.JUnitRunner
 
@RunWith(classOf[JUnitRunner])
class MutableBeanBuilderSuite extends FunSuite {
 
//  test("Produce a simple test bean") {
// 
//  	val bean = new BeanDescriptor {
//  		val packageName = null
//  		val name = "MyBean"
//  		val comment = "Javadoc comment"
//  		val fields: Seq[FieldDescriptor] = Array(new FieldDescriptor{
//  			val comment = "Number of iterations"  			
//  			val name = "counter"
//  			val classType = classOf[Integer]
//  			val defaultValue = null
//  		})	
//  	}
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