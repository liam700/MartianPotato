package model;

import java.awt.Color;

public class Board {
	private Tile[][] board;
	
	public Board(int size) {
		board = new Tile[size][size];
		board[0][0] = new Tile(Color.WHITE);
		for(int row = 0; row < size; row++) {
			for(int col = 0; col < size; col++) {
				if(row == 0 && col == 0)
					continue;
				if(col == 0)
					board[row][col] = board[row - 1][col].opposite();
				else 
					board[row][col] = board[row][col - 1].opposite();
			}
		}
	}
	
	public int getBoardSize() {
		return board.length;
	}
	
	public Tile getTile(int row, int col) {
		return board[row][col];
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
}
