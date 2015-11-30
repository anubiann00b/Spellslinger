package me.shreyasr.spellslinger.engine

import asciiPanel.AsciiPanel
import com.badlogic.ashley.core.{Entity, Family}
import com.badlogic.ashley.systems.IteratingSystem
import me.shreyasr.spellslinger.action.{Outcome, Action}
import me.shreyasr.spellslinger.entity.Components.{EntityControllerComponent, MonsterComponent}

class EntityUpdateSystem(terminal: AsciiPanel, paint: () => Unit)
  extends IteratingSystem(Family.all(classOf[MonsterComponent]).get()) {

  override def processEntity(entity: Entity, deltaTime: Float): Unit = {
    val entityControllerComponent = entity.getComponent(classOf[EntityControllerComponent])

    var action: Action = Action.WAIT
    while (action == Action.WAIT) {
      action = entityControllerComponent.controller.act(entity)
    }
    action.outcomes.foreach(doOutcome)
    paint()
  }

  def doOutcome(outcome: Outcome): Unit = {
    outcome.commit()
    outcome.animations.foreach(_.frames.foreach(_.draw(terminal, paint)))
  }
}
