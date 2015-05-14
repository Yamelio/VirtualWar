/**
 * 
 * @author Les Quatre Cavaliers de l'Apocalypse Cette classe repr�sente un objet
 *         Constantes contenant toutes les constantes de base au niveau du jeu
 */
public class Constantes {

	/** La recharge lorsqu'un robot est dans sa base */
	private final static int RECHARGE = 2;

	/** La port�e du tireur */
	private final static int PORTEE_TIREUR = 3;

	/** La port�e du piegeur */
	private final static int PORTEE_PIEGEUR = 1;

	/** La port�e du char */
	private final static int PORTEE_CHAR = 10;

	/** La port�e de d�placement du tireur */
	private final static int PORTEE_DEPLACEMENT_TIREUR = 1;

	/** La port�e de d�placement du piegeur */
	private final static int PORTEE_DEPLACEMENT_PIEGEUR = 1;

	/** La port�e de d�placement du char */
	private final static int PORTEE_DEPLACEMENT_CHAR = 2;

	/** L'�nergie initiale du tireur */
	private final static int ENERGIE_INITIALE_TIREUR = 40;

	/** L'�nergie initiale du piegeur */
	private final static int ENERGIE_INITIALE_PIEGEUR = 50;

	/** L'�nergie initiale du char */
	private final static int ENERGIE_INITIALE_CHAR = 60;

	/** Le cout en �nergie lorsqu'un piegeur pose une mine */
	private final static int COUT_MINE = 2;

	/** Le cout en �nergie d'un tir de tireur */
	private final static int COUT_TIR = 2;

	/** Le cout en �nergie d'un tir de char */
	private final static int COUT_CHAR = 1;

	/** Le cout en �nergie d'un d�placement de tireur */
	private final static int COUT_DEPLACEMENT_TIREUR = 1;

	/** Le cout en �nergie d'un d�placement de piegeur */
	private final static int COUT_DEPLACEMENT_PIEGEUR = 2;

	/** Le cout en �nergie d'un d�placement de char */
	private final static int COUT_DEPLACEMENT_CHAR = 5;

	/** Les d�gats subits par le tireur */
	private final static int DEGATS_TIREUR = 3;

	/** Les d�gats subits par le piegeur */
	private final static int DEGATS_PIEGEUR = 2;

	/** Les d�gats subits par le char */
	private final static int DEGATS_CHAR = 6;

	private static final int MINES_INIT = 10;

	public static int getRecharge() {
		return RECHARGE;
	}

	public static int getPorteeTireur() {
		return PORTEE_TIREUR;
	}

	public static int getPorteePiegeur() {
		return PORTEE_PIEGEUR;
	}

	public static int getPorteeChar() {
		return PORTEE_CHAR;
	}

	public static int getPorteeDeplacementTireur() {
		return PORTEE_DEPLACEMENT_TIREUR;
	}

	public static int getPorteeDeplacementPiegeur() {
		return PORTEE_DEPLACEMENT_PIEGEUR;
	}

	public static int getPorteeDeplacementChar() {
		return PORTEE_DEPLACEMENT_CHAR;
	}

	public static int getEnergieInitialeTireur() {
		return ENERGIE_INITIALE_TIREUR;
	}

	public static int getEnergieInitialePiegeur() {
		return ENERGIE_INITIALE_PIEGEUR;
	}

	public static int getEnergieInitialeChar() {
		return ENERGIE_INITIALE_CHAR;
	}

	public static int getCoutMine() {
		return COUT_MINE;
	}

	public static int getCoutTirTireur() {
		return COUT_TIR;
	}

	public static int getCoutTirChar() {
		return COUT_CHAR;
	}
	
	
	public int getCoutAttaque(Robot r){
		if(r instanceof Tireur){
			return COUT_TIR;
		}
		
		else if(r instanceof Piegeur){
			return COUT_MINE;
		}
		
		else {
			return COUT_CHAR;
		}
		
	}
		
	
	public int getCoutDeplacement(Robot r){
		if(r instanceof Tireur){
			return COUT_DEPLACEMENT_TIREUR;
		}
		
		else if(r instanceof Piegeur){
			return COUT_DEPLACEMENT_PIEGEUR;
		}
		
		else {
			return COUT_DEPLACEMENT_CHAR;
		}
		
		
	}

	public static int getCoutDeplacementTireur() {
		return COUT_DEPLACEMENT_TIREUR;
	}

	public static int getCoutDeplacementPiegeur() {
		return COUT_DEPLACEMENT_PIEGEUR;
	}

	public static int getCoutDeplacementChar() {
		return COUT_DEPLACEMENT_CHAR;
	}

	public static int getDegatsTireur() {
		return DEGATS_TIREUR;
	}

	public static int getDegatsPiegeur() {
		return DEGATS_PIEGEUR;
	}

	public static int getDegatsChar() {
		return DEGATS_CHAR;
	}

	public static int getMinesInit() {
		return MINES_INIT;
	}

}
