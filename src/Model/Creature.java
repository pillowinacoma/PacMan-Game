package Model;

abstract public class Creature/*imlements runnable*/ {
	private Direction currentDirection;
	private PacMode mode;
	

	public PacMode getMode() {
		return mode;
	}

	public void setMode(PacMode mode) {
		this.mode = mode;
	}

	public Creature() {
		this.currentDirection = Direction.UP;
	}
	
	public Creature(Direction dir) {
		this.currentDirection = dir;
	}
	
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}
	
	public String toString() {
		return "C";
	}
}
