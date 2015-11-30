package me.shreyasr.spellslinger

import java.awt.event.KeyEvent

import asciiPanel.AsciiPanel
import com.badlogic.ashley.core
import com.badlogic.ashley.core.{Engine, Entity, Family}
import com.badlogic.ashley.utils.ImmutableArray
import me.shreyasr.spellslinger.engine.{TileRenderSystem, EntityUpdateSystem}
import me.shreyasr.spellslinger.entity.Components._
import me.shreyasr.spellslinger.entity.{Mappers, PlayerEntityController}
import me.shreyasr.spellslinger.util.Pos
import me.shreyasr.spellslinger.world.World

import scala.collection.JavaConversions._

class Game(terminal: AsciiPanel, repaint: () => Unit) {

  val world = new World()
  val playerEntityController = new PlayerEntityController(world)

  val engine = new Engine()

  val playerEntity = new core.Entity()
  playerEntity.add(new PosComponent(new Pos(5,5)))
  playerEntity.add(new GlyphComponent('@'))
  playerEntity.add(new EntityControllerComponent(playerEntityController))
  playerEntity.add(new MonsterComponent())
  playerEntity.add(new PlayerComponent())
  engine.addEntity(playerEntity)

  engine.addSystem(new TileRenderSystem(terminal, world, paint))
  engine.addSystem(new EntityUpdateSystem(terminal, paint))

  val entities: ImmutableArray[Entity] = engine.getEntitiesFor(Family.all(classOf[MonsterComponent]).get())

  def run(): Unit = {
    while(true) {
      engine.update(1)
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
