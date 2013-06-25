package com.github.nill14.bean

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugin.MojoFailureException
import org.apache.maven.plugins.annotations.Parameter

/**
 * Says "Hi" to the user.
 *
 */
@Mojo( name = "sayhi", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)//GENERATE_SOURCES)
class BeanCreatorMojo extends AbstractMojo {

		/**
	 * The greeting to display.
	 */
	@Parameter(property = "sayhi.greeting", defaultValue = "Hello World!")
	var greeting: String = "";
	
	  /*
     * Perform whatever build-process behavior this <code>Mojo</code> implements.
     * <br/>
     * This is the main trigger for the <code>Mojo</code> inside the <code>Maven</code> system, and allows
     * the <code>Mojo</code> to communicate errors.
     *
     * @throws MojoExecutionException if an unexpected problem occurs.
     * Throwing this exception causes a "BUILD ERROR" message to be displayed.
     * @throws MojoFailureException if an expected problem (such as a compilation failure) occurs.
     * Throwing this exception causes a "BUILD FAILURE" message to be displayed.
     */
	
	def execute {
		getLog.info(greeting);
		throw new MojoFailureException("I am to say Hello World!")
	}
	
}
