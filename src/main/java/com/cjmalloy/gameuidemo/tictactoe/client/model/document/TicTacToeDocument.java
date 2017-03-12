package com.cjmalloy.gameuidemo.tictactoe.client.model.document;

import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;


public class TicTacToeDocument {

  public BoardModel boardModel = new BoardModel();
  public Piece turn = Piece.X;

  public Piece winner = null;

  public void clear() {
    boardModel.clear();
    turn = Piece.X;
    winner = null;
  }

  /**
   * Check if there is a winner.
   *
   * @return the winner, null if the game is still in progress and NONE if there is a tie.
   */
  public Piece getWinner() {
    Piece p;
    p = boardModel.getWinner();
    if (p != null) {
      return p;
    }

    if (boardModel.boardFull()) {
      return Piece.NONE;
    }
    return null;
  }

  public boolean movePiece(Piece p, int x, int y) {
    if (turn != p) {
      return false;
    }
    if (x < 0 || x > 2 || y < 0 || y > 2) {
      return false;
    }
    if (boardModel.grid[x][y] != Piece.NONE) {
      return false;
    }

    boardModel.grid[x][y] = p;
    return true;
  }

  public void nextTurn() {
    turn = (turn == Piece.X) ? Piece.O : Piece.X;
  }
}
