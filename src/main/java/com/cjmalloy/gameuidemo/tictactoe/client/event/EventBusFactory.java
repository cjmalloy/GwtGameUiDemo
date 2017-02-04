package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class EventBusFactory
{
    public static final EventBus EVENT_BUS = GWT.create(SimpleEventBus.class);

    public static EventBus get()
    {
        return EVENT_BUS;
    }
}
