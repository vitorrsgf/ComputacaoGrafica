package gui;

import javax.swing.JFrame;

public class DesenhoView extends JFrame {
	
	public DesenhoView() {
		getContentPane().setLayout(null);
		
		setSize(500, 500);
		
		JDesenho panel = new JDesenho();

		setContentPane(panel);
	}
	
	public static void main(String[] args) {
		DesenhoView d = new DesenhoView();
		d.setTitle("Desenhando com Retas (Bresenhan)");
		d.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		d.setVisible(true);
	}
}
