package com.github.nill14.beancreator.model;

import scala.Option
import scala.collection.Seq;

trait IStereotype {

	//def comment: Option[String]
	
	def name: String
	
	def packageName: Option[String] 
	
}
