import java.util.ArrayList;

public class Base extends Position {
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
		flipBase(equipe);
	}

	public void deplaceSur(Robot robot) {
		// TODO Auto-generated method stub
		robots.add(robot);
	}

	public void quitteBase(Robot robot){
		robots.remove(robot);
	}
	
	public void videCase() {
		ArrayList<Robot> toRemove = new ArrayList<Robot>();
		for (Robot r : robots) {
			if (!r.getPosition().equals(new Position(getX(),getY())));
				toRemove.add(r);
		}
		for(Robot t : toRemove) {
			robots.remove(t);
		}
	}

	public ArrayList<Robot> getRobotsInBase() {
		return robots;
	}
}