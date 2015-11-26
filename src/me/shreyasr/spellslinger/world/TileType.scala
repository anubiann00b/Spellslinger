package me.shreyasr.spellslinger.world

import java.awt.Color

import asciiPanel.AsciiPanel

case class TileType(glyph: Char, passable: Boolean, color: Color)

object TileType {
  val OUT_OF_MAP = TileType('X', passable = false, color = AsciiPanel.brightBlack)
  val WALL = TileType('#', passable = false, color = AsciiPanel.yellow)
  val FLOOR = TileType('.', passable = true, color = AsciiPanel.yellow)
}