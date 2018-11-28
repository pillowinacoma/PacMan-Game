package Model;

public class Jeu {

	public static void main(String[] args) {


		Grille grille = new Grille(10,10);
		for (int i = 0; i < grille.getN(); i++) {
			for (int j = 0; j < grille.getM(); j++) {
				grille.setCaseGrille(i, j,(i%2==0)?new Mur():new Couloir((i%2*j%2==1)?true:false,(i%2*j%2==0)?true:false));
			}
		}
		PacMan p = new PacMan();
		grille.setCaseCreature(3, 6, p);
		grille.setCaseCreature(1, 4, new Fantom());
		grille.setCaseCreature(1, 3, new Fantom());
		grille.setCaseCreature(1, 5, new Fantom());
		grille.setCaseCreature(1, 6, new Fantom());
		System.out.println(grille);
		System.out.println(grille.deplacerCreature(p, Direction.RIGHT));
		//pacman.currentdirection review
		System.out.println(grille);
	}

}
