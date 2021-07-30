package Player;

import Board.Board;
import Board.Move;

public class MoveTransition {
    private final Board transitionalBoard;
    private final Move move;
    private final MoveStatus moveStatus;
    private final Board toBoard;

    public MoveTransition(Board transitionalBoard, final Move move, final MoveStatus moveStatus, final Board toBoard) {
        //Construct move transition
        this.transitionalBoard = transitionalBoard;
        this.moveStatus = moveStatus;
        this.move = move;
        this.toBoard = toBoard;
    }

    //Get board state information
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getTransitionBoard() {
        return this.transitionalBoard;
    }

    public Board getToBoard() {
        return this.toBoard;
    }
}
