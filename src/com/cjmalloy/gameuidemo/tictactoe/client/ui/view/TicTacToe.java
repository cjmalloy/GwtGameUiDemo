package com.cjmalloy.gameuidemo.tictactoe.client.ui.view;

import com.cjmalloy.gameui.client.core.RenderEngine;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.TicTacToeLayout;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.TicTacToeLayout.TicTacToeLayoutFactory;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;


public class TicTacToe extends RenderEngine implements ResizeHandler
{

    private TicTacToeLayout layout;
    private TicTacToeDocument doc;
    private TicTacToeController engine;

    public TicTacToe(int width, int height)
    {
        super(width, height);
        layout = TicTacToeLayoutFactory.get(this, width, height);
        doc = new TicTacToeDocument();
        engine = new TicTacToeController(doc);

        int bgLayer = addLayer();
        int uiLayer = addLayer();
        int floatingLayer = addLayer();

        addChild(bgLayer, layout.getBGLayer());
        addChild(uiLayer, layout.getUILayer());
        addChild(floatingLayer, layout.getFloatingLayer());

        layout.setController(engine);
        engine.update();
    }

    @Override
    public void onResize(ResizeEvent event)
    {
        int w = Math.max(event.getWidth(), layout.getMinWidth());
        int h = Math.max(event.getHeight(), layout.getMinHeight());
        resize(w, h);
        layout.resize(w, h);
    }

}
