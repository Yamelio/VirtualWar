/**
 * 
 * @author Matthieu Gaillard
 * Cette classe repr�sente un objet Position, d�finit par ses coordonn�es x et y
 */
public class Position {

	/** L'abscisse de la position */
	private int x;
	
	/** L'ordonn�e de la position */
	private int y;
	
	/** Si une position est une mine ou une base, l'�quipe qui la poss�de */
	private int equipe = 0;
	
	/** Si une position est une base */
	private boolean base = false;
	
	/** Si une position est une mine */
	private boolean mine = false;
	
	/** Si une position est un obstacle */
	private boolean obstacle = false;
	
	/** Le plateau pris en compte pour la m�thode estRobot() */
	private static Plateau p;
	
	/**
	 * Construit un objet Position � partir de son abscisse et de son ordonn�e
	 * @param x - l'abscisse de la Position
	 * @param y - L'ordonn�e de la Position
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Retourne l'abscisse de la position
	 * @return l'abscisse de la position
	 */
	public int getX(){return this.x;}
	
	/**
	 * Retourne l'ordonn�e de la position
	 * @return l'ordonn�e de la position
	 */
	public int getY(){return this.y;}
	
	/**
	 * Retourne un bool�en, qui est vrai si la position est une base, faux si non
	 * @return un bool�en, qui est vrai si la position est une base, faux si non
	 */
	public boolean estBase(){return this.base;}

	/**
	 * Retourne un bool�en, qui est vrai si la position est une mine, faux si non
	 * @return un bool�en, qui est vrai si la position est une mine, faux si non
	 */
	public boolean estMine(){return this.mine;}
	
	/**
	 * Retourne un bool�en, qui est vrai si la position est un obstacle, faux si non
	 * @return un bool�en, qui est vrai si la position est un obstacle, faux si non
	 */
	public boolean estObstacle(){return this.obstacle;}
	
	/**
	 * Transforme une position en une base ou une base en position vide avec l'�quipe � qui appartient la base
	 * @param equipe l'�quipe � qui appartient la base
	 */
	public void flipBase(int equipe){
		if(!this.base){
			this.base = !this.base;
			this.equipe = equipe;
		}else {
			this.base = !this.base;
			this.equipe = 0;
		}
	}
	
	/**
	 * Transforme une position en une mine ou une mine en une position vide avec l'�quipe � qui appartient la mine
	 * @param equipe l'�quipe � qui appartient la mine
	 */
	public void flipMine(int equipe){
		if(!this.mine){
			this.mine = !this.mine;
			this.equipe = equipe;
		}else {
			this.mine = !this.mine;
			this.equipe = 0;
		}
	}
	
	/**
	 * Transforme une position en obstacle ou un obstacle en position vide
	 */
	public void flipObstacle(){this.obstacle = !this.obstacle;}
	
	/**
	 * Retourne un bool�en, qui est vrai si la position contient un robot, faux si non
	 * @return un bool�en, qui est vrai si la position contient un robot, faux si non
	 */
	public boolean estRobot(){
		for(int i = 0; i < p.getListeRobot().size(); i++){
			if(p.getListeRobot().get(i).getPosition().equals(this))
				return true;
		}
		return false;
	}
	
	/**
	 * D�finit le plateau � prendre en compte pour la m�thode estRobot()
	 * @param p le plateau � d�finir
	 */
	public void setPlateau(Plateau p){this.p = p;}

	/**
	 * Retourne une cha�ne de caract�re pour l'affichage de ce qu'il y a sur la position
	 * @return une cha�ne de caract�re pour l'affichage de ce qu'il y a sur la position
	 */
	public String toString(){
		if(this.estBase()){
			return "B";
		}else if(this.estMine()){
			return "M";
		}else if(this.estObstacle()){
			return "#";
		}else if(this.estRobot()){
			for(int i = 0; i < p.getListeRobot().size(); i++){
				if(p.getListeRobot().get(i).getPosition().equals(this)){
					return p.getListeRobot().get(i).toString();
				}
			}
		}
		return " ";
	}
}
