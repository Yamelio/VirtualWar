import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TestIHM extends JPanel {

	public class Case extends Polygon {
		private Position p;

		public Case(Polygon pol, Position pos) {
			super(pol.xpoints, pol.ypoints, pol.npoints);
			this.p = pos;
		}

		public void setPosition(Position p) {
			this.p = p;
		}

		public Position getPosition() {
			return p;
		}
	}

	List<Case> liste = new ArrayList<Case>();
	static Image zero = Toolkit.getDefaultToolkit().getImage("img/0.png");
	static Image un = Toolkit.getDefaultToolkit().getImage("img/1.png");
	static Image deux = Toolkit.getDefaultToolkit().getImage("img/2.png");
	static Image trois = Toolkit.getDefaultToolkit().getImage("img/3.png");
	static Case survol;
	static int hauteur = 100;
	static int largeur = 100;
	private static List<Action> actions = new ArrayList<Action>();
	private static Plateau p;
	private static Robot robotChoisi;

	// private static List<Robot> robotsInit;

	public TestIHM() {

		super();

		for (int i = 1; i <= Position.getPlateau().getHauteur(); i++) {
			for (int k = 1; k <= Position.getPlateau().getLargeur(); k++) {
				int isoX = i * hauteur / 2 - k * largeur / 2;
				int isoY = (i * hauteur / 2 + k * largeur / 2) / 2;
				liste.add(new Case((new Polygon(new int[] { isoX + 250,
						isoX + 250 + (largeur / 2), isoX + 250 + largeur,
						isoX + 250 + (largeur / 2) }, new int[] {
						isoY + (3 * hauteur) / 4, isoY + hauteur,
						isoY + (3 * hauteur) / 4, isoY + (hauteur / 2) }, 4)),
						Position.getPlateau()
								.getCarte()
								.get(Position.getPlateau().posToString(
										new Position(i, k)))));
			}
		}
		JFrame back = new JFrame("");
		back.getContentPane().add(this);
		back.setLocation(0, 0);
		back.setSize(500, 500);
		back.setVisible(true);
		back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {

			}

			public void mouseMoved(MouseEvent e) {
				survol = null;
				for (Case c : liste) {
					if (c.contains(e.getX(), e.getY())) {
						survol = c;
						break;

					}
				}
				repaint();
			}

		});
		this.addMouseListener(new MouseListener() {

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
				for (Case c : liste) {
					if (c.contains(e.getX(), e.getY())) {
						if (c.getPosition().estRobot()) {
							robotChoisi = c.getPosition().getRobot();
						} else {
							try {
								actions.add(new Deplacement(robotChoisi, c
										.getPosition()));
							} catch (Erreur e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
				repaint();

			}
		});
		this.setVisible(true);

	}

	public void paint(Graphics g) {
		// g.clearRect(0, 0, 1000, 1000);
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		for (Case c : liste) {
			Image draw = zero;
			if (c.getPosition().estBase()) {
				draw = un;
			} else {
				if (c.getPosition().estRobot()) {
					draw = trois;
				} else {
					if (survol != null) {
						if (c.xpoints.equals(survol.xpoints)
								&& c.ypoints.equals(survol.ypoints)) {
							draw = deux;
						}
					}
				}
			}
			g.drawImage(draw, c.xpoints[0], c.ypoints[1] - hauteur, hauteur,
					largeur, this);
		}
	}

	public void animer() {
		boolean fin = false;

		repaint();

		/*
		 * while (fin == false) { this.repaint(); try { Thread.sleep(100l); }
		 * catch (InterruptedException e) { e.printStackTrace(); } }
		 */
	}

	public static void main(String[] args) {
		p = new Plateau(5, 5, 0);
		p.initObstacles();
		p.ajouterListeRobot(new Tireur(0));
		p.ajouterListeRobot(new Piegeur(0));
		p.ajouterListeRobot(new Char(0));
		p.ajouterListeRobot(new Tireur(1));
		p.ajouterListeRobot(new Piegeur(1));
		p.ajouterListeRobot(new Char(1));
		Position.setPlateau(p);

		try {
			actions.add(new Deplacement(p.getListeRobot().get(0), p
					.stringToPos("B2")));
			actions.add(new Deplacement(p.getListeRobot().get(4), p
					.stringToPos("D4")));
		} catch (Erreur e) {
			e.printStackTrace();
		}
		new TestIHM();
	}
}
