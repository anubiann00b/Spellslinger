package me.shreyasr.spellslinger.entity

import com.badlogic.ashley.core.Entity
import me.shreyasr.spellslinger.action.Action
import me.shreyasr.spellslinger.world.World

abstract class EntityController(val world: World) {

  def act(entity: Entity): Action
}
