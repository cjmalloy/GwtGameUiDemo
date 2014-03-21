package com.cjmalloy.gameuidemo.tictactoe.client.model;


public class BoardModel
{
    public Piece[][] grid = new Piece[3][3];

    public BoardModel()
    {
        clear();
    }

    public boolean boardFull()
    {
        for (int i=0; i<3; i++)
        for (int j=0; j<3; j++)
        {
            if (grid[i][j] != Piece.NONE) return false;
        }
        return true;
    }

    public void clear()
    {
        for (int i=0; i<3; i++)
        for (int j=0; j<3; j++)
        {
            grid[i][j] = Piece.NONE;
        }
    }

    public Piece getWinner()
    {
        if (isWinner(Piece.X)) return Piece.X;
        if (isWinner(Piece.O)) return Piece.O;
        return null;
    }

    public boolean isWinner(Piece p)
    {
        for (int i=0; i<3; i++)
        {
            if (grid[i][0] == p &&
                grid[i][1] == p &&
                grid[i][2] == p)
            {
                return true;
            }
            if (grid[0][i] == p &&
                grid[1][i] == p &&
                grid[2][i] == p)
            {
                return true;
            }
        }
        if (grid[0][0] == p &&
            grid[1][1] == p &&
            grid[2][2] == p)
        {
            return true;
        }
        if (grid[0][2] == p &&
            grid[1][1] == p &&
            grid[2][0] == p)
        {
            return true;
        }
        return false;
    }

    public static enum Piece
    {
        NONE,
        HIGHLIGHT,
        X,
        O;
    }
}
