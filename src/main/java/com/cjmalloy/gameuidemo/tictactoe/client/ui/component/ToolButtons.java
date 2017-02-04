package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import com.cjmalloy.gameui.client.component.Button;
import com.cjmalloy.gameui.client.component.Panel;
import com.cjmalloy.gameui.client.core.UiElement;
import com.cjmalloy.gameui.client.event.MouseClickEvent;
import com.cjmalloy.gameui.client.event.MouseClickHandler;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.event.EventBusFactory;
import com.cjmalloy.gameuidemo.tictactoe.client.event.UpdateEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.ResetButtonSkin;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.binder.EventBinder;
import com.google.web.bindery.event.shared.binder.EventHandler;

import java.util.ArrayList;
import java.util.List;


public class ToolButtons extends Panel
{
    private final MyEventBinder eventBinder = GWT.create(MyEventBinder.class);
    interface MyEventBinder extends EventBinder<ToolButtons> {}

    private PieceDragButton xDragButton;
    private PieceDragButton oDragButton;
    private Button resetButton;
    private List<UiElement> animations = new ArrayList<UiElement>();

    private TicTacToeController controller;

    public ToolButtons(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        eventBinder.bindEventHandlers(this, EventBusFactory.get());

        add(xDragButton = new PieceDragButton(Piece.X));
        add(oDragButton = new PieceDragButton(Piece.O));
        add(resetButton = new Button());

        animations.add(xDragButton.getAnimationLayer());
        animations.add(oDragButton.getAnimationLayer());

        resetButton.cache = true;
        resetButton.setButtonSkin(new ResetButtonSkin());
        resetButton.addMouseClickHandler(new MouseClickHandler()
        {
            @Override
            public void onMouseClick(MouseClickEvent event)
            {
                onReset();
            }
        });

        xDragButton.cache = true;
        oDragButton.cache = true;

        resize(width, height);
    }

    @EventHandler
    public void update(UpdateEvent event)
    {
        xDragButton.setDraggable(event.model.winner == null);
        oDragButton.setDraggable(event.model.winner == null);
    }

    @Override
    public void resize(int w, int h)
    {
        super.resize(w, h);
        xDragButton.moveTo(0, 0);
        xDragButton.resize(w/3, h);
        oDragButton.moveTo(w*1/3, 0);
        oDragButton.resize(w/3, h);
        resetButton.moveTo(w*2/3, 0);
        resetButton.resize(w/3, h);
    }

    public List<UiElement> getAnimations()
    {
        return animations;
    }

    public void setController(TicTacToeController c)
    {
        this.controller = c;
    }

    protected void onReset()
    {
        controller.clear();
    }

    protected void update(TicTacToeDocument model)
    {
    }

}
