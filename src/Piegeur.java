public class Piegeur extends Robot {

	private int nbMines = Constantes.getMinesInit();

	public Piegeur(int equipe) {
		super(equipe);
		setEnergie(Constantes.getEnergieInitialePiegeur());
	}

	@Override
	public void recharger() {
		super.recharger();
		this.nbMines = 10;
	}

	public int getNbMines() {
		return this.nbMines;
	}

	public void setNbMines(int nbMines) {
		this.nbMines = nbMines;
	}

	public String toString() {
		return "P"+getId();
	}
}
