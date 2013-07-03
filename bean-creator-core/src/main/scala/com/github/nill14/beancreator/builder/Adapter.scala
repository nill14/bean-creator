package com.github.nill14.beancreator.builder

import java.util.{ArrayList, List => JList}
import javax.xml.bind.annotation._
import scala.collection.JavaConverters._
import com.github.nill14.beancreator.util.Binding._
import javax.xml.bind.annotation.adapters.XmlAdapter

object Adapter {
  class FieldListAdapter extends AbstractListAdapter[FieldDescriptor, FieldList] {
    def create(l: JList[FieldDescriptor]) = new FieldList(l)
  }

  @XmlRootElement(name = "fields")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class FieldList(@xmlElementRef(name = "fields") elem: JList[FieldDescriptor]) extends AbstractList[FieldDescriptor] {
    def this() = this(null)
  }
  
  
//  	abstract class ListAdapter[A] extends XmlAdapter[JList[A], Seq[A]] {
//		def marshal(v: Seq[A]): JList[A] = v.asJava
//		def unmarshal(v: JList[A]): Seq[A] = v.asScala.toVector
//	}	
//  	
//  	class FieldDescriptorAdapter extends ListAdapter[FieldDescriptor]
  
  

}