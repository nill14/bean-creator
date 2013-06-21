package com.github.nill14.beancreator.template

import java.util.regex.Pattern


object FmtFlag {
  
  	def parseFromInput(input: String): FmtFlag = {
	  val m = Pattern.compile("^([%][^%a-z]*[a-z])").matcher(input)
	  
	  if (m.find()) {
	    m.group(1) match {
	      case "%n" => NewlineFlag 
	      case "%i" => IntendFlag 
	      case "%2x" => RepeatFlag("%2x") 
	      case x => throw new Error(s"unknown flag format:$x")
	    }
	  }
	  else NoneFlag
	}
}

abstract sealed class FmtFlag(format: String) {
  def length = format.length
}

case object NewlineFlag extends FmtFlag("%n")
case object IntendFlag extends FmtFlag("%i")
case class RepeatFlag(format: String) extends FmtFlag(format)
case object NoneFlag extends FmtFlag("")