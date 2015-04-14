public class Char extends Robot {

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
