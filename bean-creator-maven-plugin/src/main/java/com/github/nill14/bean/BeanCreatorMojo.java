package com.github.nill14.bean;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.github.nill14.beancreator.builder.BeanDescriptor;
import com.github.nill14.beancreator.builder.FieldDescriptor;
import com.github.nill14.beancreator.jaxbreader.BeanXmlReader;
import com.github.nill14.beancreator.trivialwriter.MutableBeanBuilder;

/**
 * Says "Hi" to the user.
 *
 */
@Mojo( name = "sayhi", defaultPhase = LifecyclePhase.GENERATE_SOURCES) //PROCESS_RESOURCES)//
class BeanCreatorMojo extends AbstractMojo {

	@Parameter(property = "sayhi.greeting", defaultValue = "Hello World!")
	private String greeting = "";
	
	@Parameter
	private String[] xmlDefs;
	
	@Parameter(defaultValue="${project}")
	private MavenProject project;

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		try {
			getLog().info(greeting);
			File resDir = new File(project.getBasedir(), "src/main/resources");
			File javaDir = new File(project.getBasedir(), "src/main/java");
			
			for (String xmlDef : xmlDefs) {
				File inputFile = new File(resDir, xmlDef);
				getLog().debug(String.format("Reading input file %s", inputFile));
				InputStream is = new BufferedInputStream(new FileInputStream(inputFile));
				
				FieldDescriptor d = null;
				BeanDescriptor bean = (new BeanXmlReader()).readXml(is);
				is.close();
				
				File packageFile = getPackageDir(javaDir, bean.packageName());
				getLog().debug(String.format("Creating package %s", packageFile));
				packageFile.mkdirs();
	  	
				File outputFile = new File(packageFile, bean.name() + ".java");
				getLog().debug(String.format("Writing output file %s", outputFile));
				outputFile.createNewFile();
			  	PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
			  	MutableBeanBuilder builder = new MutableBeanBuilder();
			  	builder.build(w, bean);
			  	w.close();
			}
		} catch (FileNotFoundException e) {
			throw new MojoFailureException(e.getMessage(), e);
		} catch (IOException e) {
			throw new MojoFailureException(e.getMessage(), e);
		}
		
	}

	private File getPackageDir(File javaDir, String packageName) {
		if (packageName != null) {
			return new File(javaDir, packageName.replace(".", "/"));
		}
		else return javaDir;
	}
	
}