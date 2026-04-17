package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.net.URL;
import javax.swing.border.Border;
import java.awt.Toolkit;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String path = "/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = path+"wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = path+"bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = path+"wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = path+"bknight.png";
	private static final String RESOURCES_WROOK_PNG = path+"wrook.png";
	private static final String RESOURCES_BROOK_PNG = path+"brook.png";
	private static final String RESOURCES_WKING_PNG = path+"wking.png";
	private static final String RESOURCES_BKING_PNG = path+"bking.png";
	private static final String RESOURCES_BQUEEN_PNG = path+"bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = path+"wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = path+"wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = path+"bpawn.png";
    private static final String RESOURCES_WCHEQUER_JPG = path+"wchecquer.jpg";
    private static final String RESOURCES_BCHEQUER_JPG = path+"bchequer.jpg";

    
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    

    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
     
      //for (.....)  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                    boolean isWhite = (r + c) % 2 == 0;
                    Square sq = new Square(this, isWhite, r, c);
                    board[r][c] = sq;
                    this.add(sq);
            }    
        }



        initializePieces();


        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.

    //my piece arrangement is a piece where the bishops should normally go, but the right bishops are moved one right
    void initializePieces() {
    	
    	 board[7][3].put(new Chequer(true, RESOURCES_WCHEQUER_JPG));
         board[7][5].put(new Chequer(true, RESOURCES_WCHEQUER_JPG));
         board[0][2].put(new Chequer(false, RESOURCES_BCHEQUER_JPG));
         board[0][5].put(new Chequer(false, RESOURCES_BCHEQUER_JPG));
        

    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     Image backgroundImage = null; 
     URL imageUrl = null;
     if (currPiece != null) {
      imageUrl = getClass().getResource("/src/main/java/com/example/"+currPiece.getImage());
     }

     if (imageUrl != null) {
            // This is the cleanest way to get an AWT Image object from a URL
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        } else {
            System.err.println("Image resource not found. Check path: /src/main/java/com/example/Pictures/");
        }
    
//this code is to establish the board in a row major order and to paint the squares
//in opposite color order
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Square sq = board[r][c];
                if(sq == fromMoveSquare)
                    
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                System.out.println("Painting square at " + r + ", " + c);   
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            currPiece = sq.getOccupyingPiece();
            
            fromMoveSquare = sq;
            //highlight legal moves in red for the chosen piece
            for(Square s: currPiece.getLegalMoves(this, sq)){
                s.setBorder(BorderFactory.createMatteBorder(7,7,7,7,Color.red));
            }
            //highlight controlled moves in blue for the chosen piece
            //border section implemented from stackoverflow.com about how to make 2 borders with 
            //the util.library about borders inserted at the top of the file
            for(Square s: currPiece.getControlledSquares(this.getSquareArray(), sq)){
                Border existing = s.getBorder();
                if(existing == null){
                s.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.blue));
            } else{
                s.setBorder(BorderFactory.createCompoundBorder(existing, BorderFactory.createMatteBorder(3,3,3,3,Color.blue)));
            }
            }
            if (currPiece.getColor() != whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override
    //precondition: the piece being moved is not null and the square its being moved to is on the board
    //postcondition: the piece is moved to the new square if it's a legal move as determined by the getLegalMoves
    public void mouseReleased(MouseEvent e) {
        for(Square[] row:board){
            for(Square s:row){
                s.setBorder(null);
            }
        }
         
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        

        //using currPiece
        if(fromMoveSquare != null){
            if(currPiece != null && currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)){
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
            }
            fromMoveSquare.setDisplay(true);
        }

        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}