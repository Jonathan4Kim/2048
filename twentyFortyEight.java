package org.cis120.twentyFortyEight;

public class twentyFortyEight {

    private Tile[][] board;
    private int numTurns;
    private boolean gameGoingOn;

    /**
     * Constructor sets up game state.
     */
    public twentyFortyEight() {
        reset();
    }

    /**
     * playTurn allows players to play a turn. Returns true if the move is
     * successful and false if a player tries to play in a location that is
     * taken or after the game has ended. If the turn is successful and the game
     * has not ended, the player is changed. If the turn is unsuccessful or the
     * game has ended, the player is not changed.
     *
     * @return whether the game is still going on
     */
    public boolean playTurn() {
        numTurns++;
        if (checkWin() || checkLoss()) {
            return false;
        } else {
            generateTile();
            return true;
        }
    }


    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new Tile[4][4];
        generateTile();
        generateTile();
        numTurns = 0;
        gameGoingOn = true;
    }

    /**
     * getCell is a getter for the contents of the cell specified by the method
     * arguments.
     *
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents of the corresponding cell on the
     *         game board. 0 = empty, 1 = Player 1, 2 = Player 2
     */
    public Tile getTile(int r, int c) {
        return board[r][c];
    }

    public int getNumTurns() {
        return numTurns;
    }

    public Tile[][] getBoard() {
        Tile[][] boardNow = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                boardNow[i][j] = board[i][j];
            }
        }
        return boardNow;
    }

    public void setBoard(Tile[][] newBoard) {
        board = newBoard;
    }

    public void incNumTurns() {
        numTurns++;
    }

    // **************************************************************************
    // * CHECKING FOR WINS AND LOSSES
    // **************************************************************************

    /**
     * checkWin checks whether the game has reached a win condition.
     * checkWin only looks for horizontal wins.
     *
     * @return false if nobody has won yet, true if someone did
     */
    public boolean checkWin() {
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && board[i][j].getV() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checkLoss checks whether the game has reached a win condition.
     * checkWinner only looks for horizontal wins.
     *
     * @return true if someone has lost, false if no loss is detected
     */

    public boolean checkLoss() {
        if (anyNull()) {
            return false;
        } else {
            return !hasValidMove();
        }
    }

    /**
     * anyNull sees if there are any null pieces on the board; a helper
     * function to checkLoss
     *
     * @return true if there is a null piece, false if  is detected
     */

    public boolean anyNull() {
        for (int i = 0 ; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * hasValidMove returns if there is a possible move on the board
     *
     * @return true if there is a move possible, false if there isn't
     */

    public boolean hasValidMove() {
        return hasValidMovesOnBorder() && hasValidMovesInMiddleFour();
    }

    /**
     * hasValidMoveInMiddleFour iterates through the 4 middle piece
     * and looks for adjacent pieces. Assumes that there is no null piece.
     *
     * @return true if there is a move possible, false if there isn't
     */

    public boolean hasValidMovesInMiddleFour() {
        for (int i = 1 ; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                if (middlePieceHasMove(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * middlePieceHasMove looks through a full board and at a middle piece,
     * and sees if there is a possible outcome.  helper function to
     * hasValidMovesInMiddleFour
     *
     * @return true if there is a move possible, false if there isn't
     */

    public boolean middlePieceHasMove(int row, int col) {
        int v = board[row][col].getV();
        int vUp = board[row - 1][col].getV();
        int vDown = board[row + 1][col].getV();
        int vLeft = board[row][col - 1].getV();
        int vRight = board[row][col + 1].getV();
        return v == vUp || v == vDown || v == vLeft || v == vRight;
    }

    /**
     * checkBorder sees if there is a move in row or column 0 and 3, respectively,
     * by looking for adjacent same values of tile.
     * It's assumed that there are no null values on the board.
     *
     * @return true if there is a move possible, false if there isn't
     */

    public boolean hasValidMovesOnBorder() {
        for (int i = 0 ; i < 4; i = i + 3) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getV() == board[i][j + 1].getV()) {
                    return true;
                }
            }
        }

        for (int col = 0 ; col < 4; col = col + 3) {
            for (int row = 0; row < 3; row++) {
                if (board[row][col].getV() == board[row + 1][col].getV()) {
                    return true;
                }
            }
        }

        return false;
    }


    // **************************************************************************
    // * MOVEMENT
    // **************************************************************************

    /**
     * moveUp moves board pieces up following a key press
     */

    public void moveUp() {
        slideUp();
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3; row++) {
                if (board[row][col] != null && board[row + 1][col] != null) {
                    if (board[row][col].getV() == board[row + 1][col].getV()) {
                        board[row][col].setV(2 * board[row + 1][col].getV());
                        board[row + 1][col] = null;
                    }
                }
            }
        }
        slideUp();
    }

    /**
     * slideUp board pieces to upwards-most position following a key press;
     * a helper function to moveUp
     */
    public void slideUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 1; row < 4; row++) {
                if (row == 1 && board[row][col] != null) {
                    if (board[row - 1][col] == null) {
                        board[row - 1][col] = new Tile (row - 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else if (row == 2 && board[row][col] != null) {
                    if (board[row - 2][col] == null && board[row - 1][col] == null) {
                        board[row - 2][col] = new Tile (row - 2, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row - 2][col] != null && board[row - 1][col] == null) {
                        board[row - 1][col] = new Tile (row - 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else if (row == 3 && board[row][col] != null) {
                    if (board[row - 3][col] == null && board[row - 2][col] == null && board[row - 1][col] == null) {
                        board[row - 3][col] = new Tile (row - 3, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row - 3][col] != null && board[row - 2][col] == null
                            && board[row - 1][col] == null) {
                        board[row - 2][col] = new Tile (row - 2, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row - 3][col] != null && board[row - 2][col] != null
                            && board[row - 1][col] == null) {
                        board[row - 1][col] = new Tile (row - 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                }
            }
        }
    }

    /**
     * moveDown moves board pieces downwards following a key press
     */
    public void moveDown() {
        slideDown();
        //combine values
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0; row--) {
                if (board[row][col] != null && board[row - 1][col] != null) {
                    if (board[row][col].getV() == board[row - 1][col].getV()) {
                        board[row - 1][col].setV(2 * board[row - 1][col].getV());
                        board[row][col] = null;
                    }
                }
            }
        }
        slideDown();
    }

    /**
     * slideDown slides board pieces to downwards-most position following a key press;
     * a helper function to moveDown
     */
    public void slideDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 2; row >= 0; row--) {
                if (row == 2 && board[row][col] != null) {
                    if (board[row + 1][col] == null) {
                        board[row + 1][col] = new Tile(row + 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else if (row == 1 && board[row][col] != null) {
                    if (board[row + 1][col] == null && board[row + 2][col] == null) {
                        board[row + 2][col] = new Tile(row + 2, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row + 1][col] == null && board[row + 2][col] != null) {
                        board[row + 1][col] = new Tile(row + 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else if (row == 0 && board[row][col] != null) {
                    if (board[row + 1][col] == null && board[row + 2][col] == null && board[row + 3][col] == null) {
                        board[row + 3][col] = new Tile(row + 3, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row + 1][col] == null && board[row + 2][col] == null
                            && board[row + 3][col] != null) {
                        board[row + 2][col] = new Tile(row + 2, col, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row + 1][col] == null && board[row + 2][col] != null
                            && board[row + 3][col] != null) {
                        board[row + 1][col] = new Tile(row + 1, col, board[row][col].getV());
                        board[row][col] = null;
                    }
                }
            }
        }
    }

    /**
     * moveLeft moves board pieces leftwards following a key press
     */
    public void moveLeft() {
        slideLeft();

        //combine values
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] != null && board[row][col + 1] != null) {
                    if (board[row][col].getV() == board[row][col + 1].getV()) {
                        board[row][col].setV(2 * board[row][col].getV());
                        board[row][col + 1] = null;
                    }
                }
            }
        }
        slideLeft();
    }

    /**
     * slideLeft board pieces to leftwards-most position following a key press;
     * a helper function to moveLeft
     */
    public void slideLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 1; col < 4; col++) {
                if (col == 1) {
                    if (board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 1] = new Tile(row, 0, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else if (col == 2) {
                    if (board[row][col - 2] == null && board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 2] = new Tile(row, 0, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row][col - 2] != null && board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 1] = new Tile(row, 1, board[row][col].getV());
                        board[row][col] = null;
                    }
                } else {
                    if (board[row][col - 3] == null && board[row][col - 2] == null
                            && board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 3] = new Tile(row, 0, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row][col - 3] != null && board[row][col - 2] == null
                            && board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 2] = new Tile(row, 1, board[row][col].getV());
                        board[row][col] = null;
                    } else if (board[row][col - 3] != null && board[row][col - 2] != null
                            && board[row][col - 1] == null && board[row][col] != null) {
                        board[row][col - 1] = new Tile(row, 2, board[row][col].getV());
                        board[row][col] = null;
                    }
                }
            }
        }
    }

    /**
     * moveRight moves board pieces leftwards following a key press.
     * it first pushes all non-null tile values as far as possible,
     * then 'splashes' or combines values.
     *
     * no return value.
     */
    public void moveRight() {
        slideRight();
        //combine values if they're the same
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0; col--) {
                if (board[row][col] != null && board[row][col - 1] != null) {
                    if (board[row][col].getV() == board[row][col - 1].getV()) {
                        board[row][col] = new Tile(row, col, 2 * board[row][col - 1].getV());
                        board[row][col - 1] = null;
                    }
                }
            }
        }
        slideRight();
    }

    /**
     * slideRight board pieces to rightwards-most position following a key press;
     * a helper function to moveRight
     */
    public void slideRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 2; col >= 0; col--) {
                if (board[row][col] != null) {
                    if (col == 2) {
                        if (board[row][3] == null && board[row][col] != null) {
                            board[row][3] = new Tile(row, 3, board[row][col].getV());
                            board[row][col] = null;
                        }
                    } else if (col == 1 && board[row][col] != null) {
                        if (board[row][col + 1] == null && board[row][col + 2] == null) {
                            board[row][col + 2] = new Tile(row, col + 2, board[row][col].getV());
                            board[row][col] = null;
                        } else if (board[row][col + 1] == null && board[row][col + 2] != null) {
                            board[row][col + 1] = new Tile(row, col + 1, board[row][col].getV());
                            board[row][col] = null;
                        }
                    } else if (col == 0 && board[row][col] != null) {
                        if (board[row][col + 1] == null && board[row][col + 2] == null && board[row][col + 3] == null) {
                            board[row][col + 3] = new Tile(row, col + 3, board[row][col].getV());
                            board[row][col] = null;
                        } else if (board[row][col + 1] == null && board[row][col + 2] == null
                                && board[row][col + 3] != null) {
                            board[row][col + 2] = new Tile(row, col + 2, board[row][col].getV());
                            board[row][col] = null;
                        } else if (board[row][col + 1] == null && board[row][col + 2] != null
                                && board[row][col + 3] != null) {
                            board[row][col + 1] = new Tile(row, col + 1, board[row][col].getV());
                            board[row][col] = null;
                        }
                    }
                }
            }
        }
    }

    // **************************************************************************
    // * RANDOM TILE GENERATION
    // **************************************************************************

    /**
     * generateTile creates a new tile in a null tile of a grid
     */

    public void generateTile() {
        boolean noTileGenerated = true;
        double prob = Math.random();
        while (noTileGenerated) {
            int row = (int) (Math.random() * 4);
            int col = (int) (Math.random() * 4);
            if (board[row][col] == null) {
                if (prob < 0.5) {
                    board[row][col] = new Tile(row, col, 2);
                } else {
                    board[row][col] = new Tile(row, col, 4);
                }
                noTileGenerated = false;
            }
        }
    }


    /**
     * This main method illustrates how the model is completely independent of
     * the view and controller. We can play the game from start to finish
     * without ever creating a Java Swing object.
     *
     * This is modularity in action, and modularity is the bedrock of the
     * Model-View-Controller design framework.
     *
     * Run this file to see the output of this method in your console.
     */
    public static void main(String[] args) {

    }
}
