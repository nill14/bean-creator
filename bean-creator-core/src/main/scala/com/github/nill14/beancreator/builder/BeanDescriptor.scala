package com.github.nill14.beancreator.builder

import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation._
import javax.xml.bind.annotation.adapters.XmlAdapter
import java.util.ArrayList
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter
import scala.collection.JavaConversions
import com.github.nill14.beancreator.util.Binding._


class SeqFieldDescriptorAdapter extends AbstractListAdapter[FieldDescriptor]


@XmlRootElement(name = "MutableBean")
@XmlAccessorType(XmlAccessType.FIELD)
case class BeanDescriptor (

	@xmlAttribute(name = "name")	
	name: String,
	
	@xmlAttribute(name = "packageName")
	packageName: String,
	
	@xmlElement(name = "comment")
	comment: String,
	
	@xmlElement(name = "field", `type` = classOf[FieldDescriptor])
	@xmlJavaTypeAdapter(classOf[SeqFieldDescriptorAdapter])
	fields: Seq[FieldDescriptor]) {
	
	// only needed and accessed by JAXB
	private def this() = this("", "", "", Vector.empty)
	
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//	public Seq<FieldDescriptor> fields() {
//		Buffer<FieldDescriptor> seq = (Buffer)JavaConversions.asScalaBuffer(fields);
//		return seq;//(List)fields;
//	}
}