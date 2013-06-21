package com.github.nill14.beancreator.template

import java.util.regex.Pattern
import java.util.ArrayList
import scala.collection.mutable.MutableList

/*implicit */class LineInterpolator(val sc: StringContext)  {

//  {{{
// *   s"Hello, $name"
// * }}}
// *
// * is rewritten to be:
// *
// * {{{
// *   StringContext("Hello, ", "").s(name)
// * }}}
//    implicit def esc(sc : StringContext) = new EscapeInterpolator(sc)
  
	val newline = System.getProperty("line.separator")
	val escNewline = EscapeInterpolator.escapeExpr(newline)
	/**
	 * spaces followed by a newline
	 * 
	 * part (..|\n) is protection against "\n\r\n" occuring after variables - probably bug in Scala
	 */
	val spacesNewlinePattern = raw"[ \t\x0B\f]*(${escNewline}|\n)"
  
	type AnyFlagNext = (Array[String], FmtFlag, Array[String])
	type AppendFunction = StringBuilder => Unit

	def line(args: Any*): String = {
		val leadStr = sc.parts.head
		val strings = sc.parts.tail
		val expressions = args

		val firstLines = splitLines(stripLeadingNewline(leadStr)).reverse.toList
		val pairs = (expressions zip strings)
			.map { case (expr, next) => parseAnyFlagNext(expr, next) }

		val result = consumePairs(firstLines.tail)(firstLines.head :: Nil, pairs.toList)
		result.reverse.mkString(newline)
	}
	
	def consumePairs(result: List[String])(prefixLines: List[String], pairs: List[AnyFlagNext]): List[String] = pairs match {
		case (expLines, flag, suffixLines) :: xs => {
			
			suffixLines.toList match {
				case suffix :: Nil => {
					//single unterminated line
					val xlines = getExpr(prefixLines, flag, expLines, suffix)
					consumePairs(result)(xlines, xs)
				}
				case suffix :: ys => {
					//multiple suffix lines - expression terminated by newline
					val xlines = getExpr(prefixLines, flag, expLines, suffix)
					val zs = ys.reverse ::: (xlines.reverse ::: result)
					consumePairs(zs.tail)(zs.head :: Nil, xs)
				}
			}
		}
		case Nil => {
			prefixLines.reverse ::: result
		}
	}

	def getExpr(prefixLines: List[String], flag: FmtFlag, expLines: Array[String], suffix: String) : List[String] = flag match {
		case NewlineFlag => {
			for {
				prefix <- prefixLines
				expLine <- expLines
			} yield {
				prefix + expLine + suffix
			}
		}
		case _ => {
			for {
				prefix <- prefixLines
				expLine <- expLines
			} yield {
				prefix + expLine + suffix
			}
//			b append prefix
//				exprLines.addString(b, newline)
////				b append suffix
//				suffix(b)
		}
	}
	
	
	def stripLeadingNewline(input: String) = {
	  val m = Pattern.compile(raw"^($spacesNewlinePattern)").matcher(input)
	  
	  if (m.find()) {
	  	val len = m.group(1).length
	  	val res = input.substring(len)
	  	res
	    //input.substring(m.group(1).length)
	  }
	  else input
	}

	def splitLines(str: String) = {
		//\s 	A whitespace character: [ \t\n\x0B\f\r]
		//pattern raw"\s*\n" does not work as it consumes empty lines
		//str.split(spacesNewlinePattern)
		//str.split(escNewline)//.map(line => line.trimTrailing)
		
		val m = Pattern.compile(spacesNewlinePattern).matcher(str)
		val list = MutableList.newBuilder[String]
		var begin = 0;
		var end = 0;

		while (m.find) {
			end = m.start
			list += str.substring(begin, end)
			begin = m.end
		}
		list += str.substring(begin)

		list.result.toArray
	}

	def parseAnyFlagNext(expr: Any, next: String): AnyFlagNext = {
		val flag = FmtFlag.parseFromInput(next)
		val parsedExpr = parseExpr(expr)
		val rest = splitLines(next.substring(flag.length))
		(parsedExpr, flag, rest)
	}

	def parseExpr(arg: Any): Array[String] = arg match {
		case seq: Seq[_] => seq.map { e => raw"$e" }.toArray
		case any => splitLines(raw"$arg")
	}
	
}
