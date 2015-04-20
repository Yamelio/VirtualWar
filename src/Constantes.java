/**
 * @author matthieu
 */

public class Constantes {

	private final static int RECHARGE = 2;

	private final static int PORTEE_TIREUR = 3;
	private final static int PORTEE_PIEGEUR = 1;
	private final static int PORTEE_CHAR = 10;

	private final static int PORTEE_DEPLACEMENT_TIREUR = 1;
	private final static int PORTEE_DEPLACEMENT_PIEGEUR = 1;
	private final static int PORTEE_DEPLACEMENT_CHAR = 2;

	private final static int ENERGIE_INITIALE_TIREUR = 40;
	private final static int ENERGIE_INITIALE_PIEGEUR = 50;
	private final static int ENERGIE_INITIALE_CHAR = 60;

	private final static int COUT_MINE = 2;
	private final static int COUT_TIR = 2;
	private final static int COUT_CHAR = 1;

	private final static int COUT_DEPLACEMENT_TIREUR = 1;
	private final static int COUT_DEPLACEMENT_PIEGEUR = 2;
	private final static int COUT_DEPLACEMENT_CHAR = 5;

	private final static int DEGATS_TIREUR = 3;
	private final static int DEGATS_PIEGEUR = 2;
	private final static int DEGATS_CHAR = 6;

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

}
