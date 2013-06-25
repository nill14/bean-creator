//package com.github.nill14.beancreator;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//
//import com.github.nill14.beancreator.model.Field;
//import com.github.nill14.beancreator.model.MutableBean;
//
//
//public class MainWriter {
//
//	public static void main(String[] args) throws JAXBException, IOException {
//
//		MutableBean bean = new MutableBean();
//		List<Field> fields = bean.getFields();
//		{
//			Field fieldName = new Field();
//			fieldName.name = "name";
//			fieldName.type = "java.lang.String";
//			fieldName.value = "Hello";
//			fields.add(fieldName);
//		}
//		{
//			Field fieldAge = new Field();
//			fieldAge.name = "age";
//			fieldAge.type = "int";
//			fieldAge.value = "35";
//			fields.add(fieldAge);
//		}
//		{
//			Field fieldWeight = new Field();
//			fieldWeight.name = "weight";
//			fieldWeight.type = "double";
//			fieldWeight.value = "90.0";
//			fields.add(fieldWeight);
//		}
//		
//		
//		// create JAXB context and instantiate marshaller
//		JAXBContext context = JAXBContext.newInstance(MutableBean.class);
//		Marshaller m = context.createMarshaller();
//		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//	    m.marshal(bean, System.out);
//	}
//
//}