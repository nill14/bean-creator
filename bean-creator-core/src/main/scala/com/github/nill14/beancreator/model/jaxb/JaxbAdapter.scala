package com.github.nill14.beancreator.model.jaxb

import java.util.{ArrayList, List => JList}
import javax.xml.bind.annotation._
import scala.collection.JavaConverters._
import com.github.nill14.beancreator.util.Binding._
import javax.xml.bind.annotation.adapters.XmlAdapter

object JaxbAdapter {
	
  class FieldListAdapter extends AbstractListAdapter[JaxbField, FieldList] {
    def create(l: JList[JaxbField]) = new FieldList(l)
  }

  @XmlRootElement(name = "fields")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class FieldList(@xmlElementRef(name = "fields") elem: JList[JaxbField]) extends AbstractList[JaxbField] {
    def this() = this(null)
  }
  
  
  class MethodListAdapter extends AbstractListAdapter[JaxbMethod, MethodList] {
    def create(l: JList[JaxbMethod]) = new MethodList(l)
  }  
  
  @XmlRootElement(name = "methods")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class MethodList(@xmlElementRef(name = "methods") elem: JList[JaxbMethod]) extends AbstractList[JaxbMethod] {
    def this() = this(null)
  }  
  
  
//  	abstract class ListAdapter[A] extends XmlAdapter[JList[A], Seq[A]] {
//		def marshal(v: Seq[A]): JList[A] = v.asJava
//		def unmarshal(v: JList[A]): Seq[A] = v.asScala.toVector
//	}	
//  	
//  	class FieldDescriptorAdapter extends ListAdapter[FieldDescriptor]
  
  

}