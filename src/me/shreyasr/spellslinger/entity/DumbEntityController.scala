package me.shreyasr.spellslinger.entity

import com.badlogic.ashley.core.Entity
import me.shreyasr.spellslinger.action.MoveOutcomeBuilder.Dir
import me.shreyasr.spellslinger.action.{MoveOutcomeBuilder, Action}
import me.shreyasr.spellslinger.engine.Mappers
import me.shreyasr.spellslinger.world.World

import scala.util.Random

class DumbEntityController(world: World) extends EntityController(world) {

  val rnd: Random = new Random()

  override def act(entity: Entity): Action = {
    Action(MoveOutcomeBuilder.create(world, Dir(rnd.nextInt(3)-1, rnd.nextInt(3)-1), Mappers.PosMapper.get(entity)))
  }
}
