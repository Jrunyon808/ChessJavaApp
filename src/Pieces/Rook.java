package Pieces;

import Board.Board;
import Board.BoardUtilities;
import Board.Move;
import Board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {
            -8, -1, 1, 8
    };

    public Rook(final Alliance pieceAlliance, final int piecePosition) {
        //Declare piece of type rook
        super(PieceType.ROOK, piecePosition, pieceAlliance, true);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtilities.isValidTile(candidateDestinationCoordinate)) {
                //Determine if piece is on an edge case tile
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) || isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if (BoardUtilities.isValidTile(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) {
                        //Add candidate moves with offset equal to piece's legal move
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.MajorAttackingMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }

            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.ROOK.toString();
    }

    @Override
    public Rook movePiece(Move move) {
        return new Rook(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
    }

    //Corrects for edge cases in piece logic
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtilities.FIRST_FILE[currentPosition] && (candidateOffset == -1);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtilities.EIGHTH_FILE[currentPosition] && (candidateOffset == 1);
    }
}
