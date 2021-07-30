package Board;

import Pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createEmptyTiles();

    private static final Map<Integer, EmptyTile> createEmptyTiles() {
        //Store tiles in hash map
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtilities.TILE_NUMBER; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap);
    }

    public static Tile createNewTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    //Declare tile variables
    Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract Piece getPiece();

    public int getTileCoordinate() {
        return this.tileCoordinate;
    }

    public abstract boolean isTileOccupied();


    public static final class EmptyTile extends Tile {
        //Return correct values for empty tiles
        private EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public Piece getPiece() {
            return null;
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

    }

    public static final class OccupiedTile extends Tile {
        //Return correct values for occupied tiles
        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

    }
}
