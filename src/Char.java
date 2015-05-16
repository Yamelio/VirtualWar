/**
 * 
 * @author Matthieu Gaillard
 * Cette classe représente un objet char définit par son équipe
 */
public class Char extends Robot {

	/**
	 * Construit un objet char en fonction de son équipe et lui applique son énergie initiale
	 * @param equipe l'équipe du char
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
