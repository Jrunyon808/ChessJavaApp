/*package Tests;

import Board.Board;
import Board.BoardUtilities;
import Board.Move;
import Player.MoveTransition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckmateTest {
    @Test
    public void FoolsMateTest() {
        //Set piece positions
        final Board board = Board.generateBoard();
        final MoveTransition tb1 = board.currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(board, BoardUtilities.getCoordinateAtPosition("f2"),
                        BoardUtilities.getCoordinateAtPosition("f3")));

        assertTrue(tb1.getMoveStatus().isDone());

        final MoveTransition tb2 = tb1.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb1.getToBoard(), BoardUtilities.getCoordinateAtPosition("e7"),
                        BoardUtilities.getCoordinateAtPosition("e5")));

        assertTrue(tb2.getMoveStatus().isDone());

        final MoveTransition tb3 = tb2.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb2.getToBoard(), BoardUtilities.getCoordinateAtPosition("g2"),
                        BoardUtilities.getCoordinateAtPosition("g4")));

        assertTrue(tb3.getMoveStatus().isDone());

        final MoveTransition t4 = tb3.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb3.getToBoard(), BoardUtilities.getCoordinateAtPosition("d8"),
                        BoardUtilities.getCoordinateAtPosition("h4")));

        assertTrue(t4.getMoveStatus().isDone());
        //Test for checkmate
        assertTrue(t4.getToBoard().currentPlayer().isInCheckMate());

    }

    @Test
    public void ScholarsMateTest() {
        //Set piece positions
        final Board board = Board.generateBoard();
        final MoveTransition tb1 = board.currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(board, BoardUtilities.getCoordinateAtPosition("e2"),
                        BoardUtilities.getCoordinateAtPosition("e4")));

        assertTrue(tb1.getMoveStatus().isDone());

        final MoveTransition tb2 = tb1.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb1.getToBoard(), BoardUtilities.getCoordinateAtPosition("a7"),
                        BoardUtilities.getCoordinateAtPosition("a6")));

        assertTrue(tb2.getMoveStatus().isDone());

        final MoveTransition tb3 = tb2.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb2.getToBoard(), BoardUtilities.getCoordinateAtPosition("d1"),
                        BoardUtilities.getCoordinateAtPosition("f3")));

        assertTrue(tb3.getMoveStatus().isDone());

        final MoveTransition tb4 = tb3.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb3.getToBoard(), BoardUtilities.getCoordinateAtPosition("a6"),
                        BoardUtilities.getCoordinateAtPosition("a5")));

        assertTrue(tb4.getMoveStatus().isDone());

        final MoveTransition tb5 = tb4.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb4.getToBoard(), BoardUtilities.getCoordinateAtPosition("f1"),
                        BoardUtilities.getCoordinateAtPosition("c4")));

        assertTrue(tb5.getMoveStatus().isDone());

        final MoveTransition tb6 = tb5.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb5.getToBoard(), BoardUtilities.getCoordinateAtPosition("a5"),
                        BoardUtilities.getCoordinateAtPosition("a4")));

        assertTrue(tb6.getMoveStatus().isDone());

        final MoveTransition tb7 = tb6.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb6.getToBoard(), BoardUtilities.getCoordinateAtPosition("f3"),
                        BoardUtilities.getCoordinateAtPosition("f7")));

        assertTrue(tb7.getMoveStatus().isDone());
        assertTrue(tb7.getToBoard().currentPlayer().isInCheckMate());
        //Test for checkmate
    }

    @Test
    public void SmotheredMateTest() {
        //Set piece positions
        final Board board = Board.generateBoard();
        final MoveTransition tb1 = board.currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(board, BoardUtilities.getCoordinateAtPosition("e2"),
                        BoardUtilities.getCoordinateAtPosition("e4")));

        assertTrue(tb1.getMoveStatus().isDone());

        final MoveTransition tb2 = tb1.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb1.getToBoard(), BoardUtilities.getCoordinateAtPosition("e7"),
                        BoardUtilities.getCoordinateAtPosition("e5")));

        assertTrue(tb2.getMoveStatus().isDone());

        final MoveTransition tb3 = tb2.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb2.getToBoard(), BoardUtilities.getCoordinateAtPosition("g1"),
                        BoardUtilities.getCoordinateAtPosition("e2")));

        assertTrue(tb3.getMoveStatus().isDone());

        final MoveTransition tb4 = tb3.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb3.getToBoard(), BoardUtilities.getCoordinateAtPosition("b8"),
                        BoardUtilities.getCoordinateAtPosition("c6")));

        assertTrue(tb4.getMoveStatus().isDone());

        final MoveTransition tb5 = tb4.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb4.getToBoard(), BoardUtilities.getCoordinateAtPosition("b1"),
                        BoardUtilities.getCoordinateAtPosition("c3")));

        assertTrue(tb4.getMoveStatus().isDone());

        final MoveTransition tb6 = tb5.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb5.getToBoard(), BoardUtilities.getCoordinateAtPosition("c6"),
                        BoardUtilities.getCoordinateAtPosition("d4")));

        assertTrue(tb6.getMoveStatus().isDone());

        final MoveTransition tb7 = tb6.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb6.getToBoard(), BoardUtilities.getCoordinateAtPosition("g2"),
                        BoardUtilities.getCoordinateAtPosition("g3")));

        assertTrue(tb7.getMoveStatus().isDone());

        final MoveTransition tb8 = tb7.getToBoard()
                .currentPlayer()
                .makeTestMove(Move.MoveGenerator.createMove(tb7.getToBoard(), BoardUtilities.getCoordinateAtPosition("d4"),
                        BoardUtilities.getCoordinateAtPosition("f3")));
        //Test for checkmate
        assertTrue(tb8.getMoveStatus().isDone());
        assertTrue(tb8.getToBoard().currentPlayer().isInCheckMate());
    }
}*/