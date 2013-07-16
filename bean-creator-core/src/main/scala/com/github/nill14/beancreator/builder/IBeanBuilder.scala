package com.github.nill14.beancreator.builder

import java.io.Writer
import com.github.nill14.beancreator.model.IBean

trait IBeanBuilder {

	def build(writer: Writer, bean: IBean, ctx: IBuilderContext)
}