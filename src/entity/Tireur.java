
public class Tireur extends Robot{

	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int id;
	
	public Tireur(int equipe){
		super(equipe);
		this.energie = constantes.getEnergieInitialeTireur();
	}

	@Override
	public int getNbMines() {return 0;}

	@Override
	public void setNbMines(int nbMines){}

	

}
