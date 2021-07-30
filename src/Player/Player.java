package Player;

import Board.Board;
import Board.Move;
import Pieces.Alliance;
import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    //Declare player class variables
    protected final Collection<Move> legalMoves;
    protected final Board board;
    protected final King king;
    private final boolean isInCheck;

    //Construct player
    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {
        this.board = board;
        this.king = establishKing();
        this.isInCheck = !Player.calculateAttacksOnTile(this.king.getPiecePosition(), opponentMoves).isEmpty();
        legalMoves.addAll(calculateKingCastles(legalMoves, opponentMoves));
        this.legalMoves = Collections.unmodifiableCollection(legalMoves);
    }

    public King getKing() {
        return this.king;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    protected static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
        //Determine if piece is attacked
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (piecePosition == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoves);
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Invalid board state");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }


    public boolean hasEscapeMoves() {
        //Check for legal moves remaining
        for (final Move move : this.legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isInCheckMate() {
        return this.isInCheck && !hasEscapeMoves();
    }

    public boolean isInStaleMate() {
        return !this.isInCheck && !hasEscapeMoves();
    }

    public MoveTransition makeMove(final Move move) {
        //Determine if move is legal
        if (!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE, this.board);
        }
        //Execute move if so
        final Board transitionBoard = move.execute();
        final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getKing().getPiecePosition(), transitionBoard.currentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK, this.board);
        }
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE, this.board);
    }

    public MoveTransition makeTestMove(final Move move) {
        //Allow for legal move test cases
        if (!this.legalMoves.contains(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE, this.board);
        }
        final Board transitionedBoard = move.execute();
        return transitionedBoard.currentPlayer().getOpponent().isInCheck() ?
                new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK, this.board) :
                new MoveTransition(this.board, move, MoveStatus.DONE, transitionedBoard);
    }

    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals);

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();
}
