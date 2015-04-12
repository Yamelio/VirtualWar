/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un objet Robot d�finit par son �quipe
 */
public abstract class Robot {
	
	/** Les constantes permettant de d�terminer l'�nergie initiale, la port�e etc... */
	private Constantes constantes = new Constantes();
	
	/** L'�nergie du robot */
	private int energie;
	
	/** l'�quipe du robot */
	private int equipe;
	
	/** la position du robot */
	private Position pos;
	
	/** le compteur servant � d�finir l'ID du robot */
	public static int cpt = 0;
	
	/** l'ID du robot */
	private int id;
	
	/**
	 * Construit un Robot � partir de son �quipe
	 * @param equipe - l'�quipe du robot
	 */
	public Robot(int equipe){
		this.equipe = equipe;
		this.id = cpt++;
	}
	
	/**
	 * Retourne l'�nergie du robot
	 * @return l'�nergie du robot
	 */
	public int getEnergie(){return this.energie;}
	
	/**
	 * Attribue une nouvele �nergie au robot
	 * @param energie la nouvelle �nergie � attribuer
	 */
	public void setEnergie(int energie){
		this.energie = energie;
	}
	
	/**
	 * Retourne l'�quipe du robot
	 * @return l'�quipe du robot
	 */
	public int getEquipe(){return this.equipe;}
	
	/**
	 * Attribue une nouvelle �quipe au robot
	 * @param equipe la nouvelle �quipe � attribuer
	 */
	public void setEquipe(int equipe){
		this.equipe = equipe;
	}
	
	/**
	 * Retourne la position du robot
	 * @return la position du robot
	 */
	public Position getPosition(){return this.pos;}

	/**
	 * Attribue une nouvelle position au robot
	 * @param pos la nouvelle position � attribuer
	 */
	public void setPosition(Position pos){this.pos = pos;}
	
	/**
	 * Recharge en vie le robot
	 */
	public void recharger(){this.energie += constantes.getRecharge();}
	
	/** 
	 * Retourne l'ID du robot
	 * @return l'ID du robot
	 */
	public int getId(){return this.id;}
	
	public abstract int getNbMines();

	public abstract void setNbMines(int nbMines);
	
	public abstract String toString();
	
}
