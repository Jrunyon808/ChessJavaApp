package Board;

import Pieces.*;
import Player.BlackPlayer;
import Player.Player;
import Player.WhitePlayer;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    //Declare board variables
    private final List<Tile> gameBoard;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final Player player;
    private final Pawn enPassantPawn;

    private Board(final Builder builder) {
        //Construct Board
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculatePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculatePieces(this.gameBoard, Alliance.BLACK);
        this.enPassantPawn = builder.enPassantPawn;
        final Collection<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves);
        this.player = builder.nextMove.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    public Pawn getEnPassantPawn() {
        return this.enPassantPawn;
    }

    //Declare collection of pieces
    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    //Declare players
    public Player whitePlayer() {
        return this.whitePlayer;
    }

    public Player blackPlayer() {
        return this.blackPlayer;
    }

    public Player currentPlayer() {
        return this.player;
    }

    //Get legal moves
    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        return pieces.stream().flatMap(piece -> piece.calculateLegalMoves(this).stream())
                .collect(Collectors.toList());
    }

    //Get remaining pieces
    private static Collection<Piece> calculatePieces(List<Tile> gameBoard, Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return Collections.unmodifiableList(activePieces);
    }

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    //Create chess board using tiles
    public static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[BoardUtilities.TILE_NUMBER];
        for (int i = 0; i < BoardUtilities.TILE_NUMBER; i++) {
            tiles[i] = Tile.createNewTile(i, builder.boardConfig.get(i));
        }
        return List.of(tiles);
    }

    public static Board generateBoard() {
        final Builder builder = new Builder();
        //Setup white pieces
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
        builder.setPiece(new King(Alliance.WHITE, 60));
        builder.setPiece(new Bishop(Alliance.WHITE, 61));
        builder.setPiece(new Knight(Alliance.WHITE, 62));
        builder.setPiece(new Rook(Alliance.WHITE, 63));

        //Setup black pieces
        builder.setPiece(new Rook(Alliance.BLACK, 0));
        builder.setPiece(new Knight(Alliance.BLACK, 1));
        builder.setPiece(new Bishop(Alliance.BLACK, 2));
        builder.setPiece(new Queen(Alliance.BLACK, 3));
        builder.setPiece(new King(Alliance.BLACK, 4));
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

        //Set to white's move
        builder.setMove(Alliance.WHITE);
        return builder.build();
    }

    public Iterable<Move> getAllLegalMoves() {
        //Get list of legal moves for each player
        List<Move> allLegalMoves = new ArrayList<>();
        allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
        allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
        return Collections.unmodifiableList(allLegalMoves);
    }

    public static class Builder {
        //Build board
        Alliance nextMove;
        private Pawn enPassantPawn;
        Map<Integer, Piece> boardConfig;

        public Builder() {
            this.boardConfig = new HashMap<>();
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMove(final Alliance nextMoveMaker) {
            this.nextMove = nextMoveMaker;
            return this;
        }

        public Board build() {
            return new Board(this);
        }

        public void setEnPassantPawn(Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
        }
    }
}
