package Gui;

import Board.Board;
import Board.BoardUtilities;
import Board.Tile;
import Board.Move;
import Pieces.Piece;
import Player.MoveTransition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Gui {
    //Declare GUI variables
    private final javax.swing.JFrame JFrame;
    private final BoardPanel boardPanel;
    private Tile sourceTile;
    private Tile destinationTile;
    private Board chessBoard;
    private Piece movedPiece;

    //Declare sizes/colors of gui
    private final static Dimension BOARD_PANEL_DIMENSIONS = new Dimension(400, 400);

    private final static Dimension TILE_PANEL_DIMENSIONS = new Dimension(10, 10);

    private final static Dimension OUTER_FRAME_DIMENSIONS = new Dimension(600, 600);

    private final Color lightTileColor = Color.decode("#D8F5F9");
    private final Color darkTileColor = Color.decode("#198D9A");

    private static String defaultPieceImagesPath = "src/Art/pieces/";

    public Gui() {
        //Setup board with JFrame
        this.JFrame = new JFrame("Chess");
        this.JFrame.setLayout(new BorderLayout());
        this.JFrame.setSize(OUTER_FRAME_DIMENSIONS);
        this.chessBoard = Board.generateBoard();
        this.boardPanel = new BoardPanel();
        this.JFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.JFrame.setVisible(true);
    }


    java.util.List<TilePanel> traverseBoard(final java.util.List<TilePanel> boardTiles) {
        return boardTiles;
    }

    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;
        //Create grid structure with tile panel
        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();
            for (int i = 0; i < BoardUtilities.TILE_NUMBER; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL_DIMENSIONS);
            validate();
        }
        public void drawBoard(final Board board) {
            removeAll();
            for (final TilePanel tilePanel : traverseBoard(boardTiles)) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }

    private class TilePanel extends JPanel {
        private final int tileId;
        TilePanel(final BoardPanel boardPanel, final int tileId) {
            super(new GridBagLayout());
            this.tileId = tileId;
            setPreferredSize(TILE_PANEL_DIMENSIONS);
            assignTileColor();
            assignTilePiece(chessBoard);
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    //Allow for user input on board using mouse
                    if (isRightMouseButton(e)) {
                        sourceTile = null;
                        destinationTile = null;
                        movedPiece = null;
                        //Right clicking cancels move
                    } else if (isLeftMouseButton(e)) {
                        if (sourceTile == null) {
                            sourceTile = chessBoard.getTile(tileId);
                            movedPiece = sourceTile.getPiece();
                            if (movedPiece == null) {
                                sourceTile = null;
                            }
                            //Left clicking allows for selection of valid piece
                        } else {
                            destinationTile = chessBoard.getTile(tileId);
                            final Move move = Move.MoveGenerator.createMove(chessBoard, sourceTile.getTileCoordinate(), destinationTile.getTileCoordinate());
                            final MoveTransition transition = chessBoard.currentPlayer().makeMove(move);
                            if (transition.getMoveStatus().isDone()) {
                                chessBoard = transition.getTransitionBoard();
                                //Add move and reset input
                            }
                            sourceTile = null;
                            destinationTile = null;
                            movedPiece = null;
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(chessBoard);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(final MouseEvent e) {

                }

                @Override
                public void mouseReleased(final MouseEvent e) {

                }

                @Override
                public void mouseEntered(final MouseEvent e) {

                }

                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });
            validate();
        }

        public void drawTile(final Board board) {
            //Assign tile attributes
            assignTileColor();
            assignTilePiece(board);
            validate();
            repaint();
            highlightLegalMoves(board);
        }

        private void assignTilePiece(final Board board) {
            //Apply piece image to relevant tile
            this.removeAll();
            if (board.getTile(this.tileId).isTileOccupied()) {
                try {
                    final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath + board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1) + board.getTile(this.tileId).getPiece().toString() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void highlightLegalMoves(final Board board) {
            //Draw legal moves upon piece selection
            for (final Move move : pieceLegalMoves(board)) {
                if (move.getDestinationCoordinate() == this.tileId && !board.getTile(this.tileId).isTileOccupied()) {
                    try {
                        JLabel label = new JLabel(new ImageIcon("src/Art/misc/GreyDot.png"));
                        add(label);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            for (final Move move : pieceLegalMoves(board)) {
                //When under attack, replace piece image with attacked piece image
                if (move.getDestinationCoordinate() == this.tileId && board.getTile(this.tileId).isTileOccupied()) {
                    try {
                        this.removeAll();
                        final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath + board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1) + board.getTile(this.tileId).getPiece().toString() + "Attacked" + ".png"));
                        add(new JLabel(new ImageIcon(image)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        private Collection<Move> pieceLegalMoves(final Board board) {
            if (movedPiece != null && movedPiece.getPieceAlliance() == board.currentPlayer().getAlliance()) {
                return movedPiece.calculateLegalMoves(board);
            }
            return Collections.emptyList();
        }

        private void assignTileColor() {
            //Assign appropriate tile colors corresponding to chess board for grid
            if (BoardUtilities.EIGHTH_RANK[this.tileId] ||
                    BoardUtilities.SIXTH_RANK[this.tileId] ||
                    BoardUtilities.FOURTH_RANK[this.tileId] ||
                    BoardUtilities.SECOND_RANK[this.tileId]) {
                setBackground(this.tileId % 2 == 0 ? lightTileColor : darkTileColor);
            } else if (BoardUtilities.SEVENTH_RANK[this.tileId] ||
                    BoardUtilities.FIFTH_RANK[this.tileId] ||
                    BoardUtilities.THIRD_RANK[this.tileId] ||
                    BoardUtilities.FIRST_RANK[this.tileId]) {
                setBackground(this.tileId % 2 != 0 ? lightTileColor : darkTileColor);
            }
        }
    }
}
