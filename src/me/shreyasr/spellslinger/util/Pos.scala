package me.shreyasr.spellslinger.util

case class Pos(x: Int, y: Int) {

  def add(x: Int, y: Int) = Pos(this.x+x, this.y+y)
}