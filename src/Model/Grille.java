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
	private int superTimer = 0;
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
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 0, 0, 0, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0, 0, 1, 1, 0, 0, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 2, 2, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0, 0, 1, 1, 0, 0, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 0, 0, 0, 0, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1},
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
			fantoms[i] = new Fantom(Direction.RIGHT);
		}
		setCaseCreature(13,12,fantoms[0]);
		setCaseCreature(13,14,fantoms[1]);
		setCaseCreature(15,12,fantoms[2]);
		setCaseCreature(15,14,fantoms[3]);
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
    public void currentToNextPos(Point nextPos, Creature c){
		Point currentPos = creaturesMap.get(c);
		creaturesMap.put(c, nextPos);
		creatures[nextPos.getI()][nextPos.getJ()] = c;
		creatures[currentPos.getI()][currentPos.getJ()] = null;
	}
	public Bloc dirToBloc(Creature c, Direction d){
		Point p = null;
		Point currentPos = getCreatureCoord(c);
		switch(d) {
			case UP:
				p = new Point(currentPos.getI(), currentPos.getJ()-1);
				break;
			case RIGHT:
				p = new Point(currentPos.getI()+1, currentPos.getJ());
				break;
			case LEFT:
				p = new Point(currentPos.getI()-1, currentPos.getJ());
				break;
			case DOWN:
				p = new Point(currentPos.getI(), currentPos.getJ()+1);
				break;
		}
		return getCaseGrille(p);
	}
	public boolean isInTheMiddle(Point p){
		if(p.getI() >= 12 && p.getJ() <=16 && p.getI()>=11 && p.getI()<=17)
			return  true;
		return false;
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
					c.setMode(PacMode.SUPER);
					for (int i = 0 ; i < 4 ; i++){
						fantoms[i].setMode(PacMode.SUPER);
					}
				}
				currentToNextPos(nextPos,c);
			}
			if(getCaseCreature(nextPos) != null && getCaseCreature(nextPos) instanceof Fantom) {
				if(((PacMan) c).getMode() == PacMode.REGULAR) {
					System.exit(10);
					/*to finish the game properly, you gotta kill this in the dataset, this will be usefull when playing with more than one player  */
				}else if(((PacMan) c).getMode() == PacMode.SUPER) {
					if(getCaseCreature(nextPos).getMode() == PacMode.SUPER){
						currentToNextPos(new Point(1,1),getCaseCreature(nextPos));
					}
				}
				//deplacerCreature(c,c.getCurrentDirection());
			}
		}
		else if(c instanceof Fantom){
		    if(getCaseGrille(nextPos) != null){
                if(getCaseGrille(nextPos) instanceof Mur){
                	do{
						int coin = (int)(Math.random() * 3);
						if(coin == 0){
							c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
						}else{
							c.setCurrentDirection(picRandomDirection(c.getCurrentDirection()));
						}
                	}while (!(dirToBloc(c,c.getCurrentDirection()) instanceof Couloir));
                }
                else if(getCaseGrille(nextPos) instanceof Couloir){
                    if(getCaseCreature(nextPos) != null && getCaseCreature(nextPos) instanceof Fantom){
						c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
                    }else if(getCaseCreature(nextPos) != null && getCaseCreature(nextPos) instanceof PacMan){

					}else if(getCaseCreature(nextPos) == null){
						currentToNextPos(nextPos,c);
					}
					List<Direction> dir = Collections.unmodifiableList(Arrays.asList(Direction.values()));
                    int cmp = 0;
                    for(Direction iter : dir){
                    	if(dirToBloc(c,iter) instanceof Couloir)
						cmp++;
					}
                    if(cmp>2 && !isInTheMiddle(currentPos)){
						do{
							int coin = (int)(Math.random() * 3);
							if(coin == 0){
								c.setCurrentDirection(picOppositeDirection(c.getCurrentDirection()));
							}else{
								c.setCurrentDirection(picRandomDirection(c.getCurrentDirection()));
							}
						}while (!(dirToBloc(c,c.getCurrentDirection()) instanceof Couloir));
					}
                }
            }
        }
	}

	public Point deplacerCreature(Creature c, Direction d) {
		//you should take care of the case where get(c) returns null;
		if (superTimer > 0){
			superTimer--;
		}else{
			pacman.setMode(PacMode.REGULAR);
		}
		Point currentPos = creaturesMap.get(c);
		Point nextPos = new Point(0,0);
			switch(d) {
			case UP:
				nextPos = new Point(currentPos.getI(), currentPos.getJ()-1);
				deplacerversCouloir(currentPos, nextPos, c, d);
				break;
			case RIGHT:
				nextPos = new Point(currentPos.getI()+1, currentPos.getJ());
				deplacerversCouloir(currentPos, nextPos, c, d);
				break;
			case LEFT:
				nextPos = new Point(currentPos.getI()-1, currentPos.getJ());
				deplacerversCouloir(currentPos, nextPos, c, d);
				break;
			case DOWN:
				nextPos = new Point(currentPos.getI(), currentPos.getJ()+1);
				deplacerversCouloir(currentPos, nextPos, c, d);
				break;
			}
		return nextPos;
	}
        
        public void start(){
		new Thread(this).start();
	}
	//private int i = 0;
	@Override
	public void run() {
		while(true){
			deplacerCreature(pacman,pacman.getCurrentDirection());
			Thread [] fantThreads = new Thread[4];
			for (int i = 0 ; i < 4 ; i++){
				deplacerCreature(fantoms[i],fantoms[i].getCurrentDirection());
//				fantThreads[i] = new Thread(){
//					@Override
//					public void run() {
//						deplacerCreature(fantoms[i],fantoms[i].getCurrentDirection());
//					}
//				};
//				fantThreads[i].start();
//			}
//			for (i = 0 ; i < 4 ; i++){
//				try{
//					fantThreads[i].join();
//				}catch (InterruptedException e){
//					e.printStackTrace();
//				}
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

