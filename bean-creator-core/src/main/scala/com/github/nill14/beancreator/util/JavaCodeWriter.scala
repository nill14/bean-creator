package com.github.nill14.beancreator.util

import com.github.nill14.beancreator.builder.BeanDescriptor
import java.io.PipedWriter
import java.io.StringWriter
import java.io.PrintWriter
import com.github.nill14.beancreator.resolver.ImportResolver

class JavaCodeWriter(bean: BeanDescriptor, iWriter: IndentWriter) {

	private val topBuf = new StringWriter
	private val bottomBuf = new StringWriter
	
	private val topWriter = DefIndentWriter.create(new PrintWriter(topBuf))
	private val bottomWriter = DefIndentWriter.create(new PrintWriter(bottomBuf))
	
	val resolver = new ImportResolver(bean.packageName)
	def writer: IndentWriter = bottomWriter
	
	Utils.copyrightHeader(topWriter)
	
	bean.packageName match {
		case Some(x) => topWriter println s"package ${x};"
		case _ =>
	}
	
	def complete {
		topWriter.println
		for (imp <- resolver.collectImports) {
			topWriter println s"import ${imp};"
		}
		topWriter.println
		
		topWriter.close
		bottomWriter.close
		
		iWriter.out.append(topBuf.getBuffer)
		iWriter.out.append(bottomBuf.getBuffer)
		
		iWriter.close
	}
}