package com.cjmalloy.gameuidemo.tictactoe.client.ui.skin;

import com.cjmalloy.gameui.client.component.Panel;
import com.cjmalloy.gameuidemo.tictactoe.client.controller.TicTacToeController;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.component.*;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.view.TicTacToe;


public class TicTacToeLayoutImpl implements TicTacToeLayout {

  public static final int MIN_WIDTH = 200;
  public static final int MIN_HEIGHT = 300;

  private TicTacToe view;
  private Panel bgPanel;
  private Panel uiPanel;
  private Panel floatingPanel;

  private BoardBg boardbg;
  private Board board;
  private ToolButtons toolButtons;

  private TicTacToeController controller;

  @Override
  public Panel getBgLayer() {
    return bgPanel;
  }

  @Override
  public Panel getFloatingLayer() {
    return floatingPanel;
  }

  @Override
  public int getMinHeight() {
    return MIN_HEIGHT;
  }

  @Override
  public int getMinWidth() {
    return MIN_WIDTH;
  }

  @Override
  public Panel getUiLayer() {
    return uiPanel;
  }

  @Override
  public void init(TicTacToe view, int width, int height) {
    this.view = view;
    bgPanel = new Panel(0, 0, width, height);
    uiPanel = new Panel(0, 0, width, height);
    floatingPanel = new Panel(0, 0, width, height);

    bgPanel.add(getBoardBg(0, 0, width, height * 3 / 4));

    uiPanel.add(getBoard(0, 0, width, height * 3 / 4));
    uiPanel.add(getToolButtons(0, height * 3 / 4, width, height / 4));

    floatingPanel.addAll(toolButtons.getAnimations());
  }

  @Override
  public void resize(int w, int h) {
    bgPanel.resize(w, h);
    uiPanel.resize(w, h);
    floatingPanel.resize(w, h);
  }

  @Override
  public void setController(TicTacToeController c) {
    controller = c;
    board.setController(c);
    toolButtons.setController(c);
  }

  private BoardBg getBoardBg(int x, int y, int width, int height) {
    if (this.boardbg == null) {
      this.boardbg = new BoardBg(x, y, width, height);
    }
    return boardbg;
  }

  private Board getBoard(int x, int y, int width, int height) {
    if (this.board == null) {
      this.board = new Board(x, y, width, height);
    }
    return board;
  }

  private ToolButtons getToolButtons(int x, int y, int width, int height) {
    if (this.toolButtons == null) {
      this.toolButtons = new ToolButtons(x, y, width, height);
    }
    return toolButtons;
  }

}
