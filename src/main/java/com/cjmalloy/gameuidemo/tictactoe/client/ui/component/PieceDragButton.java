package com.cjmalloy.gameuidemo.tictactoe.client.ui.component;

import com.cjmalloy.gameui.client.component.DragButton;
import com.cjmalloy.gameui.client.event.Event;
import com.cjmalloy.gameui.client.event.MouseEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragEndEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.event.PieceDragMoveEvent;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.ui.skin.PieceDragButtonSkin;


public class PieceDragButton extends DragButton {

  public Piece piece = null;

  public PieceDragButton(Piece p) {
    this.piece = p;
    setButtonSkin(new PieceDragButtonSkin(p));
  }

  protected Event getDragEndEvent(MouseEvent event) {
    PieceDragEndEvent e = new PieceDragEndEvent();
    e.x = event.x;
    e.y = event.y;
    e.dragSource = this;
    e.piece = piece;
    return e;
  }

  protected Event getDragMoveEvent(MouseEvent event) {
    PieceDragMoveEvent e = new PieceDragMoveEvent();
    e.x = event.x;
    e.y = event.y;
    e.dragSource = this;
    e.piece = piece;
    return e;
  }
}
