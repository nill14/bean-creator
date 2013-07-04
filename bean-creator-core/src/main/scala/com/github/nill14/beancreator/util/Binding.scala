/* https://github.com/krasserm/eventsourcing-example/blob/play-blog/modules/service/src/main/scala/dev/example/eventsourcing/util/Binding.scala 
 * Apache 2.0 license
 * */
package com.github.nill14.beancreator.util

import java.math.{ BigDecimal => JBigDecimal }
import java.util.{ ArrayList, List => JList }

import scala.annotation.target.field

import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters._

object Binding {
	type JList[A] = java.util.List[A]

	type xmlAnyElement = XmlAnyElement @field
	type xmlAttribute = XmlAttribute @field
	type xmlElement = XmlElement @field
	type xmlElementRef = XmlElementRef @field
	type xmlElementRefs = XmlElementRefs @field
	type xmlElementWrapper = XmlElementWrapper @field
	type xmlJavaTypeAdapter = XmlJavaTypeAdapter @field
	type xmlTransient = XmlTransient @field

	class BigDecimalAdapter extends XmlAdapter[JBigDecimal, BigDecimal] {
		import BigDecimal.javaBigDecimal2bigDecimal
		def unmarshal(v: JBigDecimal): BigDecimal = v // implicit conversion
		def marshal(v: BigDecimal): JBigDecimal = v.underlying
	}

	abstract class AbstractListAdapter[A, B <: AbstractList[A]] extends XmlAdapter[B, Seq[A]] {
		import scala.collection.JavaConverters._

		def marshal(v: Seq[A]) = if (v == null) create(new ArrayList[A]) else create(v.asJava)
		def unmarshal(v: B): Seq[A] = v.elem.asScala.toVector
		def create(l: JList[A]): B
	}

	trait AbstractList[A] {
		def elem: JList[A]
	}	
	
	
//	abstract class AbstractListAdapter[A, J <: AbstractList[A], S <: Seq[A]] extends XmlAdapter[J, S] {
//		import scala.collection.JavaConverters._
//
//		def marshal(v: S): J = {
//			if (v == null) createJava(Collections.emptyList[A]) 
//			else createJava(v.asJava)
//		}
//		def unmarshal(v: J): S = createScala(v)//v.asScala.toVector
//		def createJava(l: JList[A]): J
//		def createScala(l: JList[A]): S
//	}
//	
//	class ListAdapter[A] extends AbstractListAdapter[A, ArrayList[A], Seq[A]] {
//		import scala.collection.JavaConverters._
//		
//		def createJava(l: JList[A]): ArrayList[A] = {
//			if (l.isInstanceOf[ArrayList[A]]) l.asInstanceOf[ArrayList[A]]
//			else new ArrayList(l)
//		}
//		def createScala(l: JList[A]): Seq[A] = l.asScala.toVector
//	}
	
	class StringOptionAdapter extends OptionAdapter[String](null, "")
	
	class OptionAdapter[A](nones: A*) extends XmlAdapter[A, Option[A]] {
		def marshal(v: Option[A]): A = v.getOrElse(nones(0))
		def unmarshal(v: A) = if (nones contains v) None else Some(v)
	}	
	
	class CommaSeparatedAdapter extends XmlAdapter[String, Seq[String]] {
		def marshal(v: Seq[String]) = v.mkString(", ")
		def unmarshal(v: String) = v.split(",").map(_.trim)
		
	}
	

}