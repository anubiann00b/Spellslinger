package me.shreyasr.spellslinger.action

case class Action(outcomes: Outcome*)

object Action {
  object WAIT extends Action() {
    override def toString: String = "Action.WAIT"
  }
  val NONE = Action()
}
