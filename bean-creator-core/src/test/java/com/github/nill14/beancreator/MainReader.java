//package com.github.nill14.beancreator;
//
//import java.io.FileReader;
//import java.io.IOException;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//
//import com.github.nill14.beancreator.model.Field;
//import com.github.nill14.beancreator.model.MutableBean;
//
//
//public class MainReader {
//
//	private static final String FOO_XML = "src/test/resources/MyBean1.xml";
//
//	public static void main(String[] args) throws JAXBException, IOException {
//
//		// create JAXB context and instantiate marshaller
//		JAXBContext context = JAXBContext.newInstance(MutableBean.class);
//
//		// get variables from our xml file, created before
//		System.out.println();
//		System.out.println("Output from our XML File: ");
//		Unmarshaller um = context.createUnmarshaller();
//		MutableBean bean = (MutableBean) um.unmarshal(new FileReader(FOO_XML));
//
//		for (Field field : bean.getFields()) {
//			System.out.printf("private %s %s = %s\n", field.type, field.name,
//					field.value);
//		}
//	}
//
//}