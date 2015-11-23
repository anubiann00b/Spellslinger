package me.shreyasr.spellslinger.action

import me.shreyasr.spellslinger.GameState
import me.shreyasr.spellslinger.entity.Entity
import me.shreyasr.spellslinger.util.Pos

object MoveOutcomeBuilder {

  case class Dir(x: Int, y: Int)
  object NW extends Dir(-1, -1)
  object N extends Dir(0, -1)
  object NE extends Dir(1, -1)
  object W extends Dir(-1, 0)
  object NONE extends Dir(0, 0)
  object E extends Dir(1, 0)
  object SW extends Dir(-1, 1)
  object S extends Dir(0, 1)
  object SE extends Dir(1, 1)

  def create(dir: Dir, entity: Entity) = new Outcome() {
    override def commit(state: GameState): Unit = {
      val destPos = Pos(entity.pos.x+dir.x, entity.pos.y+dir.y)
      if (state.world.getTile(destPos).passable) {
        entity.pos = destPos
      }
    }
  }
}
