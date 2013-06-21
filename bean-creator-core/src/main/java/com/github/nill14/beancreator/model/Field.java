package com.github.nill14.beancreator.model;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.github.nill14.beancreator.builder.FieldDescriptor;
 
@XmlType(propOrder = { "name", "type", "value" })
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
public class Field implements FieldDescriptor {
 
   @XmlAttribute
   public String name;
 
//   @XmlValue
   @XmlAttribute
   public String type;
   
   @XmlAttribute
   public String value;
   
   @XmlValue
   public String comment;

	@Override
	public String name() {
		return name;
	}

	@Override
	public String comment() {
		return comment;
	}
	
	@Override
	public Class<?> classType() {
		if ("int".equals(type)) {
			return int.class;
		} 
		else if ("double".equals(type)) {
			return double.class;
		}  
		try {
			return Class.forName(type);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Object defaultValue() {
		return null;
	}
 
}