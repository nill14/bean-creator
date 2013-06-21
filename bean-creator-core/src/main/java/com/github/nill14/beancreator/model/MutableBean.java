package com.github.nill14.beancreator.model;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import scala.collection.JavaConversions;
import scala.collection.Seq;
import scala.collection.mutable.Buffer;

import com.github.nill14.beancreator.builder.BeanDescriptor;
import com.github.nill14.beancreator.builder.FieldDescriptor;
import com.google.common.collect.Lists;
 
@XmlRootElement(name = "MutableBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class MutableBean implements BeanDescriptor {
 
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "packageName")
	private String packageName;
	
	@XmlElement(name = "comment")
	private String comment;
	
   //@XmlJavaTypeAdapter(MyMapAdapter.class)
   //@XmlList
   @XmlElement(name = "field")
   private List<Field> fields = Lists.newArrayList();
 

	@Override
	public String name() {
		return name;
	}
	
	@Override
	public String packageName() {
		return packageName;
	}
	
	@Override
	public String comment() {
		return comment;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Seq<FieldDescriptor> fields() {
		Buffer<FieldDescriptor> seq = (Buffer)JavaConversions.asScalaBuffer(fields);
		return seq;//(List)fields;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
}