package me.shreyasr.spellslinger.action

case class Action(outcomes: Outcome*)

object Action {
  object WAIT extends Action(null)
}
