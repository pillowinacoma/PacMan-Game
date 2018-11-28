package Model;
/*
 * Pacman is defined by the ability to eat pacgums, and his pacMode, and if he's dead or alive too ?
 * */
public class PacMan extends Creature{
	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PacMan() {
		score = 0;
		this.setMode(PacMode.REGULAR);
	}

	public void eatGum() {
		score++;
	}

	public void eatSuperGum() {
		score+=10;
		this.setMode(PacMode.SUPER);
	}
	
	public void eatFantom() {
		score+=5;
	}
	
	public void killed() {
		System.out.println("chaoui ");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "P";
	}
	
}
