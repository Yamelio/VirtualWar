/**
 * @author Les Quatre Cavaliers de l'apocalypse
 * Classe permettant la sauvegarde d'une partie
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sauvegarde {

	public void sauvegarder(Plateau p, List<Action> liste) {
		try {
			File save = new File("save/sauvegarde.txt");
			save.createNewFile();
			FileWriter s = new FileWriter(save);
			s.write(p.getLargeur() + " " + p.getHauteur() + " "
					+ p.getPercentObstacle() + " ");

			for (Robot r : p.getListeRobot()) {
				s.write(r + " ");
			}
			for (int i = 1; i < p.getLargeur(); i++) {
				for (int k = 1; k < p.getHauteur(); k++) {
					if (p.getCarte().get(p.posToString(new Position(i, k)))
							.estObstacle()) {
						s.write(p.posToString(new Position(i, k)));
					}
				}
			}
			s.write(" ");

			for (Action a : liste) {
				s.write(a + " ");
			}

			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void charger() {
		
	}
}
