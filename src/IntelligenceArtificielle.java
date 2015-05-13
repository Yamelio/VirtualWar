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
		this.plateau = p;
		this.equipe = equipe;
		formation = choixFormation();
		robots = creerRobots();
		System.out.println(equipe);
	}
	
	/**
	 * Méthode appelée pour générer le meilleur déplacement que l'IA a choisis de réaliser
	 * @return Action (déplacement ou attaque)
	 */
	public Action Jouer(){
		Robot r = choixRobotJoue();
		if(r instanceof Char){
			System.out.println('C');
		}
		
		if(r instanceof Tireur){
			System.out.println('T');
		}
		
		if(r instanceof Piegeur){
			System.out.println('P');
		}
		
		String action = choixAction(r);
		if(action == "Deplacement"){
			try {
				return new Deplacement(r, choixCibleDeplacement(r));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(action == "Attaque" && !(r instanceof Piegeur)){
			try {
				return new Attaque(r, robotAPortee(r).getPosition());
			} catch (Erreur e) {
				e.printStackTrace();
			}
		}
		
		
		
		else if(action == "Attaque" && r instanceof Piegeur){
			try {
				return new Attaque(r, choixCibleDeplacement(r)); // utilise choixCibleDeplacement() pour poser la mine au plus près du robot adverse
			} catch (Erreur e) {
				e.printStackTrace();
			}

		}

		return null;
	}
	
		
	/**
	 * Choisis en début de tour quel robot devra jouer
	 * @return le robot choisis
	 */
	public Robot choixRobotJoue(){

		Robot robTemp = robots.get(0);
		
		for(Robot r : robots){
			if(attribuerPoints(r) > attribuerPoints(robTemp)){
				robTemp = r;
			}
		}
		return robTemp;
	}
	
	
	
	/**
	 * fonction attribuant des points à un robot par raapoort à son positionnement et son énergie afin de trouver le meilleur robot à jouer
	 * @param Robot
	 * @return points
	 */
	public int attribuerPoints(Robot r){
		int points = 0;
		
		points += r.getEnergie();
		
		if(r.getPosition().estBase()){
			points += 200;
		}
		
		if(robotAPortee(r) != null){
			points += 150;
		}
		
		if(choixCibleDeplacement(r) == null){
			points = 0;
		}
			
		return points;
	}
	
	/**
	 * Calcul la meilleur action à faire pour le robot
	 * @param robot a tester
	 * @return type d'action (attaque ou déplacement)
	 */
	
	public String choixAction(Robot robot){
		
		if(robot.getPosition().estBase()){
			return "Deplacement";
		}
		
		else if(robotAPortee(robot) != null && constantes.getCoutAttaque(robot) >= robot.getEnergie()){
			return "Attaque";
		}
		
		else if(robot instanceof Piegeur && robot.getNbMines() > 0 && robot.getEnergie() > constantes.getCoutMine() && distancePosition(robot.getPosition(), robotPlusPres(robot))<= 5){ 
			System.out.println(distancePosition(robot.getPosition(), robotPlusPres(robot)));
			return "Attaque";
		}
		
		else{
			return "Deplacement";
		}
	}
	

	/**
	 * Choisit la meilleur case où déplacer le robot
	 * @param robot
	 * @return Position choisis
	 */

	public Position choixCibleDeplacement(Robot robot){
		Position posTemp;
		String strTemp;
		Position cible = robotPlusPres(robot);
		if(robot instanceof Char){
			Position posTemp2;
			String strTemp2;
			List<Position> depPossible = new ArrayList<Position>();
			for(int i = -2; i <= 2 ; i +=4){
				
				posTemp = new Position(robot.getPosition().getX() + i,robot.getPosition().getY());
				posTemp2 = new Position(robot.getPosition().getX(),robot.getPosition().getY()+i);
				strTemp = plateau.posToString(posTemp);
				strTemp2 = plateau.posToString(posTemp2);
				posTemp = plateau.getCarte().get(strTemp);
				posTemp2 = plateau.getCarte().get(strTemp2);
				
				if(posTemp != null && (!posTemp.estBase() && posTemp.getEquipe() != robot.getEquipe())){
					if(posTemp.estObstacle() && posTemp.estRobot()){
						Position temp = new Position(posTemp.getX() - 1,posTemp.getY());
						String tempStr = plateau.posToString(temp);
						temp = plateau.getCarte().get(tempStr);
						if(!temp.estObstacle() && !temp.estRobot()){
							depPossible.add(posTemp);
						}
					}
					else if (!posTemp.estObstacle() && !posTemp.estRobot()){
						depPossible.add(posTemp);
					}
				}
				
				if(posTemp2 != null && (!posTemp2.estBase() && posTemp2.getEquipe() != robot.getEquipe())){
					if(posTemp2.estObstacle() && posTemp2.estRobot()){
						Position temp = new Position(posTemp2.getX() - 1,posTemp2.getY());
						String tempStr = plateau.posToString(temp);
						temp = plateau.getCarte().get(tempStr);
						if(!temp.estObstacle() && !temp.estRobot()){
							depPossible.add(posTemp2);
						}
					}
					else if (!posTemp2.estObstacle() && !posTemp2.estRobot()){
						depPossible.add(posTemp2);
					}
				}
			
		
			}
			Position depPlusProche;

			try {
				depPlusProche = depPossible.get(0);
			} catch (IndexOutOfBoundsException e) {
				return null;
			}

			int distance = distancePosition(depPossible.get(0), cible);
			for (Position p : depPossible) {
				if (distancePosition(p, cible) > distance) {
					depPlusProche = p;
					distance = distancePosition(p, cible);
				}
			}
			return depPlusProche;

		}
		
		else{//piégeur ou tireur

			List<Position> depPossible = new ArrayList<Position>();
			
			for(int i = -1; i <= 1;i++){
				for(int j = -1; j <= 1;j++){
					posTemp = new Position(robot.getPosition().getX() + i,robot.getPosition().getY()+j);
					strTemp = plateau.posToString(posTemp);
					posTemp = plateau.getCarte().get(strTemp);
					if(posTemp != null &&!posTemp.estObstacle() && !posTemp.estRobot() && (!posTemp.estBase() && posTemp.getEquipe() != robot.getEquipe())){
						depPossible.add(posTemp);
					}
				}
			}
			
			Position depPlusProche;
			
			try{
				depPlusProche = depPossible.get(0);
			} catch (IndexOutOfBoundsException e){
				return null;
			}
				
				
				
				
			int distance = distancePosition(depPossible.get(0), cible);
			for(Position p:depPossible){
				if(distancePosition(p, cible) > distance){
					depPlusProche = p;
					distance = distancePosition(p, cible);
				}
			}
			
			
			return depPlusProche;

		}

	}
	
	/**
	 * Calcul la distance entre 2 case du plateau
	 * @param première position
	 * @param deuxième position
	 * @return int
	 */
	public int distancePosition(Position p, Position cible){
		return Math.abs(cible.getX() - p.getX()) + Math.abs(cible.getY() - p.getY());
	}
	
	
	/**
	 * Calcul la distance entre un Robot et une base
	 * @param Robot
	 * @return int
	 */
	public int distanceBase(Robot r){
		if(equipe == 0){
			return distancePosition(r.getPosition(), new Position(1, 1));
		}
		
		else{
			return distancePosition(r.getPosition(), new Position(plateau.getLargeur(), plateau.getHauteur()));
		}
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
		
		else if(robot instanceof Char){
			portee = constantes.getPorteeChar();
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
			if(posTemp != null && posTemp.getRobot() != null){
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
	 * Renvoi la position du robot le plus prêt du robot en paramètre
	 * @param robot
	 * @return Position
	 */
	public Position robotPlusPres(Robot r){
		Position pos = r.getPosition();
		for(int i = -distanceBord(r)[0] ; i<= distanceBord(r)[0]; i++){
			for (int j = -distanceBord(r)[1]; j <= distanceBord(r)[1]; j++) {
				if(pos.getX() + i > 0  && pos.getX() + i < plateau.getLargeur() && pos.getY() + j > 0  && pos.getY() + j < plateau.getHauteur()){
					Position temp = new Position(pos.getX()+i, pos.getY()+j);
					String coordonnee = plateau.posToString(temp);
					temp = plateau.getCarte().get(coordonnee);
					if(temp.estRobot()){
						if(temp.getRobot().getEquipe() != r.getEquipe()){
							return temp;
						}
					}
					if(temp.estBase() && temp.getEquipe() != r.getEquipe()){
						return temp;
					}
				}
			}
		}
		return baseAdverse();
	}
	
	
	
	
	/**
	 * Retourne la Position de la base adverse
	 * @return Position
	 */
	public Position baseAdverse(){
		Position posTemp;
		String strTemp;
		if(equipe == 0){;
			posTemp = new Position(1, 1);
		}
		else{
			posTemp = new Position(plateau.getLargeur(), plateau.getHauteur());
		}
		
		strTemp = plateau.posToString(posTemp);
		return plateau.getCarte().get(strTemp);
	}
	
	
	
	
	
	
	/**
	 * Renvoi la distance du bord le plus éloigné d'un robot
	 * @param robot
	 * @return int
	 */
	public int[] distanceBord(Robot r){
		int[] distance = new int[2];
		Position pos = r.getPosition();
		if(pos.getX()< plateau.getLargeur())
		{
			 distance[0] = plateau.getLargeur()-pos.getX();
		}
		else if(pos.getX()>= plateau.getLargeur()){
			distance[0] = pos.getX();
		}
		
		if(pos.getY()< plateau.getHauteur())
		{
			 distance[1] = plateau.getHauteur()-pos.getY();
		}
		else if(pos.getY()>= plateau.getHauteur()){
			distance[1] = pos.getY();
		}
		return distance;
		
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


}
