package com.cjmalloy.gameuidemo.tictactoe.client.ui.skin;

import com.cjmalloy.gameui.client.component.DragButton.DragButtonState;
import com.cjmalloy.gameui.client.component.skin.DragButtonSkin;
import com.cjmalloy.gameui.client.core.Renderer;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.google.gwt.canvas.dom.client.Context2d;


public class PieceDragButtonSkin extends DragButtonSkin
{
    public PieceDragButtonSkin(Piece p)
    {
        final Renderer r = TileRenderer.get().getRenderer(p);
        states.put(DragButtonState.UP, new Renderer()
        {
            @Override
            public void render(Context2d g, double timestamp)
            {
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
