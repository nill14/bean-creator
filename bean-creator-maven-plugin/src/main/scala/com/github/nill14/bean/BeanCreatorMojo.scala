//package com.github.nill14.bean
//
//import java.io.BufferedInputStream
//import java.io.BufferedWriter
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileWriter
//import java.io.PrintWriter
//import org.apache.maven.plugin.AbstractMojo
//import org.apache.maven.plugins.annotations.LifecyclePhase
//import org.apache.maven.plugins.annotations.Mojo
//import org.apache.maven.plugins.annotations.Parameter
//import org.apache.maven.project.MavenProject
//import com.github.nill14.beancreator.jaxbreader.BeanXmlReader
//import com.github.nill14.beancreator.trivialwriter.MutableBeanBuilder
//
///**
// * Says "Hi" to the user.
// *
// */
//@Mojo( name = "sayhi", defaultPhase = LifecyclePhase.GENERATE_SOURCES) //PROCESS_RESOURCES)//
//class BeanCreatorMojo extends AbstractMojo {
//
//	@Parameter(property = "sayhi.greeting", defaultValue = "Hello World!")
//	var greeting: String = ""
//	
//	@Parameter
//	var xmlDefs: Array[String] = Array.empty
//	
//	@Parameter
//	var project: MavenProject 
//	
//	  /*
//     * Perform whatever build-process behavior this <code>Mojo</code> implements.
//     * <br/>
//     * This is the main trigger for the <code>Mojo</code> inside the <code>Maven</code> system, and allows
//     * the <code>Mojo</code> to communicate errors.
//     *
//     * @throws MojoExecutionException if an unexpected problem occurs.
//     * Throwing this exception causes a "BUILD ERROR" message to be displayed.
//     * @throws MojoFailureException if an expected problem (such as a compilation failure) occurs.
//     * Throwing this exception causes a "BUILD FAILURE" message to be displayed.
//     */
//	
//	def execute {
//		getLog.info(greeting);
//		val resDir = new File(project.getBasedir(), "src/main/resources")
//		val javaDir = new File(project.getBasedir(), "src/main/java")
//		
//		for (xmlDef <- xmlDefs) {
//			val inputFile = new File(resDir, xmlDef)
//			val is = new BufferedInputStream(new FileInputStream(inputFile))
////			val is = this.getClass().getResourceAsStream(xmlDef)
//			
//			val bean = (new BeanXmlReader).readXml(is)	
//			val packageFile = new File(javaDir, bean.packageName.replaceAll(".", File.separator))
//  	
//			val outputFile = new File(packageFile, bean.name)
//		  	val w = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))
//		  	val builder = new MutableBeanBuilder
//		  	builder.build(w, bean)
//		}
//		
//		
////		throw new MojoFailureException("I am to say Hello World!")
//	}
//	
//}
