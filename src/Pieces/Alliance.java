package Pieces;

import Board.BoardUtilities;
import Player.BlackPlayer;
import Player.Player;
import Player.WhitePlayer;

public enum Alliance {
    WHITE {
        //Get required information for white pieces
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public int getOppositeDirection() {
            return 1;
        }

        @Override
        public boolean isPromotionSquare(int position) {
            return BoardUtilities.EIGHTH_RANK[position];
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    }, BLACK {
        //Get required information from black pieces
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public int getOppositeDirection() {
            return -1;
        }

        @Override
        public boolean isPromotionSquare(int position) {
            return BoardUtilities.FIRST_RANK[position];
        }

        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract int getDirection();

    public abstract int getOppositeDirection();

    public abstract boolean isPromotionSquare(int position);

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
