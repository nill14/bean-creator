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
import java.io.Writer;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import scala.Option;

import com.github.nill14.beancreator.builder.IBeanBuilder;
import com.github.nill14.beancreator.immutable.ImmutableBeanBuilder;
import com.github.nill14.beancreator.jaxbreader.BeanXmlReader;
import com.github.nill14.beancreator.model.IBean;

@Mojo( name = "foo", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
//@Execute(goal = "foo", phase = LifecyclePhase.INSTALL)
class BeanCreatorMojo extends AbstractMojo {

	@Parameter
	private String[] xmlDefs;
	
//	@Parameter(defaultValue="${project}")
	@Component
	private MavenProject project;

	public void execute() throws MojoExecutionException, MojoFailureException {
		
		try {
//			getLog().info(greeting);
			File resDir = new File(project.getBasedir(), "src/main/resources");
			File javaDir = new File(project.getBasedir(), "src/main/java");
			
			getLog().info(String.format("Found %d xmlDefs", xmlDefs.length));
			
			for (String xmlDef : xmlDefs) {
				File inputFile = new File(resDir, xmlDef);
				getLog().info(String.format("Reading input file %s", inputFile));
				InputStream is = new BufferedInputStream(new FileInputStream(inputFile));
				
				IBean bean = (new BeanXmlReader()).readXml(is);
				is.close();
				
				File packageFile = getPackageDir(javaDir, bean.packageName());
				getLog().info(String.format("Creating package %s", packageFile));
				packageFile.mkdirs();
	  	
				File outputFile = new File(packageFile, bean.name() + ".java");
				getLog().info(String.format("Writing output file %s", outputFile));
				outputFile.createNewFile();
			  	Writer w = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
			  	IBeanBuilder builder = new ImmutableBeanBuilder();
			  	builder.build(w, bean);
			  	w.close();
			}
		} catch (FileNotFoundException e) {
			throw new MojoFailureException(e.getMessage(), e);
		} catch (IOException e) {
			throw new MojoFailureException(e.getMessage(), e);
		}
		
	}

	private File getPackageDir(File javaDir, Option<String> packageName) {
		if (packageName.isDefined()) {
			return new File(javaDir, packageName.get().replace(".", "/"));
		}
		else return javaDir;
	}
	
}
