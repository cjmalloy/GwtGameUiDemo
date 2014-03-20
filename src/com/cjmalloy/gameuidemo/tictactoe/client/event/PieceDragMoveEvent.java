package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameui.client.event.Event;
import com.cjmalloy.gameui.client.event.EventBus;
import com.cjmalloy.gameui.client.event.EventHandler;
import com.cjmalloy.gameui.client.event.EventType;


public class PieceDragMoveEvent extends PieceDragEvent
{
    public static final EventType<PieceDragMoveEvent> TYPE = new EventType<PieceDragMoveEvent>()
    {

        @Override
        public PieceDragMoveEvent createEvent()
        {
            return new PieceDragMoveEvent();
        }

        @Override
        public boolean equals(Event e)
        {
            return e instanceof PieceDragMoveEvent;
        }
    };

    @Override
    public void callHandler(EventHandler handler)
    {
        if (!(handler instanceof PieceDragMoveHandler)) { throw new InvalidEventHandlerError(); }

        if (EventBus.get().getCapture() == source || containsPoint())
        {
            PieceDragMoveHandler h = (PieceDragMoveHandler) handler;
            h.onDragMove(this);
        }
    }

    public EventType<? extends Event> getType()
    {
        return TYPE;
    }
}
