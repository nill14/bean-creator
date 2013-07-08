package com.github.nill14.beancreator.builder

trait IMemberDescriptor {
	def name: String
	def classType: Option[String]
	def comment: Option[String]

}