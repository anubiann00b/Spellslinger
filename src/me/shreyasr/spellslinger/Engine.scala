package me.shreyasr.spellslinger

import java.awt.event.KeyEvent

import asciiPanel.AsciiPanel
import me.shreyasr.spellslinger.action.{Outcome, Action}
import me.shreyasr.spellslinger.entity.{Entity, PlayerEntityController}
import me.shreyasr.spellslinger.util.Pos
import me.shreyasr.spellslinger.world.World

class Engine(terminal: AsciiPanel, repaint: () => Unit) {

  val world = new World()
  val playerEntityController = new PlayerEntityController()
  var entities: List[Entity] = List(new Entity(new Pos(5,5), '@', playerEntityController))

  val state: GameState = GameState(world, entities)

  def run(): Unit = {
    while(true) {
      paint()
      entities.foreach(e => {
        updateEntity(e)
        paint()
      })
    }
  }

  def updateEntity(entity: Entity): Unit = {
    var action: Action = Action.WAIT
    while (action == Action.WAIT) {
      action = entity.act(state)
    }
    action.outcomes.foreach(doOutcome)
  }

  def doOutcome(outcome: Outcome): Unit = {
    outcome.commit(state)
    outcome.animations.foreach(_.frames.foreach(_.draw(terminal, paint)))
  }

  def keyPress(e: KeyEvent) = playerEntityController.onKeyPress(e)

  def paint(): Unit = {
    terminal.clear()
    for (i <- 0 to 79) {
      for (j <- 0 to 23) {
        val tile = state.world.tiles(i)(j)
        terminal.write(tile.ttype.glyph, i, j, tile.ttype.color)
      }
    }

    state.entities.foreach { e => terminal.write(e.glyph, e.pos.x, e.pos.y) }
    repaint()
  }
}
