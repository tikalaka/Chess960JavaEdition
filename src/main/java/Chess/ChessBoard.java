package Chess;

import Pieces.*;

import java.util.ArrayList;
import java.util.Random;

public class ChessBoard {
    private final Tile[][] board;

    public ChessBoard(boolean Mode960){
        board = new Tile[8][8];
        initializeBoard();
        if(Mode960) {
        	fillBoardRandomized();        	
        }
        else {
        	fillBoard();
        }
    }

    public Tile[][] getBoardArray(){
        return board;
    }

    private void initializeBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new Tile(Tile.TileColor.Black);
                else board[i][j] = new Tile(Tile.TileColor.White);
            }
        }
    }

    //Will break on boards with no Kings of 'color'. Should never happen.
    public Tuple getKingLocation(ChessPiece.PieceColor color){
        Tuple location = new Tuple(-1,-1);
        for (int x = 0; x <= 7; x++){
            for (int y = 0; y <= 7 ; y++){
                if (!board[y][x].isEmpty()) {
                    ChessPiece piece = board[y][x].getPiece();
                    if (piece.getColor() == color && piece instanceof King){
                       location = new Tuple(x, y);
                    }
                }
            }
        }
        return location;
    }

    public Tuple[] getAllPiecesLocationForColor(ChessPiece.PieceColor color){
        ArrayList<Tuple> locations = new ArrayList<>();
        for (int x = 0; x <= 7; x++){
            for (int y = 0; y <= 7; y++){
               if(!board[y][x].isEmpty() && board[y][x].getPiece().getColor() == color)
                   locations.add(new Tuple(x,y));
            }
        }
        return locations.toArray(new Tuple[0]);//allocate new array automatically.
    }

    public Tile getTileFromTuple(Tuple tuple){
        return board[tuple.Y()][tuple.X()];
    }

    /*
    Initial filler of board
     */
    private void fillBoard(){
        //pawns
        for(int i = 0; i < 8; i++){
        board[1][i].setPiece(new Pawn(ChessPiece.PieceColor.Black));
        board[6][i].setPiece(new Pawn(ChessPiece.PieceColor.White));
        }

        //rooks
        board[0][0].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[0][7].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[7][0].setPiece(new Rook(ChessPiece.PieceColor.White));
        board[7][7].setPiece(new Rook(ChessPiece.PieceColor.White));

        //knight
        board[0][1].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[0][6].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[7][1].setPiece(new Knight(ChessPiece.PieceColor.White));
        board[7][6].setPiece(new Knight(ChessPiece.PieceColor.White));

        //bishop
        board[0][2].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[0][5].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[7][2].setPiece(new Bishop(ChessPiece.PieceColor.White));
        board[7][5].setPiece(new Bishop(ChessPiece.PieceColor.White));

        //queens
        board[0][3].setPiece(new Queen(ChessPiece.PieceColor.Black));
        board[7][3].setPiece(new Queen(ChessPiece.PieceColor.White));

        //kings
        board[0][4].setPiece(new King(ChessPiece.PieceColor.Black));
        board[7][4].setPiece(new King(ChessPiece.PieceColor.White));
    }
    public static int[] usedPositions = { 0, 0, 0, 0, 0, 0, 0, 0 };

    private void fillBoardRandomized(){
        for(int i = 0; i < 8; i++){
        board[1][i].setPiece(new Pawn(ChessPiece.PieceColor.Black));
        board[6][i].setPiece(new Pawn(ChessPiece.PieceColor.White));
        }
        int kingPos = GetNewPosition(1, 6);
        board[0][kingPos].setPiece(new King(ChessPiece.PieceColor.Black));
        int rookLeftPos = GetNewPosition(0, kingPos - 1);
        int rookRightPos = GetNewPosition(kingPos + 1, 7);
        board[0][rookLeftPos].setPiece(new Rook(ChessPiece.PieceColor.Black));
        board[0][rookRightPos].setPiece(new Rook(ChessPiece.PieceColor.Black));
        int bishopPosEven = GetNewPositionBishop(true);
        int bishopPosOdd = GetNewPositionBishop(false);
        board[0][bishopPosEven].setPiece(new Bishop(ChessPiece.PieceColor.Black));
        board[0][bishopPosOdd].setPiece(new Bishop(ChessPiece.PieceColor.Black));  
        int knight1Pos = GetNewPosition(0, 7);
        int knight2Pos = GetNewPosition(0, 7);
        board[0][knight1Pos].setPiece(new Knight(ChessPiece.PieceColor.Black));
        board[0][knight2Pos].setPiece(new Knight(ChessPiece.PieceColor.Black));
        int queenPos = GetNewPosition(0, 7);
        board[0][queenPos].setPiece(new Queen(ChessPiece.PieceColor.Black));
        usedPositions[0]=0;
        usedPositions[1]=0;
        usedPositions[2]=0;
        usedPositions[3]=0;
        usedPositions[4]=0;
        usedPositions[5]=0;
        usedPositions[6]=0;
        usedPositions[7]=0;
        kingPos = GetNewPosition(1, 6);
        board[7][kingPos].setPiece(new King(ChessPiece.PieceColor.White));
        rookLeftPos = GetNewPosition(0, kingPos - 1);
        rookRightPos = GetNewPosition(kingPos + 1, 7);
        board[7][rookLeftPos].setPiece(new Rook(ChessPiece.PieceColor.White));
        board[7][rookRightPos].setPiece(new Rook(ChessPiece.PieceColor.White));
        bishopPosEven = GetNewPositionBishop(true);
        bishopPosOdd = GetNewPositionBishop(false);
        board[7][bishopPosEven].setPiece(new Bishop(ChessPiece.PieceColor.White));
        board[7][bishopPosOdd].setPiece(new Bishop(ChessPiece.PieceColor.White));
        knight1Pos = GetNewPosition(0, 7);
        knight2Pos = GetNewPosition(0, 7);
        board[7][knight1Pos].setPiece(new Knight(ChessPiece.PieceColor.White));
        board[7][knight2Pos].setPiece(new Knight(ChessPiece.PieceColor.White));
        queenPos = GetNewPosition(0, 7);
        board[7][queenPos].setPiece(new Queen(ChessPiece.PieceColor.White));

        
    }
    

    private boolean gameMode960On;
    
    public static int GetNewPosition(int min, int max)
    {
        Random rand = new Random();
        boolean done = false;
        int newPos = 8;
        do
        {
            newPos = rand.nextInt(max + 1 - min) + min;
            if (usedPositions[newPos] == 0)
            {
                usedPositions[newPos] = 1;
                done = true;
            }
        } while (!done);
        return newPos;
    }

    public static int GetNewPositionBishop(boolean even)
    {
        Random rand = new Random();
        boolean done = false;
        int newPos = 8;
        do
        {
            newPos = rand.nextInt(8);
            if (even && usedPositions[newPos] == 0 && newPos % 2 == 0)
            {
                usedPositions[newPos] = 1;
                done = true;
            }
            else if (!even && usedPositions[newPos] == 0 && newPos % 2 == 1)
            {
                usedPositions[newPos] = 1;
                done = true;
            }
        } while (!done);
        return newPos;
    }
}
