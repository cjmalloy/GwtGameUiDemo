package com.cjmalloy.gameuidemo.tictactoe.client.event;

import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;
import com.google.web.bindery.event.shared.binder.GenericEvent;

/**
 * TicTacToeDocument update.
 */
public class UpdateEvent extends GenericEvent {

  public final TicTacToeDocument model;

  public UpdateEvent(TicTacToeDocument model) {
    this.model = model;
  }
}
