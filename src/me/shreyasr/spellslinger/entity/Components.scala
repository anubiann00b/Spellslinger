package me.shreyasr.spellslinger.entity

import com.badlogic.ashley.core.Component
import me.shreyasr.spellslinger.util.Pos

object Components {
  case class MonsterComponent() extends Component
  case class PlayerComponent() extends Component
  case class PosComponent(var pos: Pos) extends Component
  case class GlyphComponent(glyph: Char) extends Component
  case class EntityControllerComponent(controller: EntityController) extends Component
}