package com.github.nill14.beancreator.model;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Lists;
 
@XmlRootElement(name = "MutableBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class MutableBean {
 
   //@XmlJavaTypeAdapter(MyMapAdapter.class)
   //@XmlList
   @XmlElement(name = "field")
   private List<Field> fields = Lists.newArrayList();
 
   public List<Field> getFields() {
      return fields;
   }
 
   public void setFields(List<Field> fields) {
      this.fields = fields;
   }
 
}