import static org.junit.Assert.*;

import org.junit.Test;

import Chess.ChessGame;
import Chess.ChessPiece;
import Chess.Tile;

public class ChessTest {

	public ChessGame c = new ChessGame(false);
	public Tile[][] testBoard = c.getBoard().getBoardArray();
	@Test
	public void KingInBlackSide() {
		boolean containsKing = false;
		for(int x = 0; x < 8; x++) {
			if(testBoard[0][x].getPiece().getPieceType() == ChessPiece.PieceType.King) {
				containsKing = true;
			}
		}
		assertEquals(containsKing,true);
	}
	@Test
	public void KingInWhiteSide() {
		boolean containsKing = false;
		for(int x = 0; x < 8; x++) {
			if(testBoard[7][x].getPiece().getPieceType() == ChessPiece.PieceType.King) {
				containsKing = true;
			}
		}
		assertEquals(containsKing,true);
	}
	@Test
	public void QueenInWhiteSide() {
		boolean containsKing = false;
		for(int x = 0; x < 8; x++) {
			if(testBoard[7][x].getPiece().getPieceType() == ChessPiece.PieceType.Queen) {
				containsKing = true;
			}
		}
		assertEquals(containsKing,true);
	}
	@Test
	public void QueenInBlackSide() {
		boolean containsKing = false;
		for(int x = 0; x < 8; x++) {
			if(testBoard[0][x].getPiece().getPieceType() == ChessPiece.PieceType.Queen) {
				containsKing = true;
			}
		}
		assertEquals(containsKing,true);
	}
	
	@Test
	public void KingBetweenRooksBlackSide() {
		int rook1 = 0;
		boolean rook1found = false;
		int rook2 = 0;
		int king = 0;
		for(int x = 0; x < 8; x++) {
			if(testBoard[0][x].getPiece().getPieceType() == ChessPiece.PieceType.Rook) {
				if(!rook1found) {
					rook1found = true;
					rook1 = x;
				}
				else {
					rook2 = x;
				}
			}
		}
		for(int x = 0; x < 8; x++) {
			if(testBoard[0][x].getPiece().getPieceType() == ChessPiece.PieceType.King) {
				king = x;
			}
		}
		assertEquals(king > rook1 && king < rook2, true);
	}
	
	
	
	
	
	
	
	
	
}
