package edu.binghamton.my;

public class Position {
	private final int x, y;
    private Position next;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

	public Position getNext() {
		return next;
	}

	public void setNext(Position next) {
		this.next = next;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
 
}
