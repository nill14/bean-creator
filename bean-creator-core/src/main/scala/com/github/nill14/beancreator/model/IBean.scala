package com.github.nill14.beancreator.model;

import scala.Option;
import scala.collection.Seq;

trait IBean {

	def name: String
	
	def comment: Option[String]
	
	def packageName: Option[String] //eventually IPackage#name
	
	def fields: Seq[IField]
	
	def methods: Seq[IMethod]
	
	//def stereotypes: Seq[String] //eventually IPackage#stereotypes
	
}
