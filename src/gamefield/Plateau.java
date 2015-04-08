import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Plateau {
	private Map<String, Position> carte;
	private List<Robot> robots;
	private int hauteur;
	private int largeur;
	private int percentObstacle;

	/**
	 * Constructeur de la classe Plateau
	 * 
	 * @param largeur
	 *            Initialise la largeur du plateau
	 * @param hauteur
	 *            Initialise la hauteur du plateau
	 */
	public Plateau(int largeur, int hauteur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}

	/**
	 * Fonction d'initialisation de la carte
	 */
	public void initCarte(){
		carte = new HashMap<String, Position>();
		for(int i = 1; i <= hauteur; i++){
			for(int j = 1; j <= largeur; j++){
				carte.put(posToString(new Postion(largeur, hauteur)), new Postion(largeur, hauteur));
		}
	}

	/**
	 * Fonction pour convertir la String en position
	 * 
	 * @param Position
	 *            La position à convertir
	 * @return La String sous la forme "B11"
	 */
	public String posToString(Position p) {
		String s = "";
		s += (char) (p.getX() + 16); // +16 car les lettres se trouvent à +16
										// dans la table ASCII
		s += p.getY(); // Pas besoin de convertion, les ordonnées sont déjà en
						// int
		return s;
	}

	/**
	 * Fonction pour convertir la Position en String
	 * 
	 * @param String
	 *            La String à convertir, sous la forme "C4"
	 * @return La Position voulu
	 */
	public Position stringToPos(String s) {
		int x = (int) ((char) (s.substring(0, 1) - 16)); // On converti
															// l'abscisse en int
		int y = (int) s.substring(1); // On converti l'ordonné en int
		return new Position(x, y);
	}

	/**
	 * Crée un chemin libre pour pouvoir poser des obstacles autour
	 * 
	 */
	private void initObstacles() {
		Random r = new Random();
		List<Postion> liste = new ArrayList<Position>(); //Liste des position à laisser libre
		Position p = new Position(0,0); //On commence en haut à gauche
		liste.add(p);//La position en haut à gauche est laissé libre
		liste.add(new Position(largeur, hauteur)); //La position en bas à droite est laissé libre

		while(p.getX() != largeur && p.getY() != hauteur){ //Temps qu'on est pas arriver dans le coin en bas à gauche
			if(r.nextBoolean()){ //une chance sur deux qu'il se déplace verticalement
				if(p.getX() + 1 <= largeur){
					p = new Position(p.getX() + 1, p.getY());
				}
			}
			else
			{
				if(p.getY() + 1 <= hauteur){
					p = new Position(p.getX(), p.getY() + 1);
				}
			}
			liste.add(p); //On ajoute a la liste

		}
		int nbObstacle = (int) getSurface * (percentObstacle / 100); //Converti le pourcentage d'obstacle en noimbre d'obsacle

		int randX;
		int randY;
		while(nbObstacle > 0){ // temps qu'il reste desobsacles à ajouter
			for(int i = 0; i < liste.size(); i++){ 
				randX = r.nextInt(largeur + 1);
				randY = r.nextInt(largeur + 1);
				if(!(Position p = new Position(randX, randY)).equals(liste.get(i))){ //Si la position aléatoire n'est pas dans la liste
					--nbObstacle; //On décremente le nombre d'obsacle à ajouter
					p.estObstacle();//La position est maintenent un u
					carte.remove(posToString(p)); //histoire d'etre sur on supp d'abord
					carte.put(posToString(p), p); //On ajoute
				}
			}
		}
	
	}

	/**
	 * Procedure qui affiche le plateau
	 * 
	 * @return String Le plateau de jeu en ascii art
	 */
	public String toString() {
		String s = "";
		for (int i = 1; i < hauteur + 1; i++) { // On parcourt en hauteur
			for (int j = 1; i < largeur + 1; j++) { // On parcourt en largeur
				if (i % 2 != 0) { // toutes les deux lignes on aterne
					s += "+---";
				} else {
					s += "| "+ carte.get(posToString(new Position(i,j))).toString() +" "; //Merci Aurélien
				}
			}
			if (i % 2 == 0) { // Pour finir la ligne joliement
				s += "+";
			} else {
				s += "|";
			}
			s += "\n"; // Et on passe la ligne
		}
		return s;
	}

	public int getSurface() {
		return largeur * hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getPercentObstacle() {
		return percentObstacle;
	}

	public HashMap<String, Position> getCarte() {
		return carte;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setPercentObstacle(int percentObstacle) {
		this.percentObstacle = percentObstacle;
	}

}
