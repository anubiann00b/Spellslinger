package me.shreyasr.spellslinger

import java.awt.event.KeyEvent

import asciiPanel.AsciiPanel
import me.shreyasr.spellslinger.action.Action
import me.shreyasr.spellslinger.entity.{Entity, PlayerEntityController}
import me.shreyasr.spellslinger.util.Pos
import me.shreyasr.spellslinger.world.World

class Engine(terminal: AsciiPanel, repaint: () => Unit) {

  val world = new World()
  val playerEntityController = new PlayerEntityController()
  var entities: List[Entity] = List(new Entity(new Pos(5,5), '@', playerEntityController))

  val state: GameState = GameState(world, entities)

  def run(): Unit = {
    paint()
    while(true) {
      Thread.sleep(50)
      for (entity <- entities) {
        val action = entity.act(state)
        if (action == Action.WAIT) {
          // TODO: actually wait
        } else {
          for (outcome <- action.outcomes) {
            outcome.commit(state)
            for (anim <- outcome.animations) {
              for (animFrame <- anim.frames) {
                animFrame.draw(terminal)
                paint()
              }
            }
            paint()
          }
        }
      }
    }
  }

  def keyPress(e: KeyEvent) = playerEntityController.onKeyPress(e)

  def paint(): Unit = {
    repaint()
    terminal.clear()
    for (i <- 0 to 79) {
      for (j <- 0 to 23) {
        terminal.write(state.world.tiles(i)(j).ttype.glyph, i, j)
      }
    }

    for (entity <- state.entities) {
      terminal.write(entity.glyph, entity.pos.x, entity.pos.y)
    }
  }
}
