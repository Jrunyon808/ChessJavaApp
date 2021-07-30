package Tests;

import Board.Board;
import Pieces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void testInvalidBoard() {
        assertThrows(RuntimeException.class,
                () -> {
                    final Board.Builder builder = new Board.Builder();
                    // Set black layout
                    builder.setPiece(new Rook(Alliance.BLACK, 0));
                    builder.setPiece(new Knight(Alliance.BLACK, 1));
                    builder.setPiece(new Bishop(Alliance.BLACK, 2));
                    builder.setPiece(new Queen(Alliance.BLACK, 3));
                    builder.setPiece(new Bishop(Alliance.BLACK, 5));
                    builder.setPiece(new Knight(Alliance.BLACK, 6));
                    builder.setPiece(new Rook(Alliance.BLACK, 7));
                    builder.setPiece(new Pawn(Alliance.BLACK, 8));
                    builder.setPiece(new Pawn(Alliance.BLACK, 9));
                    builder.setPiece(new Pawn(Alliance.BLACK, 10));
                    builder.setPiece(new Pawn(Alliance.BLACK, 11));
                    builder.setPiece(new Pawn(Alliance.BLACK, 12));
                    builder.setPiece(new Pawn(Alliance.BLACK, 13));
                    builder.setPiece(new Pawn(Alliance.BLACK, 14));
                    builder.setPiece(new Pawn(Alliance.BLACK, 15));
                    // Set white layout
                    builder.setPiece(new Pawn(Alliance.WHITE, 48));
                    builder.setPiece(new Pawn(Alliance.WHITE, 49));
                    builder.setPiece(new Pawn(Alliance.WHITE, 50));
                    builder.setPiece(new Pawn(Alliance.WHITE, 51));
                    builder.setPiece(new Pawn(Alliance.WHITE, 52));
                    builder.setPiece(new Pawn(Alliance.WHITE, 53));
                    builder.setPiece(new Pawn(Alliance.WHITE, 54));
                    builder.setPiece(new Pawn(Alliance.WHITE, 55));
                    builder.setPiece(new Rook(Alliance.WHITE, 56));
                    builder.setPiece(new Knight(Alliance.WHITE, 57));
                    builder.setPiece(new Bishop(Alliance.WHITE, 58));
                    builder.setPiece(new Queen(Alliance.WHITE, 59));
                    builder.setPiece(new Bishop(Alliance.WHITE, 61));
                    builder.setPiece(new Knight(Alliance.WHITE, 62));
                    builder.setPiece(new Rook(Alliance.WHITE, 63));
                    //Set move to white
                    builder.setMove(Alliance.WHITE);
                    //Build board
                    builder.build();
                });
    }
}