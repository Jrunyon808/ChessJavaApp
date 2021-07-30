package Board;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardUtilities {
    //Create board structure and track coordinate notation
    public static final boolean[] EIGHTH_RANK = createRow(0);
    public static final boolean[] SEVENTH_RANK = createRow(8);
    public static final boolean[] SIXTH_RANK = createRow(16);
    public static final boolean[] FIFTH_RANK = createRow(24);
    public static final boolean[] FOURTH_RANK = createRow(32);
    public static final boolean[] THIRD_RANK = createRow(40);
    public static final boolean[] SECOND_RANK = createRow(48);
    public static final boolean[] FIRST_RANK = createRow(56);

    public static final boolean[] FIRST_FILE = createColumn(0);
    public static final boolean[] SECOND_FILE = createColumn(1);
    public static final boolean[] SEVENTH_FILE = createColumn(6);
    public static final boolean[] EIGHTH_FILE = createColumn(7);

    public static final String[] ALGEBRAIC_NOTATION = initializeAlgebraicNotation();

    public static final Map<String, Integer> POSITION_TO_COORDINATE = initializePositionToCoordinateMap();

    //Board size
    public static final int TILE_NUMBER = 64;
    public static final int TILE_NUMBER_PER_ROW = 8;
    public static final int NUM_TILES = 64;

    //Generate proper tiles names
    private static String[] initializeAlgebraicNotation() {
        return new String[]{
                "a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
                "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
                "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
                "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
                "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
                "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
                "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
                "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"
        };
    }

    private static Map<String, Integer> initializePositionToCoordinateMap() {
        //Assign tile names
        final Map<String, Integer> positionToCoordinate = new HashMap<>();
        for (int i = 0; i < NUM_TILES; i++) {
            positionToCoordinate.put(ALGEBRAIC_NOTATION[i], i);
        }
        return Collections.unmodifiableMap(positionToCoordinate);
    }

    public static boolean isValidTile(final int coordinate) {
        return coordinate >= 0 && coordinate < TILE_NUMBER;
    }

    private static boolean[] createRow(int rowNumber) {
        //Create rows in board
        final boolean[] row = new boolean[TILE_NUMBER];
        do {
            row[rowNumber] = true;
            rowNumber++;
        }
        while (rowNumber % TILE_NUMBER_PER_ROW != 0);
        return row;
    }

    private static boolean[] createColumn(int columnNumber) {
        //Create columns in board
        final boolean[] column = new boolean[64];
        do {
            column[columnNumber] = true;
            columnNumber += 8;
        }
        while (columnNumber < 64);
        return column;
    }

    public static int getCoordinateAtPosition(final String position) {
        return POSITION_TO_COORDINATE.get(position);
    }
}
