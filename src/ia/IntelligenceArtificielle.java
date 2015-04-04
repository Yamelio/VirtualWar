package ia;

import java.util.Random;

public class IntelligenceArtificielle {
	
	private String formation;
	
	
	public IntelligenceArtificielle() {
		formation = choixFormation();
		
	}
	
	public String choixFormation(){
		int robotRestant = 5;
		int nbrRobots;
		String formationChoisis = "";
		char[] typeFormation = new char[]{'a','d','e'};
		Random r = new Random();
		
		for(int i = 0;i<3;i++){
			if(i<= 2){
				nbrRobots = r.nextInt(robotRestant +1);
			}
			else{
				nbrRobots = robotRestant;
			}
			robotRestant -= nbrRobots;
			formationChoisis += nbrRobots;
			for(int j =0; j<nbrRobots;j++){
				formationChoisis += typeFormation[r.nextInt(3)];
			}
		}
		
		
		return formationChoisis;
	}
	
	public String getFormation()
	{
		return this.formation;
	}
	
	public static void main(String[] args) {
		IntelligenceArtificielle i = new IntelligenceArtificielle();
		System.out.println(i.getFormation());
	}
	

}
