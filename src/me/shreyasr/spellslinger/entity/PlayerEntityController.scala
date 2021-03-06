package me.shreyasr.spellslinger.entity

import java.awt.event.KeyEvent
import java.util.concurrent.atomic.AtomicInteger

import com.badlogic.ashley.core.Entity
import me.shreyasr.spellslinger.action.{Action, MoveOutcomeBuilder}
import me.shreyasr.spellslinger.engine.Mappers
import me.shreyasr.spellslinger.world.World

import scala.collection.immutable.HashMap

class PlayerEntityController(world: World) extends EntityController(world) {

  val lastKey: AtomicInteger = new AtomicInteger(0)

  override def act(entity: Entity): Action = {
    val key = lastKey.getAndSet(0)

    if (Player.MOVEMENT_MAP.contains(key)) {
      Action(MoveOutcomeBuilder.create(world, Player.MOVEMENT_MAP.get(key).get, Mappers.PosMapper.get(entity)))
    } else {
      Action.WAIT
    }
  }

  def onKeyPress(e: KeyEvent) = {
    lastKey.set(e.getKeyCode)
  }
}

object Player {
  val MOVEMENT_MAP = HashMap[Int, MoveOutcomeBuilder.Dir](
    KeyEvent.VK_RIGHT -> MoveOutcomeBuilder.E,
    KeyEvent.VK_UP -> MoveOutcomeBuilder.N,
    KeyEvent.VK_LEFT -> MoveOutcomeBuilder.W,
    KeyEvent.VK_DOWN -> MoveOutcomeBuilder.S,
    KeyEvent.VK_Y -> MoveOutcomeBuilder.NW,
    KeyEvent.VK_U -> MoveOutcomeBuilder.NE,
    KeyEvent.VK_B -> MoveOutcomeBuilder.SW,
    KeyEvent.VK_N -> MoveOutcomeBuilder.SE,
    KeyEvent.VK_H -> MoveOutcomeBuilder.W,
    KeyEvent.VK_L -> MoveOutcomeBuilder.E,
    KeyEvent.VK_K -> MoveOutcomeBuilder.N,
    KeyEvent.VK_J -> MoveOutcomeBuilder.S,
    KeyEvent.VK_PERIOD -> MoveOutcomeBuilder.NONE
  )
}