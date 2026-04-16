package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Chequer extends Piece{
    
    
    public Chequer(boolean isWhite, String img_file) {
        super(isWhite, img_file);
         
        
    }
    
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    
    //precondition: the piece is not null and the square is on the board
    //postcondition: returns an arraylist of squares that the piece controls
    //  to show what the enemy king would be checked from (highlighted in blue)
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
     ArrayList<Square> controlled = new ArrayList<Square>();

        //this moves 2 squares left, right, up, or down
        if(start.getCol() + 2 < 8){
            controlled.add(board[start.getRow()][start.getCol()+2]);
        }
        if(start.getCol() -2 >= 0){
            controlled.add(board[start.getRow()][start.getCol()-2]);
        }
        if (start.getRow() -2 >= 0){
            controlled.add(board[start.getRow()-2][start.getCol()]);
        }
        if(start.getRow() + 2 < 8){
            controlled.add(board[start.getRow()+2][start.getCol()]);
        }

        //diagonal moves
        if(start.getCol() + 1 < 8 && start.getRow() + 1 < 8){
            controlled.add(board[start.getRow()+1][start.getCol()+1]);
        }
        if(start.getCol() + 1 < 8 && start.getRow() -1 >= 0){
            controlled.add(board[start.getRow()-1][start.getCol()+1]);
        }
        if(start.getCol() - 1 >= 0 && start.getRow() + 1 < 8){
            controlled.add(board[start.getRow()+1][start.getCol()-1]);
        }
        if(start.getCol() - 1 >= 0 && start.getRow() - 1 >= 0){
            controlled.add(board[start.getRow()-1][start.getCol()-1]);
        }
    return controlled;
}
        
    
    
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    

    //My piece moves diagonally in any direction, but only for one square. Or, it can move 2 squares left, right, or up and down. 
    //This creates almost a diamond shape of moves that match the color that the piece starts on and cant be changed.

    //precondition: the piece is not null and the square is on the board
    //postcondition: returns an arraylist of squares that the piece can move to based on the rules
    //that it can't move to a same color piece and it can't move off the board.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> moves = new ArrayList<Square>();

        //this moves 2 squares left, right, up, or down
        if(start.getCol() + 2 < 8){
            moves.add(b.getSquareArray()[start.getRow()][start.getCol()+2]);
        }
        if(start.getCol() -2 >= 0){
            moves.add(b.getSquareArray()[start.getRow()][start.getCol()-2]);
        }
        if (start.getRow() -2 >= 0){
            moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()]);
        }
        if(start.getRow() + 2 < 8){
            moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()]);
        }

        //diagonal moves
        if(start.getCol() + 1 < 8 && start.getRow() + 1 < 8){
            moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()+1]);
        }
        if(start.getCol() + 1 < 8 && start.getRow() -1 >= 0){
            moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()+1]);
        }
        if(start.getCol() - 1 >= 0 && start.getRow() + 1 < 8){
            moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()-1]);
        }
        if(start.getCol() - 1 >= 0 && start.getRow() - 1 >= 0){
            moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()-1]);
        }

    for(int i = 0; i < moves.size(); i++){
        Square s = moves.get(i);
        if(s.isOccupied() && s.getOccupyingPiece().getColor() == this.color){
            moves.remove(i);
            i--;
    }
}
        return moves;
    }
    

}
