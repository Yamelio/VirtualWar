
public class Char extends Robot{

	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int id;
	
	public Char(int equipe){
		super(equipe);
		this.energie = constantes.getEnergieInitialeChar();
	}

	@Override
	public int getNbMines() {return 0;}

	@Override
	public void setNbMines(int nbMines) {}
	
	public String toString(){
		return "C";
	}
}
