/**
 * @author Les Quatre Cavaliers de l'Apocalypse
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
	}
	
	/**
	 * Constructeur de l'IA durant un chargement de partie
	 * @param p
	 * @param equipe
	 * @param formation
	 */
	public IntelligenceArtificielle(Plateau p, int equipe, String formation) {
		this.plateau = p;
		this.formation = formation;
		this.equipe = equipe;
		robots = creerRobots();
	}
	

	/**
	 * Méthode appelée pour générer le meilleur déplacement que l'IA a choisis de réaliser
	 * @return Action (déplacement ou attaque)
	 */
	public Action Jouer(){
		checkMorts();
		Robot r = choixRobotJoue();
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
				return new Attaque(r, choixCibleMine(r)); 
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
	 * fonction attribuant des points à un robot par rapport à son positionnement et son énergie afin de trouver le meilleur robot à jouer
	 * @param Robot
	 * @return points
	 */
	public int attribuerPoints(Robot r){
		int points = 0;
		
		points += r.getEnergie();
		
		if(r.getPosition().estBase()){
			points += 200;
		}
		
		if(r instanceof Piegeur){
			points -= 20;
		}
		
		if(r instanceof Piegeur && choixCibleMine(r) == null && choixCibleDeplacement(r) == null){
			points =0;
		}
		
		if(choixCibleDeplacement(r) == null){
			points = 0;
		}
		
		if(robotAPortee(r) != null && r.getEnergie()>=constantes.getCoutAttaque(r)){
			points += 300;
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
		
		else if(robot instanceof Piegeur && robot.getNbMines() > 0 && robot.getEnergie() >= constantes.getCoutMine() && 
				distancePosition(robot.getPosition(), robotPlusPres(robot))<= 5 && distanceBase(robot)>= 2 && choixCibleMine(robot) != null){ 
			return "Attaque";
		}
		
		else if(robotAPortee(robot) != null && constantes.getCoutAttaque(robot) <= robot.getEnergie()){
			return "Attaque";
		}
		
		else{
			return "Deplacement";
		}
	}
	
	
	/**
	 * Verfie si il y a une mine visible pour l'IA sur une case (une mine de son équip)
	 * @param Position a tester
	 * @return boolean
	 */
	public boolean contientMine(Position p){
		if(p.estMine()){
			if(p.getEquipe() == this.getEquipe()){
				return true;
			}
			else{
				return false;
			}			
		}
		else{
			return false;
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
				int decalage;
				
				if(i == -2){
					decalage = -1;
				}
				else{
					decalage = 1;
				}
				
				
				if(posTemp != null){
					Position temp = new Position(posTemp.getX()-decalage,posTemp.getY());
					String tempStr = plateau.posToString(temp);
					temp = plateau.getCarte().get(tempStr);
					if(!temp.estObstacle() && !temp.estRobot()){
							depPossible.add(posTemp);
						}
					}
				
				if(posTemp2 != null){
					Position temp = new Position(posTemp2.getX() ,posTemp2.getY()-decalage);
					String tempStr = plateau.posToString(temp);
					temp = plateau.getCarte().get(tempStr);
					if(!temp.estObstacle() && !temp.estRobot()){
							depPossible.add(posTemp2);
						}
					}	
				}
			
			
			if(depPossible.size() == 0){
				return null;
			}

			
			List<Position> depSansMines = new ArrayList<Position>();
			List<Position> liste;
			
			for(Position p :depPossible){
				if(!contientMine(p)){
					depSansMines.add(p);
				}
			}
			
			if(depSansMines.size()>0){
				liste = depSansMines;
			}else{
				liste = depPossible;
			}
			
			Position depPlusProche = liste.get(0);
			int distance = distancePosition(liste.get(0), cible);
			for (Position p : liste) {
				if (distancePosition(p, cible) < distance) {
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
					if(posTemp != null &&!posTemp.estObstacle() && !posTemp.estRobot() && !posTemp.estBase()){
						depPossible.add(posTemp);
					}
				}
			}
			
			if(depPossible.size() == 0){
				return null;
			}

			
			List<Position> depSansMines = new ArrayList<Position>();
			List<Position> liste;
			
			for(Position p :depPossible){
				if(!contientMine(p)){
					depSansMines.add(p);
				}
			}
			
			if(depSansMines.size()>0){
				liste = depSansMines;
			}
			
			else{
				liste = depPossible;
			}
			
			Position depPlusProche = liste.get(0);
			int distance = distancePosition(liste.get(0), cible);
			for (Position p : liste) {
				if (distancePosition(p, cible) < distance) {
					depPlusProche = p;
					distance = distancePosition(p, cible);
				}
			}
			return depPlusProche;

		}
	}
	
	public Position choixCibleMine(Robot robot){
		Position posTemp;
		String strTemp;
		Position cible = robotPlusPres(robot);
		
		List<Position> depPossible = new ArrayList<Position>();
		
		for(int i = -1; i <= 1;i++){
			for(int j = -1; j <= 1;j++){
				posTemp = new Position(robot.getPosition().getX() + i,robot.getPosition().getY()+j);
				strTemp = plateau.posToString(posTemp);
				posTemp = plateau.getCarte().get(strTemp);
				if(posTemp != null &&!posTemp.estObstacle() && !posTemp.estRobot() && !posTemp.estBase() && !posTemp.estMine()){
					depPossible.add(posTemp);
				}
			}
		}
		
		if(depPossible.size() == 0){
			return null;
		}

		
		
		Position depPlusProche = depPossible.get(0);
		int distance = distancePosition(depPossible.get(0), cible);
		for (Position p : depPossible) {
			if (distancePosition(p, cible) < distance) {
				depPlusProche = p;
				distance = distancePosition(p, cible);
			}
		}
		return depPlusProche;

	}

	
	
	
	
	
	
	/**
	 * Calcul la distance entre 2 case du plateau
	 * @param première position
	 * @param deuxième position
	 * @return int
	 */
	public int distancePosition(Position p, Position cible){
		return (int)Math.pow(Math.pow((double)cible.getX()-p.getX(),2) + Math.pow((double)cible.getY()-p.getY(),2),0.5); //racine((xb-xa)² + (yb-ya)²) : distance entre 2 points
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
	 * Vérifie si il y a un ostacle entre 2 robots
	 * @param robot
	 * @param cible
	 * @return boolean
	 */
	public boolean champDegage(Robot r, Robot cible){
		Position posTemp;
		String strTemp;
		if(r.getPosition().getX() == cible.getPosition().getX()){
			for(int i =1; i < Math.abs(r.getPosition().getY() - cible.getPosition().getY());i++){
				if(r.getPosition().getY() < cible.getPosition().getY()){
					posTemp = new Position(r.getPosition().getX(), r.getPosition().getY() + i);
				}
				else{
					posTemp = new Position(r.getPosition().getX(), cible.getPosition().getY() + i);
				}
				strTemp = plateau.posToString(posTemp);
				posTemp = plateau.getCarte().get(strTemp);
				if(posTemp.estObstacle() || posTemp.estRobot()){
					return false;
				}
			}
			return true;
		}
		
		else if(r.getPosition().getY() == cible.getPosition().getY()){
			for(int i =1; i < Math.abs(r.getPosition().getX() - cible.getPosition().getX());i++){
				if(r.getPosition().getX() < cible.getPosition().getX()){
					posTemp = new Position(r.getPosition().getX() + i, r.getPosition().getY());
				}
				else{
					posTemp = new Position(cible.getPosition().getX() + i, r.getPosition().getY());
				}
				strTemp = plateau.posToString(posTemp);
				posTemp = plateau.getCarte().get(strTemp);
				if(posTemp.estObstacle() || posTemp.estRobot()){
					return false;
				}
			}
			return true;
		}
		
		else{ // si ni les X, ni les Y sont egaux alors les robots ne sont pas sur la même ligne
			return false;
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
			posTemp = new Position(PositionX + i, PositionY);
			strTemp = plateau.posToString(posTemp);
			posTemp = plateau.getCarte().get(strTemp);
			
			
			
			
			if(posTemp != null && posTemp.estRobot()&& posTemp.getRobot().getEquipe() != robot.getEquipe()&& champDegage(robot, posTemp.getRobot())){
				robotsPortee.add(posTemp.getRobot());
			}
			
			posTemp = new Position(PositionX, PositionY+ i);
			strTemp = plateau.posToString(posTemp);
			posTemp = plateau.getCarte().get(strTemp);
			
			

			if(posTemp != null && posTemp.estRobot()&& posTemp.getRobot().getEquipe() != robot.getEquipe()&& champDegage(robot, posTemp.getRobot())){
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
		int max;
		if(plateau.getLargeur() >= plateau.getHauteur()){
			max = plateau.getLargeur();
		}
		
		else{
			max = plateau.getHauteur();
		}
		
		int i =0;
		
		while(i < max){
			for(int x = -i; x <= i; x++){
				for(int y = -i; y <= i; y++){
				
					Position temp = new Position(pos.getX()+x, pos.getY()+y);
					String coordonnee = plateau.posToString(temp);
					temp = plateau.getCarte().get(coordonnee);
					if(temp != null && temp.estRobot()){
						if(temp.getRobot().getEquipe() != r.getEquipe()){
							return temp;
						}
					}
					if(temp != null && temp.estBase() && temp.getEquipe() != r.getEquipe()){
						return temp;
					}
					
				}
			}
		i++;
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
		for(int i = 0;i<3;i++){
			c = formation.charAt(i);
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
		Random r = new Random();

		for (int i = 0; i < 3; i++) {
			if (i < 2) {
				nbrRobots = r.nextInt(robotRestant + 1);
			} else {
				nbrRobots = robotRestant;
			}
			robotRestant -= nbrRobots;
			formationChoisis += nbrRobots;
		}

		return formationChoisis;
	}

	public String getFormation() {
		return this.formation;
	}
	
	public List<Robot> getRobots(){
		return this.robots;
	}
	
	public int getEquipe(){
		return this.equipe;
	}
	
	public void checkMorts(){
		List<Robot> toRemove = new ArrayList<Robot>();
		for(Robot r:robots){
			if(r.getEnergie()<=0){
				toRemove.add(r);
			}
		}
		for(Robot r : toRemove){
			retirerRobot(r);
		}
	}
	
	public void retirerRobot(Robot robot){
		int idx = -1;
		for(int i = 0; i<robots.size();i++){
			if(robot.equals(robots.get(i))){
				idx = i;
			}
		}
		robots.remove(idx);
	}

	
}
