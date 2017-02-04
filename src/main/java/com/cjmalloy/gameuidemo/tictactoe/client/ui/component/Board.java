package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import com.cjmalloy.gameui.client.core.Renderer;
import com.cjmalloy.gameui.client.core.UiElement;
import com.cjmalloy.gameui.client.event.HasMouseMoveHandlers;
import com.cjmalloy.gameui.client.event.MouseMoveEvent;
import com.cjmalloy.gameui.client.event.MouseMoveHandler;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.event.*;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.TileRenderer;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;


public class Board extends UiElement implements HasPieceDragEndHandlers, PieceDragEndHandler,
                                                HasPieceDragMoveHandlers, PieceDragMoveHandler,
                                                HasMouseMoveHandlers, MouseMoveHandler
{
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
    interface MyEventBinder extends EventBinder<Board> {}

    private final TileRenderer skin = TileRenderer.get();

    private TicTacToeController controller;

    private Piece[][] grid = null;

    public Board(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        eventBinder.bindEventHandlers(this, EventBusFactory.get());

        addPieceDragEndHandler(this);
        addPieceDragMoveHandler(this);
        addMouseMoveHandler(this);
    }

    @EventHandler
    public void update(UpdateEvent event)
    {
        grid = event.model.boardModel.grid;
        redrawNeeded();
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler)
    {
        return addHandler(handler, MouseMoveEvent.TYPE, true);
    }

    @Override
    public HandlerRegistration addPieceDragEndHandler(PieceDragEndHandler handler)
    {
        return addHandler(handler, PieceDragEndEvent.TYPE);
    }

    @Override
    public HandlerRegistration addPieceDragMoveHandler(PieceDragMoveHandler handler)
    {
        return addHandler(handler, PieceDragMoveEvent.TYPE);
    }

    @Override
    public void onMouseMove(MouseMoveEvent event)
    {
        if (!event.containsPoint())
        {
            event.releaseCapture(this);
            removeHighlight();
            redrawNeeded();
        }
    }

    @Override
    public void onDragEnd(PieceDragEndEvent event)
    {
        event.releaseCapture(this);
        removeHighlight();
        redrawNeeded();

        int x = (int) Math.floor(event.x*3/width);
        int y = (int) Math.floor(event.y*3/height);
        controller.movePiece(event.piece, x, y);
    }

    @Override
    public void onDragMove(PieceDragMoveEvent event)
    {
        event.setCapture(this);
        int x = (int) Math.floor(event.x*3/width);
        int y = (int) Math.floor(event.y*3/height);
        if (x < 0 || x > 2 || y < 0 || y > 2)
        {
            removeHighlight();
            return;
        }
        if (grid[x][y] == Piece.NONE)
        {
            removeHighlight();
            grid[x][y] = Piece.HIGHLIGHT;
            redrawNeeded();
        }
    }

    @Override
    public void render(Context2d g, double timestamp)
    {
        if (grid == null) return;
        redrawNeeded = false;

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

    public void setController(TicTacToeController c)
    {
        this.controller = c;
    }

    private void removeHighlight()
    {
        for (int i=0; i<3; i++)
        for (int j=0; j<3; j++)
        {
            if (grid[i][j] == Piece.HIGHLIGHT) grid[i][j] = Piece.NONE;
        }
    }

}
