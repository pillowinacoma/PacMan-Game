package Model;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Grille extends Observable implements Runnable{
	private Bloc [][] grille;
	private Creature [][] creatures;
	private HashMap<Creature, Point> creaturesMap;
	private int n;
	private int m;
	private PacMan pacman;
	private Fantom [] fantoms;
	private long SPEED = 500;
	static int[][] plateau={
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 2, 0, 0, 0, 0, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 1},
			{1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
			{1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1},
			{1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1},
			{1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
			{1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
	};

	public Grille(int n, int m) {
		this.n = n;
		this.m = m;
		this.grille = new Bloc[n][m];
		this.creatures = new Creature[n][m];
		this.creaturesMap = new HashMap<Creature,Point>();
		initGrille();initCreatures();
	}
	public Grille() {
		this.n = plateau.length;
		this.m = plateau[0].length;
		this.grille = new Bloc[n][m];
		this.creatures = new Creature[n][m];
		this.creaturesMap = new HashMap<Creature,Point>();
		initGrille();initCreatures();
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < getM() ; j++){
				switch(plateau[i][j]){
					case 0:break;
					case 1:setCaseGrille(i, j, new Model.Mur());break;
					case 2:((Couloir) getCaseGrille(i,j)).setPacGum(true);break;//normalement PacGum
					case 3:((Couloir) getCaseGrille(i,j)).setSuperPacGum(true);break;//normalement SuperPacGum
					//case 4:setCaseCreature(i, j, new Model.PacMan());break;
					//case 5:setCaseCreature(i, j, new Model.Fantom());break;
				}
			}
		}
                
		this.pacman = new PacMan();
		setCaseCreature(23,13,pacman);
		this.fantoms = new Fantom[4];
		int i1 = 0 , j1 = 0;
		for (int i = 0 ; i < 4 ; i++){
			fantoms[i] = new Fantom();
                        do{
                            i1 = new Random().nextInt(n);
                            j1 = new Random().nextInt(m);
                            System.out.println("i1 :"+i1 + " j1 :" + j1);
                        }while(!(getCaseGrille(i1,j1) instanceof Couloir || getCaseCreature(i1, j1) instanceof PacMan));
			setCaseCreature(i1,j1,fantoms[i]);
		}
	}

	public PacMan getPacman() {
		return pacman;
	}

	public Fantom[] getFantoms() {
		return fantoms;
	}

	public static int[][] getPlateau() {
		return plateau;
	}

	public void setCreaturesMap(HashMap<Creature, Point> creaturesMap) {
		this.creaturesMap = creaturesMap;
	}

	public void setPacman(PacMan pacman) {
		this.pacman = pacman;
	}

	public void setFantoms(Fantom[] fantoms) {
		this.fantoms = fantoms;
	}

	public static void setPlateau(int[][] plateau) {
		Grille.plateau = plateau;
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
        for(int j = 0 ; j < getM() ; j++) {
            for(int i = 0 ; i < getN() ; i++) {
				if(grille[i][j] != null)
                    if(creatures[i][j] == null)result += "| " + grille[i][j].toString() + " ";
                    else result += "| " + creatures[i][j].toString() + " ";
				else
					result += "|   ";
			}
			result += "|\n";
		}
		result += "\n";/*
        for(int j = 0 ; j < getM() ; j++) {
            for(int i = 0 ; i < getN() ; i++) {
				if(creatures[i][j] != null)
					result += "| " + creatures[i][j].toString() + " ";
				else
					result += "|   ";
			}
			result += "|\n";
		}*/
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
	public static void dirToNextPos(Point p, Direction d){
	    switch (d){
            case UP:
                p.setJ(p.getJ()-2);/*TODO ajouter modulo pour permetre le truc infini*/
                break;
            case DOWN:
                p.setJ(p.getJ()+2);break;
            case LEFT:
                p.setI(p.getI()-2);break;
            case RIGHT:
                p.setI(p.getI()+2);break;
        }
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
			if(getCaseCreature(nextPos) != null && getCaseCreature(nextPos) instanceof Fantom) {
				if(((PacMan) c).getMode() == PacMode.REGULAR) {
					((PacMan) c).killed();
					/*to finish the game properly, you gotta kill this in the dataset, this will be usefull when playing with more than one player  */
				}else if(((PacMan) c).getMode() == PacMode.SUPER) {
					((PacMan) c).eatFantom();
					//TODO il faut modifier cette partie pour que le fantome se deplace à sa position initiale et recommence le jeu en mode regular au lieu de le supprimer fdp
					creaturesMap.remove(getCaseCreature(nextPos));
					setCaseCreature(nextPos, null);
				}
			}
		}
		else if(c instanceof Fantom){
		    if(getCaseGrille(nextPos) != null){
                if(getCaseGrille(nextPos) instanceof Mur){
                    int coin = (int)(Math.random() * 3);
                    if(coin == 0){
                        c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
                    }else{
                        c.setCurrentDirection(picRandomDirection(c.getCurrentDirection()));
                    }
                }else if(getCaseGrille(nextPos) instanceof Couloir){
                    if(getCaseCreature(nextPos) instanceof Fantom){
                        c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
                    }else if(getCaseCreature(nextPos) instanceof PacMan){
                        PacMan pc = (PacMan)getCaseCreature(nextPos);
                        if(pc.getMode() == PacMode.REGULAR){
                            setCaseCreature(getCreatureCoord(c),null);
                            System.out.println("THE GAME COULD HAVE BEEN OVER BUT I'M CANADIAN");
                        }else if(pc.getMode() == PacMode.SUPER){
                            setCaseCreature(getCreatureCoord(c),null);
                        }
                    }
                }
                dirToNextPos(nextPos,c.getCurrentDirection());
            }
        }
	}

	public Point deplacerCreature(Creature c, Direction d) {
		//you should take care of the case where get(c) returns null;
		Point currentPos = creaturesMap.get(c);
		Point nextPos = new Point(0,0);
			switch(d) {
			case UP:
				if(this.getCaseGrille(currentPos.getI(), currentPos.getJ()-1) != null && !(this.getCaseGrille(currentPos.getI(), currentPos.getJ()-1) instanceof Mur)) {
                    nextPos = new Point(currentPos.getI(), currentPos.getJ()-1);
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case RIGHT:
				if(this.getCaseGrille(currentPos.getI()+1, currentPos.getJ()) != null && !(this.getCaseGrille(currentPos.getI()+1, currentPos.getJ()) instanceof Mur)) {
					nextPos = new Point(currentPos.getI()+1, currentPos.getJ());
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case LEFT:
				if(this.getCaseGrille(currentPos.getI()-1, currentPos.getJ()) != null && !(this.getCaseGrille(currentPos.getI()-1, currentPos.getJ()) instanceof Mur)) {
					nextPos = new Point(currentPos.getI()-1, currentPos.getJ());
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}
				break;
			case DOWN:
				if(this.getCaseGrille(currentPos.getI(), currentPos.getJ()+1) != null && !(this.getCaseGrille(currentPos.getI(), currentPos.getJ()+1) instanceof Mur)) {
					nextPos = new Point(currentPos.getI(), currentPos.getJ()+1);
					deplacerversCouloir(currentPos, nextPos, c, d);
					creaturesMap.put(c, nextPos);
					creatures[currentPos.getI()][currentPos.getJ()] = null;
					creatures[nextPos.getI()][nextPos.getJ()] = c;
				}break;
			}
		return nextPos;
	}
        
        public void start(){
		new Thread(this).start();
	}

	@Override
	public void run() {
		while(true){
			deplacerCreature(pacman,pacman.getCurrentDirection());
			for (int i = 0 ; i < 4 ; i++){
				deplacerCreature(fantoms[i],fantoms[i].getCurrentDirection());
			}
			setChanged();
			notifyObservers();
                        
                        try {
                            Thread.sleep(300); // pause
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Grille.class.getName()).log(Level.SEVERE, null, ex);
                        }
		}
	}
}

