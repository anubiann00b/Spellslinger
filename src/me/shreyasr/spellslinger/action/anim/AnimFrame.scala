package me.shreyasr.spellslinger.action.anim

import asciiPanel.AsciiPanel

abstract class AnimFrame(val millis:Int) {

  def draw(terminal: AsciiPanel, paint: () => Unit): Unit
}
