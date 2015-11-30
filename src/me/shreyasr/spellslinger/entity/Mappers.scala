package me.shreyasr.spellslinger.entity

import com.badlogic.ashley.core.ComponentMapper
import me.shreyasr.spellslinger.entity.Components._

object Mappers {
  val MonsterMapper: ComponentMapper[MonsterComponent] = ComponentMapper.getFor(classOf[MonsterComponent])
  val PlayerMapper: ComponentMapper[PlayerComponent] = ComponentMapper.getFor(classOf[PlayerComponent])
  val PosMapper: ComponentMapper[PosComponent] = ComponentMapper.getFor(classOf[PosComponent])
  val GlyphMapper: ComponentMapper[GlyphComponent] = ComponentMapper.getFor(classOf[GlyphComponent])
  val EntityControllerMapper: ComponentMapper[EntityControllerComponent] = ComponentMapper.getFor(classOf[EntityControllerComponent])
}
