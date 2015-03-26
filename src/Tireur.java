
public class Tireur extends Robot{

	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int id;
	
	public Tireur(int equipe, int id){
		super(equipe, id);
		this.energie = constantes.getEnergieInitialeTireur();
	}
}
