package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameui.client.event.*;


public class PieceDragEndEvent extends PieceDragEvent {

  public static final EventType<PieceDragEndEvent> TYPE = new EventType<PieceDragEndEvent>() {
    @Override
    public PieceDragEndEvent createEvent() {
      return new PieceDragEndEvent();
    }

    @Override
    public boolean equals(Event e) {
      return e instanceof PieceDragEndEvent;
    }
  };

  @Override
  public void callHandler(EventHandler handler) {
    if (!(handler instanceof PieceDragEndHandler)) {
      throw new InvalidEventHandlerError();
    }

    if (EventBus.get().getCapture() == source || containsPoint()) {
      PieceDragEndHandler h = (PieceDragEndHandler) handler;
      h.onDragEnd(this);
    }
  }

  public EventType<? extends Event> getType() {
    return TYPE;
  }
}
