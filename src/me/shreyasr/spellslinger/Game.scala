package me.shreyasr.spellslinger

import java.awt.event.KeyEvent

import asciiPanel.AsciiPanel
import com.badlogic.ashley.core.{Engine, Entity, Family}
import com.badlogic.ashley.utils.ImmutableArray
import me.shreyasr.spellslinger.engine.Components._
import me.shreyasr.spellslinger.engine.{EntityUpdateSystem, Mappers}
import me.shreyasr.spellslinger.entity.{DumbEntityController, PlayerEntityController}
import me.shreyasr.spellslinger.util.Pos
import me.shreyasr.spellslinger.world.{EntityFactory, World}

import scala.collection.JavaConversions._

class Game(terminal: AsciiPanel, repaint: () => Unit) {

  val engine = new Engine()
  val world = new World()
  val playerEntityController = new PlayerEntityController(world)

  engine.addEntity(EntityFactory.getPlayer(Pos(5,5), playerEntityController))
  engine.addEntity(EntityFactory.getMonster(Pos(10,10), 'o', new DumbEntityController(world)))

  engine.addSystem(new EntityUpdateSystem(terminal, paint))

  val entities: ImmutableArray[Entity] = engine.getEntitiesFor(Family.all(classOf[MonsterComponent]).get())

  def run(): Unit = {
    paint()
    while(true) {
      engine.update(1)
      Thread.sleep(10)
    }
  }

  def keyPress(e: KeyEvent) = playerEntityController.onKeyPress(e)

  def paint(): Unit = {
    terminal.clear()
    for (i <- 0 to 79) {
      for (j <- 0 to 23) {
        val tile = world.tiles(i)(j)
        terminal.write(tile.ttype.glyph, i, j, tile.ttype.color)
      }
    }

    for (e: Entity <- entities) {
      val pos = Mappers.PosMapper.get(e).pos
      terminal.write(Mappers.GlyphMapper.get(e).glyph, pos.x, pos.y)
    }
    repaint()
  }
}
