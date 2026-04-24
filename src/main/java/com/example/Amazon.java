package com.example;

import java.awt.Graphics;
import java.util.ArrayList;

public class Amazon extends Piece {

    public Amazon(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }



    @Override
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {

        ArrayList<Square> control = new ArrayList<>();

        int r = start.getRow();
        int c = start.getCol();

        int[][] knight = {
            {-2,-1},{-2,1},{-1,-2},{-1,2},
            {1,-2},{1,2},{2,-1},{2,1}
        };

        for (int[] k : knight) {
            int rr = r + k[0];
            int cc = c + k[1];

            if (rr >= 0 && rr < 8 && cc >= 0 && cc < 8) {
                control.add(board[rr][cc]);
            }
        }

        int[][] dirs = {
            {1,0},{-1,0},{0,1},{0,-1},
            {1,1},{1,-1},{-1,1},{-1,-1}
        };

        for (int[] d : dirs) {
            int rr = r + d[0];
            int cc = c + d[1];

            while (rr >= 0 && rr < 8 && cc >= 0 && cc < 8) {
                control.add(board[rr][cc]);
                if (board[rr][cc].isOccupied()) break;
                rr += d[0];
                cc += d[1];
            }
        }

        return control;
    }

    @Override
    public ArrayList<Square> getLegalMoves(Board b, Square start) {

        ArrayList<Square> moves = new ArrayList<>();
        Square[][] board = b.getSquareArray();

        int r = start.getRow();
        int c = start.getCol();

        int[][] knight = {
            {-2,-1},{-2,1},{-1,-2},{-1,2},
            {1,-2},{1,2},{2,-1},{2,1}
        };

        for (int[] k : knight) {
            int rr = r + k[0];
            int cc = c + k[1];

            if (rr >= 0 && rr < 8 && cc >= 0 && cc < 8) {
                Square sq = board[rr][cc];

                if (!sq.isOccupied() ||
                    sq.getOccupyingPiece().getColor() != this.getColor()) {
                    moves.add(sq);
                }
            }
        }

        int[][] dirs = {
            {1,0},{-1,0},{0,1},{0,-1},
            {1,1},{1,-1},{-1,1},{-1,-1}
        };

        for (int[] d : dirs) {
            int rr = r + d[0];
            int cc = c + d[1];

            while (rr >= 0 && rr < 8 && cc >= 0 && cc < 8) {

                Square sq = board[rr][cc];

                if (!sq.isOccupied()) {
                    moves.add(sq);
                } else {
                    if (sq.getOccupyingPiece().getColor() != this.getColor()) {
                        moves.add(sq);
                    }
                    break;
                }

                rr += d[0];
                cc += d[1];
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return this.getColor() ? "White Amazon" : "Black Amazon";
    }
}