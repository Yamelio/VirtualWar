import java.awt.Frame;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
/**
 * Construit une nouvelle exception
 */
public class Erreur extends Exception {

	public Erreur(){
		super();
	}
	
	public Erreur(String s) {
		super(s);
	}

	public void getG01(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur G01 : Sélection du robot impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG02(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur G02 : Action impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG03(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur G03 : Tir impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG04(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur G04 : Impossible de poser la mine", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG05(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur G05 : Impossible de se déplacer dans cette direction", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getT01(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur T01 : Valeurs incompatible!\n Les valeurs par défault seront appliquées.", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}

	public void getT02(Frame f){
		JOptionPane.showMessageDialog(f, "Erreur T02 : Valeurs non comprises dans l'intervalle! (0-3) \n Les valeurs par défault seront appliquées.", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}

}
