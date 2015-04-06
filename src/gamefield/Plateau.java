import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Plateau {
	Positione HashMap<String, Position> carte;

	private ArrayList<Robot> robots;
	private int hauteur;
	private int largeur;
	private int percentObstacle;

	/**
	 * Constructeur de la classe Plateau
	 * 
	 * @param largeur
	 *            Initialise la largeur du plateau
	 * @param hauteur
	 *            Initialise la hauteur du plateau
	 */

	public Plateau(int largeur, int hauteur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}

	public void initCarte(){
		carte = new HashMap<String, Position>();
		String s;
		int x = 64; //correspond a A (-1) dans la table ASCII
		int y = 47; //correspond a 0 (-1) dans la table ASCII 
		for(int i = 0; i<hauteur; i++){
			++x;
			for(int j = 0; j< largeur; j++){
				++y;
				if ()
					s = "" + (char) x;
				if(y > 57){ // si c'est un nombre a deux chiffre, sa va dépasser de la table, il faut faire une convertion ex: A11
					int d = (int) y / 47;
					int r = (int) y % 47;
					s += ((char) d) + ((char) r);
				}
				else
				{
					s += (char) y;
				}		
			}
			carte.put(s, new Postion(largeur, hauteur));	
		}
	}
	
	public int getSurface(){return largeur * hauteur;}
	public int getLargeur(){return largeur;}
	public int getHauteur(){return hauteur;}
	public int getPercentObstacle(){return percentObstacle;}
	public void setLargeur(int largeur){this.largeur = largeur;}
	public void setHauteur(int hauteur){this.hauteur = hauteur;}
	public void setPercentObstacle(int percentObstacle){this.percentObstacle = percentObstacle;}

	/**
	 * Crée un chemin libre pour pouvoir poser des obstacles autour
	 * 
	 *
	 */
	private void initObstacles() {
		Random r = new Random();
		List<Postion> liste = new ArrayList<Position>();
		Position p = new Position(0,0);
		liste.add(p);
		liste.add(new Position(largeur, hauteur));

		while(p.getX() != largeur && p.getY() != hauteur){
			if(r.nextBoolean()){ //une chance sur deux qu'il se déplace verticalement
				if(p.getX() + 1 <= largeur){
					p = new Position(p.getX() + 1, p.getY());
				}
			}
			else
			{
				if(p.getY() + 1 <= hauteur){
					p = new Position(p.getX(), p.getY() + 1);
				}
			}
			liste.add(p);

		}
		int nbObstacle = (int) getSurface * (percentObstacle / 100);

		int randX;
		int randY;
		String s;
		for(int i = 0; i < liste.size() && nbObstacle > 0; i++){
			randX = r.nextInt(largeur + 1);
			randY = r.nextInt(largeur + 1);
			if(!(Position p = new Position(randX, randY)).equals(liste.get(i))){ //Si la position aléatoire n'est pas dans la liste
				--nbObstacle,
				p.estObstacle;//
				// LE code pour convertir une coordonne en string
				s = "" + ((char) randX + 65);
				if(y > 57){ // si c'est un nombre a deux chiffre, sa va dépasser de la table, il faut faire une convertion ex: A11
					int d = (int) y / 48;
					int r = (int) y % 48;
					s += ((char) d) + ((char) r);
				}
				else
				{
					s += (char) y;
				}		
				carte.remove(s);
				carte.put(s, p);
			}
		}
	
	}

	public String toString(){
		String ecran = "";
		/*
		ecran += " ";
		for(int i = 0; i < largeur; i++){  //premiere ligne avec les coordonne
			ecran += ((char) i + 65) + "  ";		
		}
		ecran += "\n";
		for
		ecran += "+";
		for(int i = 0; i < largeur; i++){
			ecran += "---+";
		}
		ecran += "\n";
		*/
		return ecran;
	}
	
	
}
