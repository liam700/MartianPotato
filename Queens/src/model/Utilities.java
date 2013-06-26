package model;

public class Utilities {

	public static void findNextSolution(Board b) {
		for (int row = b.getBoardSize() - 1; row > -1; row--) {
			Tile[] rowTiles = b.getBoard()[row];
			if (findOccupiedColumn(rowTiles) == -1)
				continue;
			else {
				propagate(b, row, findOccupiedColumn(rowTiles));
				return;
			}
		}
		propagate(b, 0, 0);
	}

	public static int findOccupiedColumn(Tile[] row) {
		for (int col = 0; col < row.length; col++) {
			if (row[col].getOccupied())
				return col;
		}
		return -1;
	}
	
	private static void propagate(Board b, int row, int col) {
		int y = row;
		while(y > -1 && y < b.getBoardSize()) {
			if (findOccupiedColumn(b.getBoard()[y]) > -1) {
				col = findOccupiedColumn(b.getBoard()[y]);
				b.getBoard()[y][col].switchOccupied();
				col++;
			}
			for (int x = col; x < b.getBoardSize(); x++) {
				if(y >= b.getBoardSize())
					break;
				if (checkAttack(b, y, x, -1, -1)
						|| checkAttack(b, y, x, -1, 0)
						|| checkAttack(b, y, x, -1, 1)) {
					continue;
				} else {
					b.getBoard()[y][x].switchOccupied();
					y++;
					x = -1;
				}
			}
			if(findOccupiedColumn(b.getBoard()[b.getBoardSize() - 1]) > -1)
				continue;
			y--;
		}
	}

	private static boolean checkAttack(Board b, int row, int col, int rowDif,
			int colDif) {
		Tile[][] board = b.getBoard();
		int currRow = rowDif + row;
		int currCol = colDif + col;
		if ((currRow < 0) || currCol < 0 || currCol >= b.getBoardSize())
			return false;
		for (int offset = 1; (currRow >= 0)
				|| (currCol >= 0 || currCol < b
						.getBoardSize()); offset++) {
			currRow = offset * rowDif + row;
			currCol = offset * colDif + col;
			if ((currRow < 0) || currCol < 0 || currCol >= b.getBoardSize())
				return false;
			if (board[offset * rowDif + row][offset * colDif + col]
					.getOccupied())
				return true;
		}
		return false;
	}

}
