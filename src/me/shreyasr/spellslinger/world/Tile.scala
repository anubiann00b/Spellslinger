package me.shreyasr.spellslinger.world

class Tile(val ttype: TileType) {

  def passable: Boolean = ttype.passable
}

object Tile {
  val OUT_OF_MAP: Tile = new Tile(TileType.OUT_OF_MAP)
}