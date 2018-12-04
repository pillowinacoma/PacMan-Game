package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

abstract public class Creature {
	private Direction currentDirection;
	private PacMode mode;
	

	public PacMode getMode() {
		return mode;
	}

	public void setMode(PacMode mode) {
		this.mode = mode;
	}

	public Creature() {
		final List<Direction> VALUES =
		Collections.unmodifiableList(Arrays.asList(Direction.values()));
		final int SIZE = VALUES.size();
		final Random RANDOM = new Random();
		Direction d = VALUES.get(RANDOM.nextInt(SIZE));
		this.currentDirection = d;
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
