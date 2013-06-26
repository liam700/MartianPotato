package view_controller;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import model.*;

public class QueensGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	public static int CELL_SIZE = 25;
	private static JFrame jFrame;
	private JTextField boardSize;
	private JPanel controlPanel;
	private JPanel layoutPanel = new JPanel(new BorderLayout());
	private JPanel gridPanel = new JPanel();
	private Board chessBoard;
	private Grid boardDrawing;
	private JLabel warningLabel = new JLabel(" ");
	private GridBagConstraints gridCons = new GridBagConstraints();
	private Font unicode = new Font("Arial Unicode MS", Font.BOLD, 15);
	
	
	public QueensGUI(Board paramChessBoard) {
		chessBoard = paramChessBoard;
	}
	
	private void buildPanels() {
		controlPanel = new JPanel();
		
		boardSize = new JTextField(10);
		controlPanel.add(new JLabel("Board Size: "));
		controlPanel.add(boardSize);
		
		Button reSize = new Button("Reset the board");
		controlPanel.add(reSize);
		reSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				if (badFieldVals()) {
					layoutPanel.remove(warningLabel);
					if (!boardSize.getText().equals(" ")) {
						warningLabel = new JLabel("The Board Size must be a" +
								" positive integer. ");
					}
					layoutPanel.add(warningLabel, BorderLayout.NORTH);
					jFrame.pack();
					return;
				} 
				layoutPanel.remove(warningLabel);
				warningLabel.setText(" ");
				layoutPanel.add(warningLabel, BorderLayout.NORTH);
				gridPanel.remove(boardDrawing);
				chessBoard = new Board(Integer.parseInt(boardSize.getText()));
				boardDrawing = new Grid(chessBoard);
				gridPanel.add(boardDrawing);
				jFrame.pack();
			}
		});
		
		Button findNextSolution = new Button("Find the next solution");
		controlPanel.add(findNextSolution);
		findNextSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				gridPanel.remove(boardDrawing);
				Utilities.findNextSolution(chessBoard);
				boardDrawing = new Grid(chessBoard);
				gridPanel.add(boardDrawing);
				layoutPanel.repaint();
				jFrame.pack();
			}
		});
		
		
		layoutPanel.add(controlPanel, BorderLayout.SOUTH);
		
		boardDrawing = new Grid(chessBoard);
		gridPanel.setLayout(new GridBagLayout());
		gridPanel.add(boardDrawing, gridCons);
		layoutPanel.add(gridPanel, BorderLayout.CENTER);
		
		layoutPanel.add(warningLabel, BorderLayout.NORTH);
		
		layoutPanel.setFont(unicode);
		gridPanel.setFont(unicode);
		
		
		
	}
	
	private boolean badFieldVals() {
		try {
			Integer.parseInt(boardSize.getText());
		} catch (NumberFormatException e) {
			return true;
		} 
		if (Integer.parseInt(boardSize.getText()) < 1)
			return true;
		return false;
	}
	
	private class Grid extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6254820289602032694L;

		public Grid(Board board) {
			
			setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
			gridCons.gridx = board.getBoardSize();
			gridCons.gridy = board.getBoardSize();
			
			JLabel square;
			
			for (int row = 0; row < chessBoard.getBoardSize(); row++) {
				for (int col = 0; col < chessBoard.getBoardSize(); col++) {
					square = new JLabel();
					square.setFont(unicode);
					square.setHorizontalAlignment(JLabel.CENTER);
					square.setOpaque(true);
					if(row == 0 && col == 0){
						square.setBackground(Color.WHITE);
					}
					else if (col == 0) {
						square.setBackground(board.getTile(row - 1, col).opposite().getColor());
					}
					else {
						square.setBackground(board.getTile(row, col - 1).opposite().getColor());
					} 
					if(board.getTile(row, col).getOccupied()) {
						square.setText("\u265B");
						if(square.getBackground() == Color.WHITE) {
							square.setForeground(Color.BLACK);
						}
						else if (square.getBackground() == Color.BLACK) {
							square.setForeground(Color.WHITE);
						}
					}
					add(square);
				}
				
			}
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(chessBoard.getBoardSize() * CELL_SIZE, 
					chessBoard.getBoardSize() * CELL_SIZE);
		}
	}
	
	private void setBackgroundColor(Color c) {
		layoutPanel.setBackground(c);
		gridPanel.setBackground(c);
		controlPanel.setBackground(c);
	}
	
	public static void main(String[] args) {
		QueensGUI newGui = new QueensGUI(new Board(8));
		newGui.buildPanels();
		// I was going for a finished mahogany or redwood color. 
		newGui.setBackgroundColor(Color.getHSBColor(.07F, 1.0F, .81F));
		
		jFrame = new JFrame("N Queens");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(newGui.layoutPanel);
		// had this in originally to set the window size, but it doesn't 
		// resize to keep the board square when the board gets large. 
//		jFrame.setPreferredSize(new Dimension(650, 450));
		jFrame.setFont(newGui.unicode);
		jFrame.pack();
		jFrame.setVisible(true);
		
	}
}
