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
import java.util.Map;

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
import com.github.nill14.beancreator.builder.IBuilderContext;
import com.github.nill14.beancreator.immutable.ImmutableBeanBuilder;
import com.github.nill14.beancreator.jaxbreader.ModelXmlReader;
import com.github.nill14.beancreator.model.BuilderContext;
import com.github.nill14.beancreator.model.IBean;
import com.github.nill14.beancreator.model.IModel;
import com.github.nill14.beancreator.model.IStereotype;
import com.github.nill14.beancreator.mutable.MutableBeanBuilder;
import com.google.common.collect.Maps;
import static scala.collection.JavaConversions.*;

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
			File javaDir = new File(project.getBuild().getSourceDirectory());
			
			getLog().info(String.format("Found %d xmlDefs", xmlDefs.length));
			
			for (String xmlDef : xmlDefs) {
				File inputFile = new File(resDir, xmlDef);
				getLog().info(String.format("Reading input file %s", inputFile));
				InputStream is = new BufferedInputStream(new FileInputStream(inputFile));
				
				IModel model = (new ModelXmlReader()).readXml(is);
				is.close();
				
				anon2(model, javaDir);
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
	
	private void anon2(IModel model, File javaDir) throws FileNotFoundException, IOException {
		
		for (IStereotype stereotype : seqAsJavaList(model.stereotypes())) {
			for (IBean bean : asJavaList(model.beans())) {
				anon1(javaDir, bean, stereotype);
			}
		}
	}
	
	private void anon1(File javaDir, IBean bean, IStereotype stereotype) throws FileNotFoundException, IOException{
		File packageFile = getPackageDir(javaDir, stereotype.packageName());
		getLog().info(String.format("Creating package %s", packageFile));
		packageFile.mkdirs();
	
		File outputFile = new File(packageFile, bean.name() + ".java");
		getLog().info(String.format("Writing output file %s", outputFile));
		outputFile.createNewFile();
	  	Writer w = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
	  	
	  	Map<String, IBeanBuilder> builders = Maps.newHashMap();
	  	builders.put("immutable", new ImmutableBeanBuilder());
	  	builders.put("mutable", new MutableBeanBuilder());
	  	
	  	
	  	IBeanBuilder builder = builders.get(stereotype.name());
	  	IBuilderContext ctx = new BuilderContext(stereotype);
	  	builder.build(w, bean, ctx);
	  	w.close();
	}
}
