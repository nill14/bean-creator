package com.github.nill14.beancreator.template

import org.scalatest.FunSuite
import scala.collection.mutable.Stack
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.Assertions
 
@RunWith(classOf[JUnitRunner])
class LineInterpolatorSuite extends FunSuite {
 
  implicit def line(sc : StringContext) = new LineInterpolator(sc)
  implicit def esc(sc : StringContext) = new EscapeInterpolator(sc)
  
  test("stripLeadingNewline") {
 
    val str = line"""
 abc,
 def"""
    
    val expected = """ abc,
 def"""
      
    assert (expected === str)  
  }
 
  test("format interpolator") {
    
    val num = 0.23
    val str = f"num=$num%2.1f, num=${num-0.0001}%2.3f"
    
    val expected = "num=0.2, num=0.230"
    
    assert (expected === str)
  }
  
  test("multiple imports") {
    
    val importSection = List("java.lang.String", "java.util.Collection")
    val className = "ClassName"
    
    val str = line"""
package template;

import ${importSection}%n;

class $className {}
"""    

val expected = """package template;

import java.lang.String;
import java.util.Collection;

class ClassName {}
"""  

    assert (expected === str)
  
  }
  
  test("multiple items") {
    val items = List("item1", "item2", "item3")
    
    val result = line"""${items}%n=$$${items}%n"""
    
    val expected = """item1=$item1
item1=$item2
item1=$item3
item2=$item1
item2=$item2
item2=$item3
item3=$item1
item3=$item2
item3=$item3""" 
    	
//    println(expected)
//    println("")
//    println(result)
    
    assert (expected === result)
  }
  
  test("split lines") {
    
    val result = line"""
line1

line2
"""
    
   val expected = line"""line1

line2
"""
    assert (expected === result)
  }
  
  test("raw") {
    assert ("a\\nb" === raw"a\nb")
  }
  
  test("no prefix") {
    val abc = "abc"
    assert ("123abc123" === line"123${abc}123")
  }
  
  test("no prefix2") {
    val abc = "abc"
    val result = line"""
${abc}abc"""

    assert ("abcabc" === result)
  }  
  
  test("no variable") {
    assert ("123" === line"123")
  }
  
  test("empty lines") {
    
    val result = line"""



sth

"""    
    val expected = """


sth

"""
    assert (expected === result)
  }
  
  test("empty lines 2") {
    val line = new LineInterpolator(null) 
    val str = """


sth

"""
    
    val result = line.splitLines(str)
//    val result = str.split(line.spacesNewlinePattern)

	val expected = Array("", "", "", "sth", "", "")

	assert(expected === result)
  }  
  
  test("newlines at the end") {
  	val sth = "sth"
  	val result = line"""
${sth}

"""  	
  	
  	val expected = """sth

"""
  	assert (expected === result)	
  }
  
  test("multiple variables at line") {
    
    val importSection = List("java.lang.String", "java.util.Collection")
    val className = "ClassName"
    val test = "test"
    
    val str = line"import ${importSection}%n; class ${className} $test"    

val expected = """import java.lang.String; class ClassName test
import java.util.Collection; class ClassName test"""  

    assert (expected === str)
  
  }  
  
  implicit class RawStrCmp(val expected: String) {
	  def === (actual: String): Option[String] = {
	    if (expected == actual) {
	      None
	    }
	    else {
	      val msg = esc"RawStrCmp failed. Expected/Resulted:\n${expected}\n${actual}\n"
	      Some(msg)
	    }
	  }
  }

}

