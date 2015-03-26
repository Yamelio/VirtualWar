
public class Piegeur extends Robot{
	
	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int id;
	private int nbMines = 10;
	
	public Piegeur(int equipe, int id){
		super(equipe, id);
		this.energie = constantes.getEnergieInitialePiegeur();
	}

	@Override
	public void recharger(){
		this.energie += constantes.getRecharge();
		this.nbMines = 10;
	}
}
