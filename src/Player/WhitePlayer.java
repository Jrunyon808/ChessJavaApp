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

public class WhitePlayer extends Player{
    public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    //Get important player information
    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals, Collection<Move> opponentsLegals) {
        //Check if kingside castle is legal and add move
        final List<Move> kingCastles = new ArrayList<>();
        if (this.king.isFirstMove() && !this.isInCheck()) {
            if (!this.board.getTile(61).isTileOccupied() && !this.board.getTile(62).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(63);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new Move.KingSideCastleMove(this.board, this.king, 62, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 61));
                    }
                }
            }
            //Check if queen side castle is legal and add move
            if (!this.board.getTile(59).isTileOccupied() && !this.board.getTile(58).isTileOccupied() && !this.board.getTile(57).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(56);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() && Player.calculateAttacksOnTile(58, opponentsLegals).isEmpty() && Player.calculateAttacksOnTile(59, opponentsLegals).isEmpty() && rookTile.getPiece().getPieceType().isRook()) {
                    kingCastles.add(new Move.QueenSideCastleMove(this.board, this.king, 58, (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }
}
