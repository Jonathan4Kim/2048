package org.cis120.twentyFortyEight;

import org.cis120.twentyFortyEight.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunTwentyFortyEight implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("2048");
        frame.setLocation(0, 0);

        final JPanel m = new JPanel();
        frame.add(m);
        m.setLocation(0, 300);
        m.setLayout(new FlowLayout());

        final JLabel space = new JLabel("\n");

        final JLabel instructions = new JLabel("Welcome to 2048! Use keys a to move right, d to move left,");

        final JLabel instructions2 = new JLabel("w to move up, and s to move down! The goal is to combine");

        final JLabel instructions3 = new JLabel("like numbers using movements to get the number 2048. Remember,");

        final JLabel instructions4 = new JLabel("if you can't combine numbers and the board is filled without");

        final JLabel instructions5 = new JLabel("the 2048 block, you lose!  You can restart the game using the");

        final JLabel instructions6 = new JLabel("New Game Button.  Good Luck!");

        final JButton start = new JButton("Go Play");

        m.add(instructions);
        m.add(instructions2);
        m.add(instructions3);
        m.add(instructions4);
        m.add(instructions5);
        m.add(instructions6);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e1) {

                frame.remove(m);

                // Status panel
                final JPanel status_panel = new JPanel();
                frame.add(status_panel, BorderLayout.SOUTH);

                final JLabel status = new JLabel("Setting up...");
                status_panel.add(status);
                // Game board
                final Board board = new Board(status);
                frame.add(board, BorderLayout.CENTER);

                // Reset button
                final JPanel control_panel = new JPanel();
                frame.add(control_panel, BorderLayout.NORTH);

                // Note here that when we add an action listener to the reset button, we
                // define it as an anonymous inner class that is an instance of
                // ActionListener with its actionPerformed() method overridden. When the
                // button is pressed, actionPerformed() will be called.

                final JButton reset = new JButton("New Game");
                reset.addActionListener(e -> board.reset());
                control_panel.add(reset);

                final JButton upload = new JButton("Get Past Game");
                upload.addActionListener(e -> board.upload());
                control_panel.add(upload);

                final JButton save = new JButton("Save Game");
                save.addActionListener(e -> board.save());
                control_panel.add(save);

                final JButton undo = new JButton("Undo");
                undo.addActionListener(e -> board.undo());
                control_panel.add(undo);

                // Start the game
                board.reset();
                // Put the frame on the screen
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        m.add(start);
        frame.add(m);

    }
}
