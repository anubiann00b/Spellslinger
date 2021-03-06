package me.shreyasr.spellslinger.world

import me.shreyasr.spellslinger.util.Pos

import scala.util.Random

class World {

  val tiles = Array.ofDim[Tile](80,24)
  shuffle()

  def getTile(destPos: Pos): Tile = {
    if (tiles.indices.contains(destPos.x) && tiles(destPos.x).indices.contains(destPos.y)) {
      tiles(destPos.x)(destPos.y)
    } else {
      Tile.OUT_OF_MAP
    }
  }

  def shuffle(): Unit = {
    for (i <- 1 to 80) {
      for (j <- 1 to 24) {
        tiles(i-1)(j-1) = new Tile(if (Random.nextDouble() < 0.9) TileType.FLOOR else TileType.WALL)
      }
    }
  }
}
