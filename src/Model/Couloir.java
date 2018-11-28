package Model;

public class Couloir extends Bloc{
	private boolean pacGum;
	private boolean superPacGum;
	public Couloir() {
	}
	
	public Couloir(boolean pacGum, boolean superPacGum) {
		this.pacGum = pacGum;
		this.superPacGum = superPacGum;
	}

	public boolean isPacGum() {
		return pacGum;
	}

	public void setPacGum(boolean pacGum) {
		this.pacGum = pacGum;
	}

	public boolean isSuperPacGum() {
		return superPacGum;
	}

	public void setSuperPacGum(boolean superPacGum) {
		this.superPacGum = superPacGum;
	}

	public String toString() {
		if(superPacGum) {
			return "0";
		}else if(pacGum) {
			return "o";
		}return " ";
	}
	
	
}
