package me.shreyasr.spellslinger.action.anim

case class Animation(frames:List[AnimFrame])

object Animation {
  val NONE = Animation(List())
}