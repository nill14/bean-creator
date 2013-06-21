package com.github.nill14.beancreator.builder

import java.util.List

trait BeanDescriptor {

	def name: String
	def packageName: String
	def comment: String
	def fields: Seq[FieldDescriptor]
}