package me.shreyasr.spellslinger.world

import com.badlogic.ashley.core
import com.badlogic.ashley.core.Entity
import me.shreyasr.spellslinger.engine.Components._
import me.shreyasr.spellslinger.entity.{EntityController, PlayerEntityController}
import me.shreyasr.spellslinger.util.Pos

object EntityFactory {

  def getPlayer(pos: Pos, playerEntityController: PlayerEntityController): Entity = {
    val playerEntity = getMonster(pos, '@', playerEntityController)
    playerEntity.add(new PlayerComponent())
  }

  def getMonster(pos: Pos, glyph: Char, entityController: EntityController): Entity = {
    val monster = getDumbMonster(pos, glyph)
    monster.add(new EntityControllerComponent(entityController))
  }

  private def getDumbMonster(pos: Pos, glyph: Char): Entity = {
    val entity = new core.Entity()
    entity.add(new PosComponent(pos))
    entity.add(new GlyphComponent(glyph))
    entity.add(new MonsterComponent())
  }
}
