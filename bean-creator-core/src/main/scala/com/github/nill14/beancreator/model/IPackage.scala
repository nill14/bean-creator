package com.github.nill14.beancreator.model;

import scala.Option
import scala.collection.Seq;

trait IPackage {

	def name: String
	
	//def comment: Option[String]
	
	def beans: Seq[IBean]
	
	def stereotypes: Seq[String] 
	
}
