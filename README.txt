=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
Checkers Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

  1. 2D arrays: The 2d array will probably best be implemented in the checkers class,
  where I'll store the checkerboard, with each piece object being a portion of the 2d array.  A piece object would be
  created in a separate class

  2. File I/O: I used the File I/O to save the state of a game, store it into a csv file of some sort
  through writing, and then be able to retrieve the state of the game and rewrite it when it is possible.
  Basically, a save game button through File I/O would be implemented.

  3. Collections: implement undo functionality with the linkedlist (basically for each move a person makes,
  save the move or the game board in a list of moves, then when they
  press the undo button you can remove the head/tail of the list and "redo" the last move).

  4. JUnit Testing:  I tested my Piece Object's getters and setters, as well as the many methods that exist in my
  checkers class.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  1. Board.java
  A class where we implement our JPanel, our mouseListeners, and our getPreferredSize and paintComponent.
  We have a reset and updateStatus method that resets the game board and updates our Jlabel to denote the turns.
  We also have a paintComponent, where we draw the board in Swing, the checkered pattern of the board,
  and our non-null pieces that remain on the board.  Kings are denoted with a big K (when applicable) while nonKing
  pieces are just regular nonfilled oval pieces.  Player 1 is Red and Player 2 is Blue.
  Our getPreferredSize() method returns our board width and height.

  2. Moves.java
  A simple object that stores a piece, and coordinates to a move.  I would be using this to create potential moves
  for a given piece in movement methods, to see if it is in fact a valid move.

  3. Piece.java
  The object that would fill the 2d board array.  Piece stored a lot of things:
  - position in array (row, column)
  - width and height (for drawing ovals)
  - a boolean isPlayer1 (to see which player the piece belonged to)
  - a boolean isKing (to see if the piece was a king, which affected movement and drawing).

  I have getters and setters for positions, and isKing (as the player the piece belongs to doesn't change).

  4. Checkers.java
  Where the brunt work is done.  a Checkers object stores:
  - reset(), which stores:
    - A piece double array which is our board
    - an int that stores the number of turns
    - booleans for whether the game is over, whose turn it is, and if a game is going on, respectively
  Checkers also has several methods, like checking for a winner, playing a turn, getters for pieces and
  booleans, as well as various movement functions, validating moves or seeing if they're even possible, as well as
  actually moving pieces when it is ok to do so.

  5. RunCheckers.java
  Where we run all of our code from Checkers, implementing the Runnable Interface and drawing our Swing
  interface that our user will eventually be using to play checkers with it.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  Movement was definitely the hardest part, not only in implementation, but in design.  I didn't know where the best
  place to put all movement functions in.  I considered putting it in the board class, but I found it difficult to
  access the actual board and change it unless it was in the checkers class, which I eventually did.  Furthermore,
  I had a lot of problems with swing in terms of mouse listeners and what I want to do with those mouse listeners.
  Did I want to show the user all possible moves and highlight them and have them pick?  Did I want the user to
  click first a piece that they could, and then select a place they could move?  What about double jumps?  These were
  my biggest stumbling blocks of my game.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think private state is well encapsulated because of the getters and setters in the checkers, Piece, and Moves
  classes.  I can't change the double Piece array that is the board in my actual Board class, so I think the board
  is overall well encapsulated.  However, in terms of movement, I think that it might have been better to create
  a move class of some sort or to figure out a better way of putting movement instead of putting it in the checkers
  class.  Maybe in the board class would have been better, and that might be something I would have refactored.
  Also, instead of creating a piece class, I thought recently of using an int double array and having values
  Like a player 1 King and Player 2 King and player 1 regular piece and a player 2 regular piece taking on different
  values in the array.  Instead of null, I would have put 0.  It might have made working with things a lot easier,
  but I'm not too sure.  But, I would have thought that would have been easier than a piece class and all the other
  things I had to do.  I would refactor if I could.

========================
=: External Resources :=
========================

  1. https://en.wikipedia.org/wiki/Checkers <- How to play checkers, I didn't know how to play
  2. https://www.nctm.org/Publications/TCM-blog/Blog/How-Many-Squares-on-a-Checkerboard_/, to see checkerboard
  and what it looks like
  3. The Java Font page online; I wanted to draw a K as my king piece, and needed to learn it for the string part
  and setting an appropriate size.
