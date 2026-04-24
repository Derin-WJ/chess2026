//Attila Juhasz

//Rook
//A rook can move vertically and horizontally. It can't jump over pieces. It is worth 5 points.

package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Rook extends Piece{
    
    public Rook(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }


    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.

    //Precondition: the piece is currently on the "start" square, start is not null, and it is on the board.
    //Postcondition: returns an arraylist of squares that this piece controlls on the board.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> moves = new ArrayList<Square>();

        for (int col = start.getCol()+1; col < 8; col++){
            if (board[start.getRow()][col].getOccupyingPiece() == null || (board[start.getRow()][col].getOccupyingPiece().getColor() != this.getColor())){
                moves.add(board[start.getRow()][col]);
                if (board[start.getRow()][col].getOccupyingPiece() != null){
                    col +=100;
                }
            }
        }
        for (int col = start.getCol()-1; col >= 0; col--){
            if (board[start.getRow()][col].getOccupyingPiece() == null || (board[start.getRow()][col].getOccupyingPiece().getColor() != this.getColor())){
                moves.add(board[start.getRow()][col]);
                if (board[start.getRow()][col].getOccupyingPiece() != null){
                    col = -1;
                }
            }
        }

        for (int row = start.getRow()+1; row < 8; row++){
            if ((board[row][start.getCol()].getOccupyingPiece() == null) || (board[row][start.getCol()].getOccupyingPiece().getColor() != this.getColor())){
                moves.add(board[row][start.getCol()]);
                if (board[row][start.getCol()].getOccupyingPiece() != null){
                    row = 100;
                }
            }
        }

        for (int row = start.getRow()-1; row >= 0; row--){
            if ((board[row][start.getCol()].getOccupyingPiece() == null) || (board[row][start.getCol()].getOccupyingPiece().getColor() != this.getColor())){
                moves.add(board[row][start.getCol()]);
                if (board[row][start.getCol()].getOccupyingPiece() != null){
                    row = -1;
                }
            }
        }

        

        return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

    //Precondition: the piece is currently on the "start" square, start is not null, and it is on the board.
    //Postcondition: returns an arraylist of squares that this piece could move to legally.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        //the board that I'm in
        //b.getSquareArray();

        //your location in this board is start.getRow() and start.getCol()
        

        ArrayList<Square> moves = new ArrayList<Square>();

        for (int col = start.getCol()+1; col < 8; col++){
            moves.add(b.getSquareArray()[start.getRow()][col]);
            if (b.getSquareArray()[start.getRow()][col].getOccupyingPiece() != null){
                col +=100;
            }
        }
        for (int col = start.getCol()-1; col >= 0; col--){
            moves.add(b.getSquareArray()[start.getRow()][col]);
            if (b.getSquareArray()[start.getRow()][col].getOccupyingPiece() != null){
                col = -1;
            }
        }

        for (int row = start.getRow()+1; row < 8; row++){
            moves.add(b.getSquareArray()[row][start.getCol()]);
            if (b.getSquareArray()[row][start.getCol()].getOccupyingPiece() != null){
                row = 100;
            }
        }

        for (int row = start.getRow()-1; row >= 0; row--){
            moves.add(b.getSquareArray()[row][start.getCol()]);
            if (b.getSquareArray()[row][start.getCol()].getOccupyingPiece() != null){
                row = -1;
            }
        }

       

        

        return moves;
    }
    
    //Pre-condition: Rook is created and color is not null
    //Post-condition: Returns what the piece is along with its color
    public String toString() {
        if (color)
            return "white rook";
        else
            return "black rook";
    }
}