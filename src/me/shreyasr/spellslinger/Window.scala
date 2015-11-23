package me.shreyasr.spellslinger

import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}
import javax.swing.JFrame

import asciiPanel.AsciiPanel

object Window extends JFrame() with KeyListener {

  def main(args: Array[String]): Unit = {
    Window.setVisible(true)
    engine.run()
  }

  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setBackground(Color.BLACK)
  setTitle("Spellslinger")

  val terminal = new AsciiPanel()
  add(terminal)
  pack()
  addKeyListener(this)

  val engine = new Engine(terminal, this.repaint)

  // Different thread
  override protected def keyPressed(e: KeyEvent): Unit = {
    engine.keyPress(e)
  }

  override protected def keyTyped(e: KeyEvent): Unit = ()

  override protected def keyReleased(e: KeyEvent): Unit = ()
}
