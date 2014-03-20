package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import java.util.ArrayList;
import java.util.List;

import com.cjmalloy.gameui.client.component.Button;
import com.cjmalloy.gameui.client.component.Panel;
import com.cjmalloy.gameui.client.core.UiElement;
import com.cjmalloy.gameui.client.event.MouseClickEvent;
import com.cjmalloy.gameui.client.event.MouseClickHandler;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.ResetButtonSkin;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;


public class ToolButtons extends Panel
{

    private PieceDragButton xDragButton;
    private PieceDragButton oDragButton;
    private Button resetButton;
    private List<UiElement> animations = new ArrayList<UiElement>();

    private TicTacToeController controller;

    public ToolButtons(int x, int y, int width, int height)
    {
        super(x, y, width, height);

        add(xDragButton = new PieceDragButton(Piece.X));
        add(oDragButton = new PieceDragButton(Piece.O));
        add(resetButton = new Button());

        animations.add(xDragButton.getAnimationLayer());
        animations.add(oDragButton.getAnimationLayer());

        resetButton.setButtonSkin(new ResetButtonSkin());
        resetButton.addMouseClickHandler(new MouseClickHandler()
        {
            @Override
            public void onMouseClick(MouseClickEvent event)
            {
                onReset();
            }
        });

        resize(width, height);
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
        c.addValueChangeHandler(new ValueChangeHandler<TicTacToeDocument>()
        {
            @Override
            public void onValueChange(ValueChangeEvent<TicTacToeDocument> event)
            {
                update(event.getValue());
            }
        });
    }

    protected void onReset()
    {
        controller.clear();
    }

    protected void update(TicTacToeDocument model)
    {
        xDragButton.setDraggable(model.winner == null);
        oDragButton.setDraggable(model.winner == null);
    }

}
