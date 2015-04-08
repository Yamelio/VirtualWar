
public abstract class Robot {
	
	private Constantes constantes = new Constantes();
	private int energie;
	private int equipe;
	private Position pos;
	public static int cpt = 0;
	private int id;
	
	public Robot(int equipe){
		this.equipe = equipe;
		this.id = cpt++;
	}
	
	public int getEnergie(){return this.energie;}
	
	public void setEnergie(int energie){
		this.energie = energie;
	}
	
	public int getEquipe(){return this.equipe;}
	
	public void setEquipe(int equipe){
		this.equipe = equipe;
	}
	
	public Position getPosition(){return this.pos;}

	public void setPosition(Position pos){this.pos = pos;}
	
	public void recharger(){this.energie += constantes.getRecharge();}
	
	public int getId(){return this.id;}
}
