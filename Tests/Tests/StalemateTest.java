/*package Tests;

import Board.Board;
import Board.BoardUtilities;
import Board.Move;
import Pieces.Alliance;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Pawn;
import Player.MoveTransition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StalemateTest {
    @Test
    public void staleMateTest() {
        final Board.Builder builder = new Board.Builder();
        //Set piece positions
        builder.setPiece(new King(Alliance.BLACK, 2));
        builder.setPiece(new Pawn(Alliance.WHITE, 10));
        builder.setPiece(new King(Alliance.WHITE, 26));
        // Set move to white
        builder.setMove(Alliance.WHITE);
        //Test for stalemate
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isInStaleMate());
        final MoveTransition t1 = board.currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(board, BoardUtilities.getCoordinateAtPosition("c5"),
                        BoardUtilities.getCoordinateAtPosition("c6")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }

    @Test
    public void staleMateTest2() {
        final Board.Builder builder = new Board.Builder();
        //Set piece positions
        builder.setPiece(new King(Alliance.BLACK, 0));
        builder.setPiece(new Pawn(Alliance.WHITE, 16));
        builder.setPiece(new King(Alliance.WHITE, 17));
        builder.setPiece(new Bishop(Alliance.WHITE, 19));
        // Set move to white
        builder.setMove(Alliance.WHITE);
        //Test for stalemate
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isInStaleMate());
        final MoveTransition t1 = board.currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(board, BoardUtilities.getCoordinateAtPosition("a6"),
                        BoardUtilities.getCoordinateAtPosition("a7")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInStaleMate());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }
}*/