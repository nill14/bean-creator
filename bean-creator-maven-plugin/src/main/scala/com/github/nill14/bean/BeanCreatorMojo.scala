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
//import org.apache.maven.plugin.MojoFailureException
//import org.apache.maven.plugin.MojoExecutionException
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
//	@Parameter(defaultValue="${project}")
//	var project: MavenProject 
//	
//	
//	def execute {
//		try {
//			getLog.info(greeting);
//			val resDir = new File(project.getBasedir, "src/main/resources")
//			val javaDir = new File(project.getBasedir, "src/main/java")
//			
//			for (xmlDef <- xmlDefs) {
//				val inputFile = new File(resDir, xmlDef)
//				getLog.debug(s"Reading input file $inputFile");
//				val is = new BufferedInputStream(new FileInputStream(inputFile))
////				val is = this.getClass().getResourceAsStream(xmlDef)
//				
//				val bean = (new BeanXmlReader).readXml(is)	
//				is.close
//				
//				val packageFile = bean.packageName match {
//					case Some(pack) => {
//						val dir = new File(javaDir, pack.replace(".", File.separator))
//						dir.mkdirs
//						dir
//					}
//					case None => javaDir
//				}
//				
//				val outputFile = new File(packageFile, bean.name)
//				getLog.debug(s"Writing output file $outputFile");
//				val w = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))
//				(new MutableBeanBuilder).build(w, bean)
//				w.close
//			}
//			
//		} catch  {
//			case e: RuntimeException => throw new MojoFailureException(e.getMessage, e)
//			case e: Exception => throw new MojoExecutionException(e.getMessage, e)
//		}
//	}
//	
//}
