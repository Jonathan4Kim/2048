package org.cis120.twentyFortyEight;

import java.awt.*;

public class Tile {
    private int row;
    private int col;
    private int v;

    public Tile(int row, int col, int v) {
        this.row = row;
        this.col = col;
        this.v = v;
    }

    // **************************************************************************
    // * GETTERS
    // **************************************************************************

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getV() { return this.v; }

    // **************************************************************************
    // * SETTERS
    // **************************************************************************

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setV(int v) {
        this.v = v;
    }

    // **************************************************************************
    // * GETTING COLOR
    // **************************************************************************

    public Color getBoxColor() {
        if (this.v == 2) {
            return Color.BLACK;
        } else if (this.v == 4) {
            return Color.BLUE;
        } else if (this.v == 8) {
            return Color.PINK;
        } else if (this.v == 16) {
            return Color.MAGENTA;
        } else if (this.v == 32) {
            return Color.RED;
        } else if (this.v == 64) {
            return Color.gray;
        } else if (this.v == 128) {
            return Color.BLACK;
        } else if (this.v == 256) {
            return Color.ORANGE;
        } else if (this.v == 512) {
            return Color.DARK_GRAY;
        } else if (this.v == 1024) {
            return Color.CYAN;
        } else {
            return Color.GREEN;
        }
    }
}
