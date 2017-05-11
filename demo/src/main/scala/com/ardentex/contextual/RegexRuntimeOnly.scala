package com.ardentex.contextual

import scala.util.matching.Regex

object RegexRuntimeOnly {

  implicit class RegexRuntimeOnly(val sc: StringContext) extends AnyVal {
    def regex(args: Any*): Regex = {
      val strings = sc.parts.iterator.toSeq
      val substitutions = args.iterator.map(_.toString).toSeq match {
        case s if s.size >= strings.length => s
        case s                             => s.padTo(strings.length, "")
      }

      val combined = strings zip substitutions map { case (prefix, value) =>
          prefix + value
      }

      combined.mkString.r
    }
  }
}
