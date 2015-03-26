
public class Char extends Robot{

	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	private int id;
	
	public Char(int equipe, int id){
		super(equipe, id);
		this.energie = constantes.getEnergieInitialeChar();
	}
}
