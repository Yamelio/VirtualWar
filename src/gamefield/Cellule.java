package virtualwars.gamefield;

import virtualwars.entity.Robot;

/**
 * Cette classe gère le contenu d'une case du plateau.
 * 
 */
public abstract class Cellule {

	/**
	 * 
	 */
	protected boolean obstacle;
	/**
	 * 
	 */
	protected boolean mur;
	/**
	 * Numéro d'équipe ayant posé la mine, 0 si il n'y a pas de mine.
	 */
	protected int mine;
	/**
	 * Numéro d'équipe auquel appartient la base, 0 si ce n'est pas une base.
	 */
	protected int base;
	/**
	 * Coordonnées de la cellule.
	 */
	protected Coordonnees coord;
	/**
	 * Robot present sur la cellule, null s'il n'y a pas de robot.
	 */
	protected Robot contenu;
	protected String image;

	/**
	 * Constructeur créant une cellule avec des Coordonnées
	 * 
	 * @param largeur
	 *            Abscisse de la cellule
	 * @param hauteur
	 *            Ordonnée de la cellule
	 */
	public Cellule(int largeur, int hauteur) {
		this.coord = new Coordonnees(largeur, hauteur);
	}

	/**
	 * Permet de savoir si la cellule contient une mine.
	 * 
	 * @return Renvoie le numéro d'équipe auquel appartient la mine ou 0 si il n'y a pas
	 *         de mine.
	 */
	public int contientMine() {
		return this.mine;
	}

	/**
	 * Permet de savoir si la cellule est une base.
	 * 
	 * @return Renvoie l'équipe auquelle appartient la base, 0 si ce n'est pas
	 *         une base.
	 */
	public int estBase() {
		return this.base;
	}
	/**
	 * Permet de savoir si la Cellule est un mur
	 * @return Retourne Vrai si la Cellule est un mur
	 * 
	 */
	public boolean estMur() {
		return this.mur;
	}
	/**
	 * Permet de savoir si la Cellule est un Obstacle
	 * @return Retourne Vrai si la Cellule est un Obstacle
	 * 
	 */
	public boolean estObstacle() {
		return this.obstacle;
	}

	/**
	 * Permet de récupérer les coordonnées de la cellule
	 * 
	 * @return Les coordonnées de la cellule.
	 */
	public Coordonnees getCoordonnees() {
		return this.coord;
	}

	/**
	 * Permet de récupérer le Robot présent sur la cellule, null si rien.
	 * 
	 * @return Le robot présent sur la cellule, null si rien.
	 */
	public Robot getContenu() {
		return this.contenu;
	}

	public String toString() {
		return "Cellule[Base="+this.base+";Mine="+this.mine+";Contenu="+this.contenu.toString()+"]";
	}

	/**
	 * Permet de déplacer le Robot sur la cellule.
	 * 
	 * @param robot
	 *            Le Robot à déplacer.
	 */
	public abstract void deplaceSur(Robot robot);

	/**
	 * Permet d'ajouter une mine sur la cellule.
	 * 
	 * @param equipe
	 *            L'équipe ajoutant la mine.
	 */
	public abstract void ajouteMine(int equipe);

	/**
	 * Permet d'enlever de la cellule les Robots et les mines.
	 */
	public abstract void videCase();

	/**
	 * Methode deprecated
	 * @param equipe
	 * 
	 */
	@Deprecated
	abstract void ajoute(int equipe);
}
