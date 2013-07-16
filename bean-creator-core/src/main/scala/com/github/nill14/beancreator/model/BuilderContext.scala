package com.github.nill14.beancreator.model

import com.github.nill14.beancreator.builder.IBuilderContext

case class BuilderContext
(
	val packageName: Option[String]

) extends IBuilderContext {
	
	def this(stereotype: IStereotype) = this(stereotype.packageName)
}