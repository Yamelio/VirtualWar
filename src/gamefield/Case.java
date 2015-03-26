package virtualwars.gamefield;

import virtualwars.entity.Robot;

public class Case extends Cellule{

	public Case(int largeur, int hauteur) {
		super(largeur, hauteur);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void deplaceSur(Robot robot) {
		// TODO Auto-generated method stub
		this.contenu = robot;
	}

	@Override
	public
	void ajouteMine(int equipe) {
		// TODO Auto-generated method stub
		this.mine = equipe;
	}

	@Override
	public void videCase() {
		// TODO Auto-generated method stub
		this.contenu = null;
		this.mine = 0;
	}

	@Override
	void ajoute(int equipe) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.contenu.toString();
	}

}
