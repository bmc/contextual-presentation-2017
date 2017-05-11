package com.ardentex.contextual

import _root_.contextual._

import scala.language.implicitConversions

object thing {

  case class Thing(s: String)

  sealed trait ThingContext extends Context
  final object InString extends ThingContext

  trait ThingInterpolator extends Interpolator {
    type ContextType = ThingContext
    type Input = String
    type Output = Thing
  }

  object ThingInterpolator extends ThingInterpolator {

    def contextualize(interpolation: StaticInterpolation): Seq[ContextType] = {
      interpolation.parts.map { _ => InString }
/*
        case lit @ Literal(i, s) =>
          InString

        case hole @ Hole(_, _) =>
          InString
      }
*/
    }

    def evaluate(interpolation: RuntimeInterpolation): Thing =
      Thing(interpolation.parts.mkString)
  }

  implicit class ThingStringContext(sc: StringContext) {
    val thing = Prefix(ThingInterpolator, sc)
  }

  import ThingInterpolator._

  // The following two defs (embedT and embedAnything) allow any type to
  // be embedded, via its toString method.
  private def embedT[T, C <: Context](cases: Case[(C, C), T, Input]*):
    Embedder[(C, C), T, Input, ThingInterpolator.type] =
    embed[T](cases: _*)

  implicit def embedAnything[T]:
    Embedder[(InString.type, InString.type), T, Input, ThingInterpolator.type] = {
    embedT[T, InString.type](
      Case(InString, InString) { o => o.toString }
    )
  }

}
