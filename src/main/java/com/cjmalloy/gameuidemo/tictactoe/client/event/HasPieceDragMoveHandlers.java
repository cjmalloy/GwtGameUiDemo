package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.google.gwt.event.shared.HandlerRegistration;


public interface HasPieceDragMoveHandlers {

  HandlerRegistration addPieceDragMoveHandler(PieceDragMoveHandler handler);
}
