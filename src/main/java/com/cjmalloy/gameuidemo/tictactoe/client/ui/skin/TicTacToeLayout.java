package com.cjmalloy.gameuidemo.tictactoe.client.ui.skin;

import com.cjmalloy.gameui.client.component.Panel;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.view.TicTacToe;
import com.google.gwt.core.client.GWT;


public interface TicTacToeLayout {

  Panel getBgLayer();

  Panel getFloatingLayer();

  int getMinHeight();

  int getMinWidth();

  Panel getUiLayer();

  void init(TicTacToe view, int width, int height);

  void resize(int w, int h);

  void setController(TicTacToeController c);

  public static class TicTacToeLayoutFactory {

    public static TicTacToeLayout get(TicTacToe view, int width, int height) {
      TicTacToeLayout impl = GWT.create(TicTacToeLayout.class);
      impl.init(view, width, height);
      return impl;
    }
  }

}
