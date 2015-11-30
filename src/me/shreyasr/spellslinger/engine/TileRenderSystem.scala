package me.shreyasr.spellslinger.engine

import asciiPanel.AsciiPanel
import com.badlogic.ashley.core.EntitySystem
import me.shreyasr.spellslinger.world.World

class TileRenderSystem(terminal: AsciiPanel, world: World, paint: () => Unit) extends EntitySystem {

  override def update(deltaTime: Float): Unit = {
    paint()
  }
}
