import java.awt.Desktop;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {
	public JMenuBar menuBar;
	JTextArea historique = null;

	public Menu(JFrame frame) {
		// Build the menu bar
		menuBar = new JMenuBar();

		// Build new tab "Menu"
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
				"The principal menu in this program");
		menuBar.add(menu);

		// Build new tab "Help"
		JMenu help = new JMenu("Aide");
		help.getAccessibleContext().setAccessibleDescription(
				"The help menu in this program");
		help.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop desktop = Desktop.getDesktop();
					desktop.open(new File("README.txt"));
				} catch (Exception e2) {
					// Probl�me lors du lancement du programme
					e2.printStackTrace();
				}
				
			}
		});
			
		menuBar.add(help);

		// Build new tab "About us"
		JMenu aboutUs = new JMenu("A propos de nous");
		aboutUs.getAccessibleContext().setAccessibleDescription(
				"The link to developper page");
		aboutUs.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				try {
					URI uriLien = new URI(
							"https://github.com/Yamelio/VirtualWar");
					Desktop.getDesktop().browse(uriLien);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException ex) {
					Logger.getLogger(Menu.class.getName()).log(Level.SEVERE,
							null, ex);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub

			}
		});
		menuBar.add(aboutUs);

		// Build the item "Nouveau" in "menu"
		JMenuItem menuNouveau = new JMenuItem("Nouveau");
		menuNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		menuNouveau.getAccessibleContext().setAccessibleDescription(
				"Open a new windows");
		menu.add(menuNouveau);
		menuNouveau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Parametrage();
				frame.dispose();
			}
		});

		// Build the item "Ouvrir" in "menu"
		final JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
		menuOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		menuOuvrir.getAccessibleContext().setAccessibleDescription(
				"Open a pevious game");
		menu.add(menuOuvrir);
		menuOuvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier de sauvegarde VirtualWar (.txt)", "txt",
						"text");
				fc.setFileFilter(filter);
				File workingDirectory = new File(System.getProperty("user.dir")
						+ "/save");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showOpenDialog(menuOuvrir);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					IHM.savePath = fc.getSelectedFile().getPath();
					IHM.PlateauIHM.chargement(IHM.savePath);
				}

			}
		});

		// Build the item "Save" in "menu"
		JMenuItem menuSave = new JMenuItem("Sauvegarder");
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		menuSave.getAccessibleContext().setAccessibleDescription(
				"Open current game");
		menu.add(menuSave);
		menuSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IHM.PlateauIHM.sauvegarde(IHM.savePath);
			}
		});

		// Build the item "SaveAs" in "menu"
		final JMenuItem menuSaveAs = new JMenuItem("Sauvegarder sous");
		menuSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_DOWN_MASK));
		menuSave.getAccessibleContext().setAccessibleDescription(
				"Open current game as");
		menu.add(menuSaveAs);
		menuSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				File workingDirectory = new File(System.getProperty("user.dir")
						+ "/save");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showSaveDialog(menuSaveAs);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						IHM.savePath = fc.getSelectedFile().getPath();
						if (IHM.savePath.length() > 4) {
							if (!IHM.savePath.substring(
									IHM.savePath.length() - 4,
									IHM.savePath.length()).equals(".txt")) {
								IHM.savePath = IHM.savePath + ".txt";
							}
						}
						IHM.PlateauIHM.sauvegarde(IHM.savePath);
					} catch (Exception e3) {
						e3.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Imbossible de sauvegarder le fichier",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// Build the item "plein ecran" in menu
		menu.addSeparator();
		JMenuItem menuPleinEcran = new JMenuItem("Toogle Musique");
		menuPleinEcran.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK));
		final GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		menuPleinEcran.getAccessibleContext().setAccessibleDescription(
				"Ce bouton switch la musique");
		menu.add(menuPleinEcran);
		menuPleinEcran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Accueil.musique.isAlive()) {
						Accueil.musique.stop();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Erreur avec la musique", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Build the item "Exit" in "menu"
		menu.addSeparator();
		JMenuItem menuExit = new JMenuItem("Quitter");
		menuExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_DOWN_MASK));
		menuExit.getAccessibleContext().setAccessibleDescription(
				"Ce bouton quitte le programme");
		menu.add(menuExit);
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
