/**
 * @author AurÈlien SVEVI
 * 
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
	 * M√©thode appel√©e pour g√©n√©rer le meilleur d√©placement que l'IA a choisis de r√©aliser
	 * @return Action (d√©placement ou attaque)
	 */
	public Action Jouer(){
		Robot r = choixRobotJoue();
		String action = choixAction(r);
		if(action == "Attaque" && !(r instanceof Piegeur)){
			return new Attaque(r, robotAPortee(r).getPosition());
		}
		
		return null;
	}
	
		
	/**
	 * Choisis en d√©but de tour quel robot devra jouer
	 * @return le robot choisis
	 */
	public Robot choixRobotJoue() {
		Random r = new Random();
		Robot temp;
		do {
			temp = robots.get(r.nextInt(5));
		} while (temp.getEnergie() < 5);

		return temp;

	}

	/**
	 * Calcul la meilleur action ‡ faire pour le robot
	 * 
	 * @param robot
	 *            a tester
	 * @return type d'action (attaque ou dÈplacement)
	 */

	public String choixAction(Robot robot) {

		if (robotAPortee(robot) != null) {
			return "Attaque";
		}
		
		if(robot instanceof Piegeur && robot.getNbMines() > 0 && robot.getEnergie() > constantes.getCoutMine()){ // !!!!!!!!! √† modifier, poses des mines trop souvent
			return "Attaque";
		}
		
		if(robot.getPosition().estBase()){
			return "Deplacement";
		}
		
		return "Deplacement";
	}
	
	
	
	public Position choixCibleDeplacement(Robot robot){//!!!!!!!!!!!!!!!!A TERMINER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		Position pos;
		for(int i = -1;i<=1;i++){
			for(int j =-1;j<=1;j++){
				pos = plateau.getCarte().get(plateau.posToString(new Position(robot.getPosition().getX()+i,robot.getPosition().getY()+j)));
				
				if(!pos.estObstacle() && !pos.estRobot()){
					if(robot.getEquipe() == 0){
						if(robot.getEnergie()>3*constantes.getCoutDeplacement(robot) && pos.getX()>robot.getPosition().getX()){//essaye de se rapporcher de la base adverse
							return pos;
						}
						
						else if(robot.getEnergie()<= 3*constantes.getCoutDeplacement(robot) && pos.getX()<robot.getPosition().getX()){
							return pos;
						}
						
						
					}
					else if(robot.getEquipe() == 1){
						if(robot.getEnergie()>3*constantes.getCoutDeplacement(robot) && pos.getX()<robot.getPosition().getX()){
							return pos;
						}
						
						else if(robot.getEnergie()<= 3*constantes.getCoutDeplacement(robot) && pos.getX()>robot.getPosition().getX()){
							return pos;
						}
					}
				}
			}
		}		
		return null;
	}
	
	
		if (robot instanceof Piegeur && robot.getNbMines() > 0
				&& robot.getEnergie() > constantes.getCoutMine()) {
			return "Attaque";
		}

		return "";
	}

	/**
	 * Verifie si le robot fournit en paramËtre a un robot ennemi ‡ portÈe
	 * 
	 * @param robot
	 *            a tester
	 * @return le robot le plus utile ‡ viser
	 */
	public Robot robotAPortee(Robot robot) {

		int portee;
		List<Robot> robotsPortee = new ArrayList<Robot>();

		if (robot instanceof Tireur) {
			portee = constantes.getPorteeTireur();
		}
		
		else if(robot instanceof Char){
			portee = constantes.getPorteeChar();

		else if (robot instanceof Piegeur) {
			portee = constantes.getPorteePiegeur();
		}

		else {
			return null;
		}

		int PositionX = robot.getPosition().getX();
		int PositionY = robot.getPosition().getY();
		String strTemp;
		Position posTemp;

		for (int i = -portee; i <= portee; i++) {
			strTemp = plateau.posToString(new Position(PositionX + portee,
					PositionY));
			posTemp = plateau.getCarte().get(strTemp);
			if (posTemp.getRobot() != null) {
				robotsPortee.add(posTemp.getRobot());
			}
		}

		if (robotsPortee.size() == 0) {
			return null;
		}

		else if (robotsPortee.size() == 1) {
			return robotsPortee.get(0);
		}

		else {
			Robot rTemp = robotsPortee.get(0);
			for (Robot r : robotsPortee) {
				if (r.getEnergie() < rTemp.getEnergie()) { // tries les robots
															// pour renvoyer
															// celui avec le
															// moins d'energie
					rTemp = r;
				}
			}
			return rTemp;
		}

	}

	/**
	 * CrÈation de la List des robots ‡ partir de la formation
	 * 
	 * @return la liste des robots
	 */

	private List<Robot> creerRobots() {
		List<Robot> r = new ArrayList<Robot>();
		char c;
		int compt;
		int position = 0;
		for (int i = 0; i < 3; i++) {
			c = formation.charAt(position);
			compt = (int) c - 48; // car le chiffre 1 en ascii est codÈ par 48
			for (int j = 0; j < compt; j++) {
				if (i == 0) {
					r.add(new Tireur(equipe));
				} else if (i == 1) {
					r.add(new Piegeur(equipe));
				} else if (i == 2) {
					r.add(new Char(equipe));
				}
			}
			position += compt + 1;

		}
		return r;
	}

	/**
	 * GÈnËre la formation que l'IA va adopter de maniËre alÈatoire et sous la
	 * forme d'un String
	 * 
	 * @return Un String contenant le nombre de chaques types de robots avec
	 *         leurs types de formations
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

	public List<Robot> getRobots() {
		return this.robots;
	}

	public static void main(String[] args) {
		IntelligenceArtificielle i = new IntelligenceArtificielle(null, 1);
		System.out.println(i.getFormation());
		System.out.println(i.getRobots());
	}

}
