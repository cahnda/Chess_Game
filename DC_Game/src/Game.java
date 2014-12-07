
// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes inmutability
		// even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		final JFrame frame = new JFrame("David Cahn's Chess Game");
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.NORTH);
		final JLabel status = new JLabel("White's Turn");
		status_panel.add(status);
		
		// Main playing area
		GameBoard court;
		court = new GameBoard(status);
		frame.add(court, BorderLayout.CENTER);

		//Create the Control Panel
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.SOUTH);

		//Add easy-to-find instructions
		final JButton instructions = new JButton("Instructions");
		instructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog
				(frame, "Welcome to David's Chess Game!"
						+ "\n"
						+ "\n"
						+ "My game is chess and all of the rules are the"
						+ "standard chess rules. "
						+ "\n"
						+ "If you don't know how to play"
						+ "chess, please read the Wikipedia page."
						+ "\n"
						+ "\n"
						+ "All functionality is implemented using the mouse. "
						+ "\n"
						+ "Click on a piece and the possible places where it can"
						+ "be moved will be highlighted in green. "
						+ "\n"
						+ "Click on a "
						+ "highlighted square to move the piece to that square."
						+ "\n"
						+ "\n"
						+ "To win the game, you must checkmate your opponent."
						+ "\n"
						+ "Like regular chess games, checkmate means that the "
						+ "king cannot avoid attack "
						+ "\n"
						+ "and happens when a player "
						+ "acknowledges that they cannot move and ends the game."
						+ "\n"
						+ "\n"
						+ "Because I am an avid chess fan, I did not implement "
						+ "additional features to change the game, "
						+ "\n"
						+ "but did include"
						+ "all of the cool features in the game already, such as"
						+ "castling, queen pawning, and stalemate."
						+ "", "Instructions",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		control_panel.add(instructions);
		
		// Reset button
		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.reset();
			}
		});
		control_panel.add(reset);
		
		//Add the checkmate button to the screen
		final JButton checkmate = new JButton("I Resign");
		checkmate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.checkmate();
			}
		});
		control_panel.add(checkmate);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
