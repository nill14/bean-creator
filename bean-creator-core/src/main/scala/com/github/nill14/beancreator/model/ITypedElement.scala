package com.github.nill14.beancreator.model;

import scala.Option;

trait ITypedElement {
	
	def name: String
	
	def classType: Option[String]
	
	def comment: Option[String]
}
