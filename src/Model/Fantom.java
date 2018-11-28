package Model;
/*
 * a fantom can attack a pacman when he's in regular mode, but when pacman is in super mode he can't attack him but he can be attacked
 * after an attack by pacman a fantom returns to his initial position and starts again in reegular mode */
public class Fantom extends Creature{

	public Fantom() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "F";
	}

}
