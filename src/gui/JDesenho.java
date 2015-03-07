package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import utils.Primitiva;

public class JDesenho extends JPanel {


	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		int [] pontoCentral = this.retornaPontoCentralDoPainel();
		
		int xCentro = pontoCentral[0];
		int yCentro = pontoCentral[1];
		
		Primitiva.drawHLine(g, 0, this.getWidth(), yCentro, JDesenho.this);
		Primitiva.drawVLine(g, xCentro, 0, this.getHeight(), JDesenho.this);
		
		g.setColor(Color.RED);
		Primitiva.drawLineBresenhan(g, 0, 0, 8, 120, pontoCentral, JDesenho.this);
		
		g.setColor(Color.BLUE);
		//Primitiva.drawLineBresenhan(g, 7, 7, -120, 89, pontoCentral, JDesenho.this);
		
		g.setColor(Color.GREEN);
		//Primitiva.drawLineBresenhan(g, -5, -5, -80, -120, pontoCentral, JDesenho.this);
		
		g.setColor(Color.ORANGE);
		//Primitiva.drawLineBresenhan(g, 8, -6, 120, -80, pontoCentral, JDesenho.this);
	}
	
	private int[] retornaPontoCentralDoPainel() {
		
		int x = this.getWidth();

		if (x % 2 != 0) {
			x += 1;
		}
				
		x = x / 2;
		
		int y = this.getHeight();
		
		if (y % 2 != 0) {
			y += 1;
		}
		
		y = y / 2;
		
		int [] ponto = { x, y };
		
		return ponto;
	}
	
}
