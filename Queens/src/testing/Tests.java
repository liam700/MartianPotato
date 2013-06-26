package testing;

import model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.*;

public class Tests {
	
	@Test
	public void testTileConstructor() {
		Tile testTile = new Tile(Color.WHITE);
		
		assertTrue(testTile.getColor() == Color.WHITE);
		
		Tile testTileBlack = new Tile(Color.BLACK);
		
		assertTrue(testTileBlack.getColor() == Color.BLACK);
		
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
//		Font[] allFonts = ge.getAllFonts();
		
//		System.out.println("So many fonts!");
		
		
	}
	
	@Test
	public void testBoardConstructor() {
		System.out.println("\u265A");
	}
	
	@Test
	public void testGUIConstructor() {
		
	}
	
	@Test
	public void testBoardGetters() {
		
	}
	
	@Test
	public void testLogicFirstSolution() {
		Board b = new Board(4);
		
		Utilities.findNextSolution(b);
		
		printBoard(b);
	}
	
//	@Test
//	public void testLogicSecondSolution() {
//		Board b = new Board(5);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//		
//		Utilities.findNextSolution(b);
//		printBoard(b);
//	}
	
	private void printBoard(Board b) {
		if(Utilities.findOccupiedColumn(b.getBoard()[b.getBoardSize() - 1]) == -1) {
			System.out.println("No more solutions. ");
			System.out.println();
			return;
		}
		
		for(int row = 0; row < b.getBoardSize(); row++) {
			for(int col = 0; col < b.getBoardSize(); col++) {
				if (b.getBoard()[row][col].getOccupied())
					System.out.print(" Q");
				else 
					System.out.print(" *");
			}
			System.out.println();
		}
		System.out.println();
	}
}
