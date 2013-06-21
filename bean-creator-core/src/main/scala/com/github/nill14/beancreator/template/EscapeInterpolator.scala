package com.github.nill14.beancreator.template

import java.util.regex.Pattern

/*implicit */class EscapeInterpolator(val sc: StringContext)  {

//  {{{
// *   s"Hello, $name"
// * }}}
// *
// * is rewritten to be:
// *
// * {{{
// *   StringContext("Hello, ", "").s(name)
// * }}}
  
  
	def esc(args: Any*): String = {
	  val strings = sc.parts.iterator
      val expressions = args.iterator
      
	  val b = new StringBuilder(StringContext.treatEscapes(strings.next))
	 
	  while (strings.hasNext) {
	    b append EscapeInterpolator.escapeExpr(expressions.next)
	    b append StringContext.treatEscapes(strings.next)
	  }
	  
	  b.toString
	}
	
	
	
}

object EscapeInterpolator {

	def escapeExpr(input: Any): String = {
		escapeExpr(String valueOf input)
	}
	
	def escapeExpr(input: String): String = {
		val it = input.iterator
		val b = new StringBuilder

		while (it.hasNext) {
			val c = it.next

			//    \t Insert a tab in the text at this point.
			//    \b Insert a backspace in the text at this point.
			//    \n Insert a newline in the text at this point.
			//    \r Insert a carriage return in the text at this point.
			//    \f Insert a formfeed in the text at this point.
			//    \' Insert a single quote character in the text at this point.
			//    \" Insert a double quote character in the text at this point.
			//    \\ Insert a backslash character in the text at this point.

			c match {
				case '\t' => b.append('\\').append('t')
				case '\b' => b.append('\\').append('b')
				case '\n' => b.append('\\').append('n')
				case '\r' => b.append('\\').append('r')
				case '\f' => b.append('\\').append('f')
				case '\'' => b.append('\\').append(''')
				case '\"' => b.append('\\').append('"')
				case '\\' => b.append('\\').append('\\')

				case _ => b.append(c)
			}
		}

		b.toString
	}
}
