package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameui.client.event.DragEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;


public abstract class PieceDragEvent extends DragEvent
{
    public Piece piece = null;
}
