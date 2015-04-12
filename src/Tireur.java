
public class Tireur extends Robot{

	private Constantes constantes = new Constantes();
	
	public Tireur(int equipe){
		super(equipe);
		setEnergie(constantes.getEnergieInitialePiegeur());
	}

	@Override
	public int getNbMines() {return 0;}

	@Override
	public void setNbMines(int nbMines){}

	public String toString(){
		return "T";
	}

}
