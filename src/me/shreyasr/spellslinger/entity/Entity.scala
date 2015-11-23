package me.shreyasr.spellslinger.entity

import me.shreyasr.spellslinger.GameState
import me.shreyasr.spellslinger.action.Action
import me.shreyasr.spellslinger.util.Pos

class Entity(var pos: Pos, val glyph: Char, val controller: EntityController) {

  def act(state: GameState): Action = controller.act(state, this)
}
