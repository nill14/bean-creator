package com.github.nill14.beancreator.util

import java.io.PipedWriter
import java.io.StringWriter
import java.io.PrintWriter
import java.io.Writer
import com.github.nill14.beancreator.model.IBean
import com.github.nill14.beancreator.tool.ImportResolver

class JavaCodeWriter(bean: IBean, iWriter: Writer) {

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
		
		iWriter.append(topBuf.getBuffer)
		iWriter.append(bottomBuf.getBuffer)
		
		iWriter.close
	}
}