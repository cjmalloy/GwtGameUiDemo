package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import com.cjmalloy.gameui.client.core.UiElement;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.TileRenderer;
import com.google.gwt.canvas.dom.client.Context2d;


public class BoardBg extends UiElement {

  private final TileRenderer skin = TileRenderer.get();

  public BoardBg(int x, int y, int width, int height) {
    super(x, y, width, height);
  }

  @Override
  public void render(Context2d g, double timestamp) {
    redrawNeeded = false;
    g.save();
    {
      g.scale(width, height);
      skin.getGridRenderer().render(g, timestamp);
    }
    g.restore();
  }

}
