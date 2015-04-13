
public class Piegeur extends Robot{
	
	private Constantes constantes = new Constantes();
	private int nbMines = 10;
	
	public Piegeur(int equipe){
		super(equipe);
		setEnergie(constantes.getEnergieInitialePiegeur());
	}

	@Override
	public void recharger(){
		super.recharger();
		this.nbMines = 10;
	}

	public int getNbMines(){return this.nbMines;}
	
	public void setNbMines(int nbMines){this.nbMines = nbMines;}

	public String toString(){
		return "P";
	}
}