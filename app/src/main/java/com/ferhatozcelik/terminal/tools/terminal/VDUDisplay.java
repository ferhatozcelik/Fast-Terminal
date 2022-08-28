package com.ferhatozcelik.terminal.tools.terminal;

public interface VDUDisplay {
  public void redraw();
  public void updateScrollBar();

  public void setVDUBuffer(VDUBuffer buffer);
  public VDUBuffer getVDUBuffer();
  
  public void setColor(int index, int red, int green, int blue);
  public void resetColors();
}
