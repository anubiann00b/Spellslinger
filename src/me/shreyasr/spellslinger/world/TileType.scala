package me.shreyasr.spellslinger.world

case class TileType(glyph: Char, passable: Boolean)

object TileType {
  val WALL = TileType('#', passable = false)
  val FLOOR = TileType('.', passable = true)
}