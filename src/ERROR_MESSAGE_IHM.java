import javax.swing.JOptionPane;


public class ERROR_MESSAGE_IHM {	

	public void getG01(){
		JOptionPane.showMessageDialog(null, "Erreur G01 : Sélection du robot impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG02(){
		JOptionPane.showMessageDialog(null, "Erreur G02 : Action impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG03(){
		JOptionPane.showMessageDialog(null, "Erreur G03 : Tir impossible", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG04(){
		JOptionPane.showMessageDialog(null, "Erreur G04 : Impossible de poser la mine", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getG05(){
		JOptionPane.showMessageDialog(null, "Erreur G05 : Impossible de se déplacer dans cette direction", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
	
	public void getT01(){
		JOptionPane.showMessageDialog(null, "Erreur T01 : Caractère incompatible!\n Veuillez enter un nombre entier.", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}

	public void getT02(){
		JOptionPane.showMessageDialog(null, "Erreur T02 : Valeur incompatible!\n Veuillez enter un entier compris dans l'intervalle.", "VirtualWar", JOptionPane.ERROR_MESSAGE);
	}
}
