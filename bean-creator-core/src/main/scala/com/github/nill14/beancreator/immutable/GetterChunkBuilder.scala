//package com.github.nill14.beancreator.trivialwriter
//
//import com.github.nill14.beancreator.builder.IChunkBuilder
//import com.github.nill14.beancreator.util.IndentWriter
//import com.github.nill14.beancreator.builder.BeanDescriptor
//import com.github.nill14.beancreator.builder.MethodDescriptor
//import com.github.nill14.beancreator.builder.FieldDescriptor
//import com.github.nill14.beancreator.util.Utils
//import com.github.nill14.beancreator.resolver.ImportResolver
//
//class GetterChunkBuilder extends IChunkBuilder {
//
//	def build(w: IndentWriter, bean: BeanDescriptor, chunk: Object, resolver: ImportResolver) {
//		val field = chunk.asInstanceOf[FieldDescriptor]
//		w.println 
//		
//		field.comment match {
//			case Some(comment) => 
//				w println s"/**"
//				w println s" * $comment"
//				w println s" * "
//				w println s" * @return $comment"
//				w println s" */"
//			case None =>
//		}			
//		
//		w prntInc s"public ${resolver.resolve(field.fieldType)} ${Utils.getter(field)}() {" 
//		w println s"return ${field.name};"
//		w decPrnt s"}"		
//	}
//	
//}