package com.github.nill14.beancreator.model.jaxb

import java.util.{ArrayList, List => JList}
import javax.xml.bind.annotation._
import scala.collection.JavaConverters._
import com.github.nill14.beancreator.util.Binding._
import javax.xml.bind.annotation.adapters.XmlAdapter

object JaxbAdapter {

	
  @XmlRootElement(name = "fields")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class JaxbFields(@xmlElementRef(name = "fields") elem: JList[JaxbField]) extends AbstractList[JaxbField] {
    def this() = this(null)
  }
  
  class FieldsAdapter extends AbstractListAdapter[JaxbField, JaxbFields] {
	  def create(list: JList[JaxbField]) = new JaxbFields(list)
  }
  
  
  
  @XmlRootElement(name = "methods")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class JaxbMethods(@xmlElementRef(name = "methods") elem: JList[JaxbMethod]) extends AbstractList[JaxbMethod] {
    def this() = this(null)
  }  
  
  class MethodsAdapter extends AbstractListAdapter[JaxbMethod, JaxbMethods] {
	  def create(list: JList[JaxbMethod]) = JaxbMethods(list)
  }  
  

  
  @XmlRootElement(name = "beans")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class JaxbBeans(@xmlElementRef(name = "beans") elem: JList[JaxbBean]) extends AbstractList[JaxbBean] {
    def this() = this(null)
  }   
  
  class BeansAdapter extends AbstractListAdapter[JaxbBean, JaxbBeans] {
    def create(list: JList[JaxbBean]) = JaxbBeans(list)
  } 
  

  
  @XmlRootElement(name = "stereotypes")
  @XmlAccessorType(XmlAccessType.FIELD)
  case class JaxbStereotypes(@xmlElementRef(name = "stereotype") elem: JList[JaxbStereotype]) extends AbstractList[JaxbStereotype] {
    def this() = this(null)
  }   
  
  class StereotypesAdapter extends AbstractListAdapter[JaxbStereotype, JaxbStereotypes] {
    def create(list: JList[JaxbStereotype]) = JaxbStereotypes(list)
  } 
  
  
//  	abstract class ListAdapter[A] extends XmlAdapter[JList[A], Seq[A]] {
//		def marshal(v: Seq[A]): JList[A] = v.asJava
//		def unmarshal(v: JList[A]): Seq[A] = v.asScala.toVector
//	}	
//  	
//  	class FieldDescriptorAdapter extends ListAdapter[FieldDescriptor]
  
  

}