package org.cis120.twentyFortyEight;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

/**
 * This class instantiates a 2048 object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 *
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 *
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class Board extends JPanel {

    private final twentyFortyEight tfe; // model for the game
    private final JLabel status; // current status text
    private final LinkedList<Tile[][]> pastBoards;
    // Game constants
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;

    /**
     * Initializes the game board.
     */
    public Board(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        tfe = new twentyFortyEight(); // initializes model for the game
        status = statusInit; // initializes the status JLabel
        pastBoards = new LinkedList<Tile[][]>();

        //KeyListeners
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == 'a' || c == 'A') {
                    tfe.moveLeft();
                    if (tfe.checkWin()) {
                        status.setText("You Won!");
                    } else if (tfe.checkLoss()) {
                        status.setText("You Lost :(");
                    } else  {
                        tfe.generateTile();
                        updateStatus(); // updates the status JLabel
                        repaint(); // repaints the game board
                    }
                } else if (c == 'w' || c == 'W') {
                    tfe.moveUp();
                    if (tfe.checkWin()) {
                        status.setText("You Won!");
                    } else if (tfe.checkLoss()) {
                        status.setText("You Lost :(");
                    } else  {
                        pastBoards.add(tfe.getBoard());
                        tfe.generateTile();
                        updateStatus(); // updates the status JLabel
                        repaint(); // repaints the game board
                    }
                } else if (c == 's' || c == 'S') {
                    tfe.moveDown();
                    if (tfe.checkWin()) {
                        status.setText("You Won!");
                    } else if (tfe.checkLoss()) {
                        status.setText("You Lost :(");
                    } else  {
                        pastBoards.add(tfe.getBoard());
                        tfe.generateTile();
                        updateStatus(); // updates the status JLabel
                        repaint(); // repaints the game board
                    }
                } else if (c == 'd' || c == 'D') {
                    tfe.moveRight();
                    if (tfe.checkWin()) {
                        status.setText("You Won!");
                    } else if (tfe.checkLoss()) {
                        status.setText("You Lost :(");
                    } else  {
                        pastBoards.add(tfe.getBoard());
                        tfe.generateTile();
                        updateStatus(); // updates the status JLabel
                        repaint(); // repaints the game board
                    }
                }
                tfe.incNumTurns();
            }
        });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        tfe.reset();
        status.setText("Still Alive!");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        status.setText("Number of Moves: " + tfe.getNumTurns());
    }

    /**
     * Draws the game board.
     *
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws vertical board components
        g.drawLine(100, 100, 100, 500);
        g.drawLine(200, 100, 200, 500);
        g.drawLine(300, 100, 300, 500);
        g.drawLine(400, 100, 400, 500);
        g.drawLine(500, 100, 500, 500);

        // Draws horizontal board components
        g.drawLine(100, 100, 500, 100);
        g.drawLine(100, 200, 500, 200);
        g.drawLine(100, 300, 500, 300);
        g.drawLine(100, 400, 500, 400);
        g.drawLine(100, 500, 500, 500);


        // Draws Tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tfe.getTile(i, j) != null) {
                    int val = tfe.getTile(i, j).getV();
                    Color boxCol = tfe.getTile(i, j).getBoxColor();
                    g.setColor(boxCol);
                    g.fillRect(100 + 100 * j, 100 + 100 * i, 100, 100);
                    g.setColor(Color.WHITE);
                    if (val == 2 || val == 4 || val == 8) {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 75));
                        g.drawString("" + val, 130 + 100 * j, 175 + 100 * i);
                    } else if (val == 16 || val == 32 || val == 64) {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
                        g.drawString("" + val, 115 + 100 * j, 175 + 100 * i);
                    } else if (val == 128 || val == 256 || val == 512) {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
                        g.drawString("" + val, 105 + 100 * j, 175 + 100 * i);
                    } else {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
                        g.drawString("" + val, 101 + 100 * j, 175 + 100 * i);
                    }
                }
            }
        }
    }

    public void undo() {
        Tile[][] lastBoard = pastBoards.getFirst();
        tfe.setBoard(lastBoard);
    }

    public void redo() {

    }

    public void save() {
        Tile[][] saveBoard = tfe.getBoard();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

            }
        }
    }

    public void upload() {

    }
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
