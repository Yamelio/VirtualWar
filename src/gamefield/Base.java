package virtualwars.gamefield;

import java.util.ArrayList;

import virtualwars.entity.Robot;

public class Base extends Cellule {
	private ArrayList<Robot> robots;

	/**
	 * Constructeur de Base
	 * 
	 * @param largeur
	 *            Spécifie la largeur du plateau
	 * @param hauteur
	 *            Spécifie la hauteur du plateau
	 * @param equipe
	 *            Spécifie le numéro d'équipe de la Base
	 */
	public Base(int largeur, int hauteur, int equipe) {
		super(largeur, hauteur);
		robots = new ArrayList<Robot>();
		// TODO Auto-generated constructor stub
		this.base = equipe;
	}

	@Override
	public void deplaceSur(Robot robot) {
		// TODO Auto-generated method stub
		robots.add(robot);
	}

	@Override
	void ajoute(int equipe) {
		// TODO Auto-generated method stub

	}

	@Override
	public void videCase() {
		ArrayList<Robot> toRemove = new ArrayList<Robot>();
		for (Robot r : robots) {
			if (!r.getCoordonnees().isEquals(this.coord))
				toRemove.add(r);
		}
		for(Robot t : toRemove) {
			robots.remove(t);
		}
	}

	@Override
	public void ajouteMine(int equipe) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Robot> getRobotsInBase() {
		return robots;
	}

	public String toString() {
		String s = "Base+--";
		for (Robot r : robots) {
			s += "\t+[ " + r.toString()+" ]\n";
		}
		if(robots.isEmpty())
			s += " vide";
		return s;
	}
}
