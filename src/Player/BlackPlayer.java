package Player;

import Board.Board;
import Board.Move;
import Board.Tile;
import Pieces.Alliance;
import Pieces.Piece;
import Pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    //Get important player information
    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals, final Collection<Move> opponentsLegals) {
        //Check if king side castle is legal and add move
        final List<Move> kingCastles = new ArrayList<>();
        if (this.king.isFirstMove() && !this.isInCheck()) {
            if (!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(7);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.king, 6, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 5));
                    }
                }
            }
            //Check if queen side castle is legal and add move
            if (!this.board.getTile(1).isTileOccupied() && !this.board.getTile(2).isTileOccupied() && !this.board.getTile(3).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(0);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() && Player.calculateAttacksOnTile(2, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(3, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.king, 2, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 3));
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }
}
