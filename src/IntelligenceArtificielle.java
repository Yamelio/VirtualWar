/**
 * @author Aurélien SVEVI
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntelligenceArtificielle {

	private String formation;
	private List<Robot> robots;
	private int equipe;	

	/**
	 * Constructeur de base de l'IA
	 */
	public IntelligenceArtificielle() {
		formation = choixFormation();
		//robots = creerRobots();
		this.equipe = equipe;

	}


	/**
	 * Création de la List des robots à partir de la formation
	 * @return la liste des robots
	 */
	
	private List<Robot> creerRobots() {
		List<Robot> r = new ArrayList<Robot>();
		char c;
		int compt;
		int position = 0;
		for(int i = 0;i<3;i++){
			c = formation.charAt(position);
			compt = (int)c;
			for(int j = 0; j<compt;j++){
				if(i == 0){
					r.add(new Tireur(equipe,r.size()+1));
				}
				else if(i == 1){
					r.add(new Piegeur(equipe,r.size()+1));
				}
				else if(i == 2){
					r.add(new Char(equipe,r.size()+1));
				}
				
					position ++;
			}
			position += 1;
			
		}

	}


	/**
	 * Génère la formation que l'IA va adopter de manière aléatoire et sous la forme d'un String
	 * @return Un String contenant le nombre de chaques types de robots avec leurs types de formations
	 */

	public String choixFormation() {
		int robotRestant = 5;
		int nbrRobots;
		String formationChoisis = "";
		char[] typeFormation = new char[] { 'a', 'd', 'e' };
		Random r = new Random();

		for (int i = 0; i < 3; i++) {
			if (i <= 2) {
				nbrRobots = r.nextInt(robotRestant + 1);
			} else {
				nbrRobots = robotRestant;
			}
			robotRestant -= nbrRobots;
			formationChoisis += nbrRobots;
			for (int j = 0; j < nbrRobots; j++) {
				formationChoisis += typeFormation[r.nextInt(3)];
			}
		}

		return formationChoisis;
	}

	public String getFormation() {
		return this.formation;
	}

	public static void main(String[] args) {
		IntelligenceArtificielle i = new IntelligenceArtificielle();
		System.out.println(i.getFormation());
	}

}
