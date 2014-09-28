package com.cjmalloy.gameuidemo.tictactoe.client.controller;

import com.cjmalloy.gameui.client.core.EventDispatcher;
import com.cjmalloy.gameuidemo.tictactoe.client.model.BoardModel.Piece;
import com.cjmalloy.gameuidemo.tictactoe.client.model.document.TicTacToeDocument;


public class TicTacToeController extends EventDispatcher<TicTacToeDocument>
{
    TicTacToeDocument doc;

    public TicTacToeController(TicTacToeDocument d)
    {
        this.doc = d;
    }

    public void movePiece(Piece p, int x, int y)
    {
        if (!doc.movePiece(p, x, y)) return;
        doc.nextTurn();
        Piece winner = doc.getWinner();
        if (winner != null)
        {
            doc.winner = winner;
        }
        update();
    }

    public void update()
    {
        update(doc);
    }

    public void clear()
    {
        doc.clear();
        update();
    }
}
