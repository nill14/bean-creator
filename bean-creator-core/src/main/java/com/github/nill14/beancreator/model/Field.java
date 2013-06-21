package com.github.nill14.beancreator.model;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
 
@XmlType(propOrder = { "name", "type", "value" })
@XmlAccessorOrder(value = XmlAccessOrder.ALPHABETICAL)
public class Field {
 
   @XmlAttribute
   public String name;
 
//   @XmlValue
   @XmlAttribute
   public String type;
   
   @XmlAttribute
   public String value;
 
}