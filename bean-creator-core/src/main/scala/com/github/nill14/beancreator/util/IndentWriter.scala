package com.github.nill14.beancreator.util

import java.io.PrintWriter
import java.io.Writer


trait IndentWriter {
	
    object IndentType extends Enumeration {
        type IntentType = Value
        val Tab, Space = Value
        
        def toChar(t: Value) = t match {
        	case Tab => '\t'
        	case Space => ' '
        }
        
    }
    import IndentType._
	
    def out: PrintWriter
	var indentSize = 1
	var indentType = Tab
	private var _indentLevel = 0
	
	def incIndent {
    	indentLevel +=  1
    }
    
    def decIndent {
    	if (indentLevel > 0) indentLevel -= 1 
    }
    
    //getter
    def indentLevel = _indentLevel
    
    //setter
    def indentLevel_= (indentLevel: Int) = {
    	if (indentLevel < 0) throw new Error(s"indentLevel:$indentLevel < 0!")
    	_indentLevel = indentLevel
    }
	
	private var isNewline = true
	
	def println(str: String) {
    	indent
    	out.println(str)
    	isNewline = true
    } 
    
	def print(str: String) {
		indent
    	out.print(str)
    	isNewline = false
    } 
	
	def println {
		out.println
		isNewline = false
	}
	
	private def indent {
		if (isNewline) {
			val c = toChar(indentType)
			for (i <- 1 to indentSize) out.print(c)
			isNewline = false
		}
	}
	
	def close {
		out.close
	}

}

object DefIndentWriter {
	private class DefaultIndentWriter(val out: PrintWriter) extends IndentWriter 
	def create(out: PrintWriter): IndentWriter = new DefaultIndentWriter(out)
}