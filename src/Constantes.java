/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un objet Constantes qui contient toutes les constantes appliquables au jeu
 */
public class Constantes {
	
	/** La recharge lorsqu'un robot est dans sa base */
	private final int RECHARGE = 2;
	
	/** La port�e du tireur */
	private final int PORTEE_TIREUR = 3;
	
	/** La port�e du piegeur */
	private final int PORTEE_PIEGEUR = 1;
	
	/** La port�e du char */
	private final int PORTEE_CHAR = 10;
	
	/** La port�e de d�placement du tireur */
	private final int PORTEE_DEPLACEMENT_TIREUR = 1;
	
	/** La port�e de d�placement du piegeur */
	private final int PORTEE_DEPLACEMENT_PIEGEUR = 1;
	
	/** La port�e de d�placement du char */
	private final int PORTEE_DEPLACEMENT_CHAR = 2;
	
	/** L'�nergie initiale du tireur */
	private final int ENERGIE_INITIALE_TIREUR = 40;
	
	/** L'�nergie initiale du piegeur */
	private final int ENERGIE_INITIALE_PIEGEUR = 50;
	
	/** L'�nergie initiale du piegeur */
	private final int ENERGIE_INITIALE_CHAR = 60;
	
	/** Le cout en �nergie lorsqu'un piegeur pose une mine */
	private final int COUT_MINE = 2;
	
	/** Le cout en �nergie d'un tir de tireur */
	private final int COUT_TIR = 2;
	
	/** Le cout en �nergie d'un tir de char */
	private final int COUT_CHAR = 1;
	
	/** Le cout en �nergie d'un d�placement de tireur */
	private final int COUT_DEPLACEMENT_TIREUR = 1;
	
	/** Le cout en �nergie d'un d�placement de piegeur */
	private final int COUT_DEPLACEMENT_PIEGEUR = 2;
	
	/** Le cout en �nergie d'un d�placement de char */
	private final int COUT_DEPLACEMENT_CHAR = 5;
	
	/** Les d�gats subits par le tireur */
	private final int DEGATS_TIREUR = 3;
	
	/** Les d�gats subits par le piegeur */
	private final int DEGATS_PIEGEUR = 2;
	
	/** Les d�gats subits par le char */
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
