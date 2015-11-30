package me.shreyasr.spellslinger.action

import me.shreyasr.spellslinger.entity.Components.PosComponent
import me.shreyasr.spellslinger.world.World

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

  def create(world: World, dir: Dir, posComponent: PosComponent) = new Outcome() {
    override def commit(): Unit = {
      val destPos = posComponent.pos.add(dir.x, dir.y)
      if (world.getTile(destPos).passable) {
        posComponent.pos = destPos
      }
    }
  }
}
