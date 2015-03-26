package virtualwars.gamefield;

public class Coordonnees {
	/**
	 * Position en abscisse
	 * @param largeur
	 */
	private int largeur;
	
	/**
	 * Position en ordonnées
	 * @param hauteur
	 */
	private int hauteur;

	/**
	 * Constucteur de la classe Coordonnee
	 * 
	 * @param largeur
	 *            Position en abscisse
	 * @param hauteur
	 *            Position en ordonnée
	 */
	public Coordonnees(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	/**
	 * Fonction qui retourne la position en abscisse
	 * 
	 * @return La position en abscisse
	 */
	public int getLargeur() {
		return largeur;
	}

	/**
	 * Fonction qui retourne la position en ordonnée
	 * 
	 * @return La position en ordonnée
	 */
	public int getHauteur() {
		return hauteur;
	}
/**
 * compare 2 coordonnees
 * @param coord
 * @return Vrai si les coordonnees sont egales
 */
	public boolean isEquals(Coordonnees coord){
		return hauteur==coord.hauteur && largeur==coord.largeur;
	}
	
	public String toString() {
		return "{"+this.largeur+";"+this.hauteur+"}";
	}

	/**
	 * Fonction qui ajoute les coordonnées
	 * 
	 * @param coord
	 *            Objet Coordonnées
	 * @return La somme des coordonnées
	 */
	public Coordonnees ajout(Coordonnees coord) {
		Coordonnees n = new Coordonnees(this.largeur+coord.getLargeur(), this.hauteur+coord.getHauteur());
		return n;
	}
}
