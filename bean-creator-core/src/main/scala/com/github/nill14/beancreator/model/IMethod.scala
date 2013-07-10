package com.github.nill14.beancreator.model;

import scala.Option;
import scala.collection.Seq;

trait IMethod extends ITypedElement {

	
	def members: Seq[String]

}
