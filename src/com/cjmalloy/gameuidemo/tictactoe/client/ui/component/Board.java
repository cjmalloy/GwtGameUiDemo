package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import com.cjmalloy.gameui.client.core.Renderer;
import com.cjmalloy.gameui.client.core.UiElement;
import com.cjmalloy.gameui.client.event.EventBus;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragEndEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragEndHandler;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragMoveEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragMoveHandler;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.TileRenderer;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;


public class Board extends UiElement implements PieceDragEndHandler, PieceDragMoveHandler
{
    private final TileRenderer skin = TileRenderer.get();

    private TicTacToeController controller;

    private Piece[][] grid = null;

    public Board(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        EventBus.get().addHandler(this, this, PieceDragEndEvent.TYPE, false);
        EventBus.get().addHandler(this, this, PieceDragMoveEvent.TYPE, false);
    }

    public void setController(TicTacToeController c)
    {
        this.controller = c;
        c.addValueChangeHandler(new ValueChangeHandler<TicTacToeDocument>()
        {
            @Override
            public void onValueChange(ValueChangeEvent<TicTacToeDocument> event)
            {
                update(event.getValue());
            }
        });
    }

    protected void update(TicTacToeDocument model)
    {
        grid = model.boardModel.grid;
        redrawNeeded();
    }

    @Override
    public void render(Context2d g, double timestamp)
    {
        if (grid == null) return;

        g.save();
        {
            g.scale(width/3., height/3.);
            for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
            {
                Renderer r = skin.getRenderer(grid[i][j]);
                if (r == null) continue;

                g.save();
                g.translate(i, j);
                r.render(g, timestamp);
                g.restore();
            }
        }
        g.restore();
    }

    @Override
    public void onDragEnd(PieceDragEndEvent event)
    {
        int x = (int) Math.floor(event.x*3/width);
        int y = (int) Math.floor(event.y*3/height);
        controller.movePiece(event.piece, x, y);
    }

    @Override
    public void onDragMove(PieceDragMoveEvent event)
    {
        // TODO Auto-generated method stub

    }

}
