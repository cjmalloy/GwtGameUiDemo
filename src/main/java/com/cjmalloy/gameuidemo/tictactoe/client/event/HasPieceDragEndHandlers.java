package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.google.gwt.event.shared.HandlerRegistration;


public interface HasPieceDragEndHandlers
{

    HandlerRegistration addPieceDragEndHandler(PieceDragEndHandler handler);
}
