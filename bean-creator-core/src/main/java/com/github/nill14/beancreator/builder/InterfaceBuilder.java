package com.github.nill14.beancreator.builder;

import java.io.Writer;

import com.github.nill14.beancreator.model.MutableBean;
import com.github.nill14.shellformat.ShellDictionary;
import com.github.nill14.shellformat.ShellFormat;

public class InterfaceBuilder implements IBuilder {

	
	
	{
		ShellDictionary dict = ShellFormat.dictionary();
		dict.append("import", "");
		
	}
	
	
	@Override
	public void build(Writer writer, MutableBean bean) {
		// TODO Auto-generated method stub
		
	}
	

}
