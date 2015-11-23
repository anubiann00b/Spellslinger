package me.shreyasr.spellslinger.world

class Tile(val ttype: TileType) {

  def passable: Boolean = ttype.passable
}