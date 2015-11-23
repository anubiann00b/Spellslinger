package me.shreyasr.spellslinger.entity

import me.shreyasr.spellslinger.GameState
import me.shreyasr.spellslinger.action.Action

abstract class EntityController {

  def act(state: GameState, entity: Entity): Action
}
