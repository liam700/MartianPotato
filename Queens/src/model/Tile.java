package model;

import java.awt.*;

public class Tile {
	
	private Color color;
	private boolean occupied;
	
	public Tile(Color color) {
		this.color = color;
		occupied = false;
	}
	
	public Color getColor() {
		return color;
	}
	
	public boolean getOccupied() {
		return occupied;
	}
	
	public void switchOccupied() {
		if (occupied)
			occupied = false;
		else
			occupied = true;
	}
	
	public Tile opposite() {
		if(this.color == Color.WHITE)
			return new Tile(Color.BLACK);
		else 
			return new Tile(Color.WHITE);
	}
}
