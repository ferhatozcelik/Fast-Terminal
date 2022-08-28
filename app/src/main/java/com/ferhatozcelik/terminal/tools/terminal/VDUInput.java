package com.ferhatozcelik.terminal.tools.terminal;

import java.util.Properties;

public interface VDUInput {

  public final static int KEY_CONTROL = 0x01;
  public final static int KEY_SHIFT = 0x02;
  public final static int KEY_ALT = 0x04;
  public final static int KEY_ACTION = 0x08;



  /**
   * Direct access to writing data ...
   * @param b
   */
  void write(byte b[]);

  /**
   * Terminal is mouse-aware and requires (x,y) coordinates of
   * on the terminal (character coordinates) and the button clicked.
   * @param x
   * @param y
   * @param modifiers
   */
  void mousePressed(int x, int y, int modifiers);

  /**
   * Passes mouse wheel events to the terminal.
   * @param down True if scrolling down the page. False if scrolling up.
   * @param x
   * @param y
   * @param ctrl
   * @param shift
   * @param meta
   */
  public void mouseWheel(boolean down, int x, int y, boolean ctrl, boolean shift, boolean meta);

  /**
   * Terminal is mouse-aware and requires the coordinates and button
   * of the release.
   * @param x
   * @param y
   */
  void mouseReleased(int x, int y);

  /**
   * Override the standard key codes used by the terminal emulation.
   * @param codes a properties object containing key code definitions
   */
  void setKeyCodes(Properties codes);

  /**
   * main keytyping event handler...
   * @param keyCode the key code
   * @param keyChar the character represented by the key
   * @param modifiers shift/alt/control modifiers
   */
  void keyPressed(int keyCode, char keyChar, int modifiers);

  /**
   * Handle key Typed events for the terminal, this will get
   * all normal key types, but no shift/alt/control/numlock.
   * @param keyCode the key code
   * @param keyChar the character represented by the key
   * @param modifiers shift/alt/control modifiers
   */
  void keyTyped(int keyCode, char keyChar, int modifiers);
}
