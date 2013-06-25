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

	abstract class AbstractListAdapter[A] extends XmlAdapter[ArrayList[A], Seq[A]] {
		import scala.collection.JavaConverters._

		def marshal(v: Seq[A]) = new ArrayList(v.asJava)
		def unmarshal(v: ArrayList[A]) = v.asScala.toVector
	}

}