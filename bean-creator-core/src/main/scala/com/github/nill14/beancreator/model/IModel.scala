package com.github.nill14.beancreator.model;

import scala.Option
import scala.collection.Seq;

trait IModel {

	//def comment: Option[String]
	
	def beans: Seq[IBean]
	
	def stereotypes: Seq[IStereotype] 
	
}
