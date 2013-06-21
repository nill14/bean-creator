package com.github.nill14.beancreator.builder

trait FieldDescriptor {

	def name: String
	def comment: String
	def classType: Class[_]
	def defaultValue: Any
}