
public class Piegeur extends Robot{
	
	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int nbMines = 10;
	private int id;
	
	public Piegeur(int equipe){
		super(equipe);
		this.energie = constantes.getEnergieInitialePiegeur();
	}

	@Override
	public void recharger(){
		this.energie += constantes.getRecharge();
		this.nbMines = 10;
	}

	public int getNbMines(){return this.nbMines;}
	
	public void setNbMines(int nbMines){this.nbMines = nbMines;}
}
