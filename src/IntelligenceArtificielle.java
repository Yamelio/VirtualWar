/**
 * @author Aurélien SVEVI
 * @author Nicolas MAUGER lol
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntelligenceArtificielle {

	private String formation;
	private List<Robot> robots;
	private int equipe;	
	private Plateau plateau;
	private Constantes constantes = new Constantes();

	/**
	 * Constructeur de base de l'IA
	 */
	public IntelligenceArtificielle(Plateau p, int equipe) {
		formation = choixFormation();
		robots = creerRobots();
		this.equipe = equipe;
		this.plateau = p;
	}
	
	/**
	 * Choisis en début de tour quel robot devra jouer
	 * @return le robot choisis
	 */
	public Robot choixRobotJoue(){
		Random r = new Random();
		Robot temp;
		do{
			temp = robots.get(r.nextInt(5));
		}while(temp.getEnergie() < 5);
		
		return temp;
		
	}
	
	
	/**
	 * Calcul la meilleur action à faire pour le robot
	 * @param robot a tester
	 * @return type d'action (attaque ou déplacement)
	 */
	
	public String choixAction(Robot robot){
		
		if(robotAPortee(robot) != null){
			return "Attaque";
		}
		
		if(robot instanceof Piegeur && robot.getNbMines() > 0 && robot.getEnergie()>constantes.getCoutMine()){
			return "Attaque";
		}
		
		
		
		return "";
	}
	
	
	/**
	 * Verifie si le robot fournit en paramètre a un robot ennemi à portée
	 * @param robot a tester
	 * @return le robot le plus utile à viser
	 */
	public Robot robotAPortee(Robot robot){
		
		int portee;
		List<Robot> robotsPortee = new ArrayList<Robot>();
		
		if(robot instanceof Tireur){
			portee = constantes.getPorteeTireur();
		}
		
		else if(robot instanceof Piegeur){
			portee = constantes.getPorteePiegeur();
		}
		
		else{
			return null;
		}
		
		int PositionX = robot.getPosition().getX();
		int PositionY = robot.getPosition().getY();
		String strTemp;
		Position posTemp;
		
		for (int i = -portee; i <= portee; i++) {
			strTemp = plateau.posToString(new Position(PositionX + portee, PositionY));
			posTemp = plateau.getCarte().get(strTemp);
			if(posTemp.getRobot() != null){
				robotsPortee.add(posTemp.getRobot());
			}
		}
		
		if(robotsPortee.size() == 0){
			return null;
		}
		
		else if(robotsPortee.size() == 1){
			return robotsPortee.get(0);
		}
		
		else{
			Robot rTemp = robotsPortee.get(0);
			for(Robot r : robotsPortee){
				if(r.getEnergie() < rTemp.getEnergie()){ //tries les robots pour renvoyer celui avec le moins d'energie
					rTemp = r;
				}
			}
			return rTemp;
		}
		
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
			compt = (int)c -48; //car le chiffre 1 en ascii est codé par 48
			for(int j = 0; j<compt;j++){
				if(i == 0){
					r.add(new Tireur(equipe));
				}
				else if(i == 1){
					r.add(new Piegeur(equipe));
				}
				else if(i == 2){
					r.add(new Char(equipe));
				}
				
			}
			position += compt + 1;
			
		}
		return r;
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
			if (i < 2) {
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
	
	public List<Robot> getRobots(){
		return this.robots;
	}

	public static void main(String[] args) {
		IntelligenceArtificielle i = new IntelligenceArtificielle(null, 1);
		System.out.println(i.getFormation());
		System.out.println(i.getRobots());
	}

}
