package com.github.nill14.beancreator.model;

import scala.Option;

trait IField extends ITypedElement {
	
	def defaultValue: Option[String];

}
