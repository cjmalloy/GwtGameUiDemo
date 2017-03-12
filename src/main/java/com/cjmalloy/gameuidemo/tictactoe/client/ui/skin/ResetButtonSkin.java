package com.cjmalloy.gameuidemo.tictactoe.client.ui.skin;

import com.cjmalloy.gameui.client.component.Button.ButtonState;
import com.cjmalloy.gameui.client.component.skin.ButtonSkin;
import com.cjmalloy.gameui.client.core.Renderer;
import com.google.gwt.canvas.dom.client.Context2d;


public class ResetButtonSkin extends ButtonSkin {

  public ResetButtonSkin() {
    final Renderer r = TileRenderer.get().getResetRenderer();
    states.put(ButtonState.UP, new Renderer() {
      @Override
      public void render(Context2d g, double timestamp) {
        g.save();
        {
          g.scale(width, height);
          r.render(g, timestamp);
        }
        g.restore();
      }
    });
  }
}
