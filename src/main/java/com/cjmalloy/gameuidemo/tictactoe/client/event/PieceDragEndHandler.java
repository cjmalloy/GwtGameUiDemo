package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameui.client.event.EventHandler;


public interface PieceDragEndHandler extends EventHandler {

  void onDragEnd(PieceDragEndEvent event);
}
