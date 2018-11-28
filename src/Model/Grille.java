package Model;
import java.util.*;

public class Grille {
	private Bloc [][] grille;
	private Creature [][] creatures;
	private HashMap<Creature, Point> creaturesMap;
	private int n;
	private int m;

	public Grille(int n, int m) {
		this.n = n;
		this.m = m;
		this.grille = new Bloc[n][m];
		this.creatures = new Creature[n][m];
		this.creaturesMap = new HashMap<Creature,Point>();
		initGrille();initCreatures();		
	}
	
	public Bloc[][] getGrille() {
		return grille;
	}

	public void setGrille(Bloc[][] grille) {
		this.grille = grille;
	}
	
	public Creature[][] getCreatures() {
		return creatures;
	}

	public HashMap<Creature, Point> getCreaturesMap() {
		return creaturesMap;
	}

	public void setCreatures(Creature[][] creatures) {
		this.creatures = creatures;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public Bloc getCaseGrille(int i , int j) {
		return grille[i][j];
	}
	
	public void setCaseGrille(int i , int j ,Bloc b) {
		grille[i][j] = b;
	}

	public Bloc getCaseGrille(Point p) {
		int i = p.getI(), j = p.getJ();
		return grille[i][j];
	}
	
	public void setCaseGrille(Point p ,Bloc b) {
		int i = p.getI(), j = p.getJ();
		grille[i][j] = b;
	}

	public Creature getCaseCreature(int i , int j) {
		return creatures[i][j];
	}
	
	public void setCaseCreature(int i , int j ,Creature c) {
		creatures[i][j] = c;
		creaturesMap.put(c,new Point(i,j));
	}
	
	public Creature getCaseCreature(Point p) {
		int i = p.getI(), j = p.getJ();
		return creatures[i][j];
	}
	
	public void setCaseCreature(Point p, Creature c) {
		int i = p.getI(), j = p.getJ();
		creatures[i][j] = c;
		creaturesMap.put(c,new Point(i,j));
	}
	
	public Point getCreatureCoord(Creature c) {
		Point result = creaturesMap.get(c);
		if(result != null) {
			return result;
		}
		return null;
	}
	
	private void initGrille() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				grille[i][j] = new Couloir();
			}
		}
	}
	
	private void initCreatures() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < m ; j++) {
				creatures[i][j] = null;
			}
		}
	}

	public String toString() {
		String result = "";
		for(int i = 0 ; i < getN() ; i++) {
			for(int j = 0 ; j < getM() ; j++) {
				if(grille[i][j] != null)
					result += "| " + grille[i][j].toString() + " ";
				else
					result += "|   ";
			}
			result += "|\n";
		}
		result += "\n";
		for(int i = 0 ; i < getN() ; i++) {
			for(int j = 0 ; j < getM() ; j++) {
				if(creatures[i][j] != null)
					result += "| " + creatures[i][j].toString() + " ";
				else
					result += "|   ";
			}
			result += "|\n";
		}
		return result;
	}

	public static Direction picRandomDirection(Direction i){
		final List<Direction> VALUES =
				Collections.unmodifiableList(Arrays.asList(Direction.values()));
		final int SIZE = VALUES.size();
		final Random RANDOM = new Random();
		Direction d = VALUES.get(RANDOM.nextInt(SIZE));
		if(d == picOppositeDirection(i) || d == i){
			return picRandomDirection(i);
		}
		return d;
	}
	public static Direction picOppositeDirection(Direction i){
		Direction d = null;
		switch (i){
			case UP:
				d = Direction.DOWN;break;
			case DOWN:
				d = Direction.UP;break;
			case RIGHT:
				d = Direction.LEFT;break;
			case LEFT:
				d = Direction.RIGHT;break;
		}
		return d;
	}
	
	private void deplacerversCouloir(Point currentPos, Point nextPos, Creature c, Direction d) {
		if(c instanceof PacMan){
			if(getCaseGrille(nextPos) != null && getCaseGrille(nextPos) instanceof Couloir) {
				Couloir currCouloir = (Couloir)getCaseGrille(nextPos);
				if(currCouloir.isPacGum()) {
					((PacMan) c).eatGum();
					currCouloir.setPacGum(false);
				}
				if(currCouloir.isSuperPacGum()) {
					((PacMan) c).eatSuperGum();
					currCouloir.setSuperPacGum(false);
				}
			}
//			(getCaseCreature(nextPos) != null && getCaseCreature(nextPos) instanceof Fantom) {
//				if(((PacMan) c).getMode() == PacMode.REGULAR) {
//					((PacMan) c).killed();
//					/*to finish the game properly, you gotta kill this in the dataset, this will be usefull when playing with more than one player  */
//				}else if(((PacMan) c).getMode() == PacMode.SUPER) {
//					((PacMan) c).eatFantom();
//					//TODO il faut modifier cette partie pour que le fantome se deplace à sa position initiale et recommence le jeu en mode regular au lieu de le supprimer fdp
//					creaturesMap.remove(getCaseCreature(nextPos));
//					setCaseCreature(nextPos, null);
//				}
//			}
		}
		else if(c instanceof Fantom){
			if(getCaseGrille(nextPos) != null && getCaseGrille(nextPos) instanceof Mur) {
				Couloir currCouloir = (Couloir)getCaseGrille(nextPos);
				int coin = (int)(Math.random() * 10);
				if(coin == 0 || coin == 1){
					c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
				}else{
					c.setCurrentDirection(picRandomDirection(c.getCurrentDirection()));
				}
				deplacerCreature(c,c.getCurrentDirection());
			}
			if(getCaseGrille(nextPos) != null && getCaseCreature(nextPos) != null) {
				Couloir currCouloir = (Couloir)getCaseGrille(nextPos);
				int coin = (int)(Math.random() * 10);
				if(coin == 0 || coin == 1){
					c.setCurrentDirection(picRandomDirection(c.getCurrentDirection()));
				}else{
					c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
				}
				deplacerCreature(c,c.getCurrentDirection());
			}
		}
	}

	public Point deplacerCreature(Creature c, Direction d) {
		//you should take care of the case where get(c) returns null;
		Point currentPos = creaturesMap.get(c);
		Point nextPos = currentPos;
			switch(d) {
			case UP:
				if(!(this.getCaseGrille(currentPos.getI()-1, currentPos.getJ()) instanceof Mur)) {
					nextPos = new Point(currentPos.getI()-1, currentPos.getJ());
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case RIGHT:
				if(!(this.getCaseGrille(currentPos.getI(), currentPos.getJ()+1) instanceof Mur)) {
					nextPos = new Point(currentPos.getI(), currentPos.getJ()+1);
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case LEFT:
				if(!(this.getCaseGrille(currentPos.getI(), currentPos.getJ()-1) instanceof Mur)) {
					nextPos = new Point(currentPos.getI(), currentPos.getJ()-1);
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case DOWN:
				if(!(this.getCaseGrille(currentPos.getI()+1, currentPos.getJ()) instanceof Mur)) {
					nextPos = new Point(currentPos.getI()+1, currentPos.getJ());
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}break;
			}
		return nextPos;
	}
}
