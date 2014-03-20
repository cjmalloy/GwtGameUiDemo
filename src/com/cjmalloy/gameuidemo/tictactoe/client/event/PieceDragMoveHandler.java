package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameui.client.event.EventHandler;


public interface PieceDragMoveHandler extends EventHandler
{

    void onDragMove(PieceDragMoveEvent event);
}
