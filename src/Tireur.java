public class Tireur extends Robot {

	public Tireur(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialeTireur());
	}

	@Override
	public int getNbMines() {
		return 0;
	}

	@Override
	public void setNbMines(int nbMines) {
	}

	public String toString() {
		return "T" + getId();
	}

}
