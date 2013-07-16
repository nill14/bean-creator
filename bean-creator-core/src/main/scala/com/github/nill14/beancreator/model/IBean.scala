package com.github.nill14.beancreator.model;

import scala.Option;
import scala.collection.Seq;

trait IBean {

	def name: String
	
	def comment: Option[String]
	
	def fields: Seq[IField]
	
	def methods: Seq[IMethod]
	
}
