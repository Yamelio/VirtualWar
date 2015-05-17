/**
 * 
 * @author Matthieu Gaillard
 * Cette classe represente un objet char definit par son equipe
 */
public class Char extends Robot {

	/**
	 * Construit un objet char en fonction de son equipe et lui applique son energie initiale
	 * @param equipe l'equipe du char
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
