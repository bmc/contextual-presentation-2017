package com.ardentex.contextual

import java.util.regex.PatternSyntaxException

import _root_.contextual._

import scala.util.matching.Regex

/** Adapted from contextual.examples. Differs from contextual version
  * as follows:
  *
  *  - Has context, so that it's possible to register an embedder (separately),
  *    which will trigger an error if substitutions are supported.
  *  - Returns scala.util.matching.Regex, instead of java.util.regex.Pattern
  */
object RegexCompileTime {

  object RegexParser extends Interpolator {

    type Input = String
    type Output = Regex

    def contextualize(interpolation: StaticInterpolation): Seq[ContextType] = {

      interpolation.parts.foreach {
        case lit@Literal(_, string) =>
          try {
            new Regex(string)
          }
          catch {
            case p: PatternSyntaxException =>
              // We take only the interesting part of the error message
              val message = p.getMessage.split(" near").head
              interpolation.error(lit, p.getIndex - 1, message)
          }

        case hole@Hole(_, _) =>
          interpolation.abort(hole, "substitution is not supported")
      }

      Nil
    }

    def evaluate(interpolation: RuntimeInterpolation): Regex =
      new Regex(interpolation.parts.mkString)
  }

  implicit class RegexStringContext(sc: StringContext) {
    val regex = Prefix(RegexParser, sc)
  }
}

