package me.shreyasr.spellslinger.action

import me.shreyasr.spellslinger.GameState
import me.shreyasr.spellslinger.action.anim.Animation

abstract class Outcome(val animations: List[Animation] = List()) {

  def commit(state: GameState): Unit
}
