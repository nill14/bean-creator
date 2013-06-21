package com.github.nill14.beancreator.template

object Template {
  
  def mutableBean( packageDeclaration: Any,
		  			importSection: Any,
		  			className: Any,
		  			methodSection: Any) = {
s"""$packageDeclaration;
$importSection
    
public class $className {
    
    $methodSection
    
}"""
    
  }



		  			  
  def main(args: Array[String]) {
    
      val imports = List("java.lang.String", "java.util.Collection")
    
	  val beanCode = mutableBean("nill", imports.fold("")(_ + "\n" + _), "className", "methodSection");
	  println(beanCode)
  }		  			
		  			  
}