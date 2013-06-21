package com.github.nill14.beancreator.trivialwriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

public class CompileTest {

	public void compile(InputStream is) {
		
//		JavaFileObject java 
//		JavaCompiler compiler;
//		Javac
//		ClassLoader.getSystemClassLoader().co
	}
	
}

class MyJavaFileObject extends SimpleJavaFileObject {

	protected MyJavaFileObject(URI uri, Kind kind) {
		super(uri, Kind.SOURCE);
	}

	@Override
	public InputStream openInputStream() throws IOException {
		// TODO Auto-generated method stub
		return super.openInputStream();
	}
	
	@Override
	public OutputStream openOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return super.openOutputStream();
	}
	
	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors)
			throws IOException {
		// TODO Auto-generated method stub
		return super.getCharContent(ignoreEncodingErrors);
	}
	
}
