
public class Position {

	private int x;
	private int y;
	private int equipe = 0;
	private boolean base = false;
	private boolean mine= false;
	private boolean obstacle= false;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return this.x;}
	
	public int getY(){return this.y;}
	
	public boolean estBase(){return this.base;}

	public boolean estMine(){return this.mine;}
	
	public boolean estObstacle(){return this.obstacle;}
	
	public void flipBase(int equipe){
		if(!this.base){
			this.base = !this.base;
			this.equipe = equipe;
		}else {
			this.base = !this.base;
			this.equipe = 0;
		}
	}
	
	public void flipMine(int equipe){
		if(!this.mine){
			this.mine = !this.mine;
			this.equipe = equipe;
		}else {
			this.mine = !this.mine;
			this.equipe = 0;
		}
	}
	
	public void flipObstacle(){this.obstacle = !this.obstacle;}
	
	public boolean isEquals(Coordonnees coord) {
		// TODO Auto-generated method stub
		return (this.getX() == coord.getLargeur() && this.getY() == coord.getHauteur());
	}
}
