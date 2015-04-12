/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un objet Robot définit par son équipe
 */
public abstract class Robot {
	
	/** Les constantes permettant de déterminer l'énergie initiale, la portée etc... */
	private Constantes constantes = new Constantes();
	
	/** L'énergie du robot */
	private int energie;
	
	/** l'équipe du robot */
	private int equipe;
	
	/** la position du robot */
	private Position pos;
	
	/** le compteur servant à définir l'ID du robot */
	public static int cpt = 0;
	
	/** l'ID du robot */
	private int id;
	
	/**
	 * Construit un Robot à partir de son équipe
	 * @param equipe - l'équipe du robot
	 */
	public Robot(int equipe){
		this.equipe = equipe;
		this.id = cpt++;
	}
	
	/**
	 * Retourne l'énergie du robot
	 * @return l'énergie du robot
	 */
	public int getEnergie(){return this.energie;}
	
	/**
	 * Attribue une nouvele énergie au robot
	 * @param energie la nouvelle énergie à attribuer
	 */
	public void setEnergie(int energie){
		this.energie = energie;
	}
	
	/**
	 * Retourne l'équipe du robot
	 * @return l'équipe du robot
	 */
	public int getEquipe(){return this.equipe;}
	
	/**
	 * Attribue une nouvelle équipe au robot
	 * @param equipe la nouvelle équipe à attribuer
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
	 * @param pos la nouvelle position à attribuer
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
