/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un objet Constantes qui contient toutes les constantes appliquables au jeu
 */
public class Constantes {
	
	/** La recharge lorsqu'un robot est dans sa base */
	private final int RECHARGE = 2;
	
	/** La portée du tireur */
	private final int PORTEE_TIREUR = 3;
	
	/** La portée du piegeur */
	private final int PORTEE_PIEGEUR = 1;
	
	/** La portée du char */
	private final int PORTEE_CHAR = 10;
	
	/** La portée de déplacement du tireur */
	private final int PORTEE_DEPLACEMENT_TIREUR = 1;
	
	/** La portée de déplacement du piegeur */
	private final int PORTEE_DEPLACEMENT_PIEGEUR = 1;
	
	/** La portée de déplacement du char */
	private final int PORTEE_DEPLACEMENT_CHAR = 2;
	
	/** L'énergie initiale du tireur */
	private final int ENERGIE_INITIALE_TIREUR = 40;
	
	/** L'énergie initiale du piegeur */
	private final int ENERGIE_INITIALE_PIEGEUR = 50;
	
	/** L'énergie initiale du piegeur */
	private final int ENERGIE_INITIALE_CHAR = 60;
	
	/** Le cout en énergie lorsqu'un piegeur pose une mine */
	private final int COUT_MINE = 2;
	
	/** Le cout en énergie d'un tir de tireur */
	private final int COUT_TIR = 2;
	
	/** Le cout en énergie d'un tir de char */
	private final int COUT_CHAR = 1;
	
	/** Le cout en énergie d'un déplacement de tireur */
	private final int COUT_DEPLACEMENT_TIREUR = 1;
	
	/** Le cout en énergie d'un déplacement de piegeur */
	private final int COUT_DEPLACEMENT_PIEGEUR = 2;
	
	/** Le cout en énergie d'un déplacement de char */
	private final int COUT_DEPLACEMENT_CHAR = 5;
	
	/** Les dégats subits par le tireur */
	private final int DEGATS_TIREUR = 3;
	
	/** Les dégats subits par le piegeur */
	private final int DEGATS_PIEGEUR = 2;
	
	/** Les dégats subits par le char */
	private final int DEGATS_CHAR = 6;
	
	
	public int getRecharge(){return this.RECHARGE;}
	
	
	public int getPorteeTireur(){return this.PORTEE_TIREUR;}

	public int getPorteePiegeur(){return this.PORTEE_PIEGEUR;}

	public int getPorteeChar(){return this.PORTEE_CHAR;}
	
	public int getPorteeDeplacementTireur(){return this.PORTEE_DEPLACEMENT_TIREUR;}
	
	public int getPorteeDeplacementPiegeur(){return this.PORTEE_DEPLACEMENT_PIEGEUR;}
	
	public int getPorteeDeplacementChar(){return this.PORTEE_DEPLACEMENT_CHAR;}
	
	public int getEnergieInitialeTireur(){return this.ENERGIE_INITIALE_TIREUR;}
	
	public int getEnergieInitialePiegeur(){return this.ENERGIE_INITIALE_PIEGEUR;}
	
	public int getEnergieInitialeChar(){return this.ENERGIE_INITIALE_CHAR;}
	
	public int getCoutMine(){return this.COUT_MINE;}
	
	public int getCoutTirTireur(){return this.COUT_TIR;}
	
	public int getCoutTirChar(){return this.COUT_CHAR;}
	
	public int getCoutDeplacementTireur(){return this.COUT_DEPLACEMENT_TIREUR;}
	
	public int getCoutDeplacementPiegeur(){return this.COUT_DEPLACEMENT_PIEGEUR;}
	
	public int getCoutDeplacementChar(){return this.COUT_DEPLACEMENT_CHAR;}
	
	public int getDegatsTireur(){return this.DEGATS_TIREUR;}
	
	public int getDegatsPiegeur(){return this.DEGATS_PIEGEUR;}
	
	public int getDegatsChar(){return this.DEGATS_CHAR;}
	
}
