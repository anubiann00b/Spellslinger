package me.shreyasr.spellslinger.entity

import java.awt.event.KeyEvent

import me.shreyasr.spellslinger.GameState
import me.shreyasr.spellslinger.action.{Action, MoveOutcomeBuilder}

import scala.collection.immutable.HashMap

class PlayerEntityController extends EntityController {

  @volatile var lastKey = 0

  override def act(state: GameState, entity: Entity): Action = {
    val key = lastKey
    lastKey = 0

    if (Player.MOVEMENT_MAP.contains(key)) {
      Action(MoveOutcomeBuilder.create(Player.MOVEMENT_MAP.get(key).get, entity))
    } else {
      Action.WAIT
    }
  }

  def onKeyPress(e: KeyEvent) = {
    lastKey = e.getKeyCode
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
    KeyEvent.VK_J -> MoveOutcomeBuilder.S
  )
}