import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {
	public JMenuBar menuBar;
	File currentFile = null;
	JTextArea historique = null;
	
	
	public Menu() {
		// Build the menu bar
		menuBar = new JMenuBar();

		// Build new tab "Menu"
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
				"The principal menu in this program");
		menuBar.add(menu);

		// Build new tab "Help"
		JMenu Help = new JMenu("Aide");
		menu.getAccessibleContext().setAccessibleDescription(
				"The help menu in this program");
		menuBar.add(Help);

		// Build new tab "About us"
		JMenu AboutUs = new JMenu("A propos de nous");
		menu.getAccessibleContext().setAccessibleDescription(
				"The link to developper page");
		menuBar.add(AboutUs);

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
				new IHM();
			}
		});

		// Build the item "Ouvrir" in "menu"
		JMenuItem menuOuvrir = new JMenuItem("Ouvrir");
		menuOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		menuOuvrir.getAccessibleContext().setAccessibleDescription(
				"Open a pevious game");
		menu.add(menuOuvrir);
		menuOuvrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				File file = fc.getSelectedFile();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Fichier de sauvegarde VirtualWar (.txt)", "txt",
						"text");
				fc.setFileFilter(filter);
				File workingDirectory = new File(System.getProperty("user.dir")
						+ "/save");
				fc.setCurrentDirectory(workingDirectory);
				int returnVal = fc.showOpenDialog(menuOuvrir);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ fc.getSelectedFile().getName());
				}
				//currentFile = file;
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
				if (currentFile == null) {
					JFileChooser fc = new JFileChooser();
					fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					File workingDirectory = new File(System
							.getProperty("user.dir") + "/save");
					fc.setCurrentDirectory(workingDirectory);
					int returnVal = fc.showSaveDialog(menuSave);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						// on ecrit dans le fichier comme pour un save as
						FileWriter writer = null;
						try {
							writer = new FileWriter(fc.getSelectedFile());
							writer.write(historique.getText());
						} catch (Exception e3) {
							JOptionPane.showMessageDialog(null,
									"Imbossible de sauvegarder le fichier",
									"Erreur", JOptionPane.ERROR_MESSAGE);
						} finally {
							try {
								writer.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				} else {
					// ecriture dans le fichier
					FileWriter writer = null;
					try {
						writer = new FileWriter(currentFile);
						writer.write(historique.getText());
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null,
								"Imbossible de sauvegarder le fichier",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							writer.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		// Build the item "SaveAs" in "menu"
		JMenuItem menuSaveAs = new JMenuItem("Sauvegarder sous");
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
					FileWriter writer = null;
					try {
						writer = new FileWriter(fc.getSelectedFile());
						writer.write(historique.getText());
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null,
								"Imbossible de sauvegarder le fichier",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} finally {
						try {
							writer.close();
						} catch (IOException e1) {

							e1.printStackTrace();
						}
					}
				}
			}
		});

		// Build the item "plein ecran" in menu
		menu.addSeparator();
		JMenuItem menuPleinEcran = new JMenuItem("Mode plein ecran");
		menuPleinEcran.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK));
		GraphicsDevice device = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		menuPleinEcran.getAccessibleContext().setAccessibleDescription(
				"Ce bouton met je jeu en plein ecran");
		menu.add(menuPleinEcran);
		menuPleinEcran.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					device.setFullScreenWindow(device.getFullScreenWindow());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null,
							"Impossible de mettre le jeu en plein ecran",
							"Erreur", JOptionPane.ERROR_MESSAGE);
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