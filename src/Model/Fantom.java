package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * a fantom can attack a pacman when he's in regular mode, but when pacman is in super mode he can't attack him but he can be attacked
 * after an attack by pacman a fantom returns to his initial position and starts again in reegular mode */
public class Fantom extends Creature{

	public Fantom() {
		super();
		final List<Direction> VALUES =
				Collections.unmodifiableList(Arrays.asList(Direction.values()));
		final int SIZE = VALUES.size();
		final Random RANDOM = new Random();
		Direction d = VALUES.get(RANDOM.nextInt(SIZE));
		this.setCurrentDirection(d);
	}
	public Fantom(Direction d){
		super(d);
	}

	@Override
	public String toString() {
		return "F";
	}

}
