/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un Robot Char, d�finit par son �quipe
 */
public class Char extends Robot {

	/**
	 * 
	 * Constructeur de la classe Char
	 * 
	 * @param equipe
	 * 					l'�quipe du char
	 */
	public Char(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialeChar());
	}

	@Override
	public int getNbMines() {
		return 0;
	}

	@Override
	public void setNbMines(int nbMines) {
	}

	public String toString() {
		return "C" + getId();
	}
}
