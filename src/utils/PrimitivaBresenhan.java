package utils;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PrimitivaBresenhan {

	public static ArrayList<Integer[]> drawLineBresenhan(Graphics g, int x1, int y1, int x2, int y2, JPanel painel) {

		ArrayList<Integer[]> conjPontosDaReta = new ArrayList<Integer[]>(0); 
		
		Integer[] ponto = null;
		
		int dx = Math.abs( x2 - x1 );
		int dy = Math.abs( y2 - y1 );
		int d = Math.abs( 2 * dy - dx ); /* Valor inicial de d */
		
		int incE = 2 * dy; /* Incremento de E */
		int incNE = 2 * dy - dx; /* Incremento de NE */
		int x = x1;
		int y = y1;
		
		System.out.println("dx = " + dx);
		System.out.println("dy = " + dy);
		System.out.println("d = " + d);
		System.out.println("incE = " + incE);
		System.out.println("incNE = " + incNE);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		Primitiva.drawPixel(g, x, y);
		
		ponto = new Integer[2];
		ponto[0] = x;
		ponto[1] = y;
		
		conjPontosDaReta.add( ponto );
		
		while ( x < x2 ){
			
			if (d <= 0){
				// Escolhe E
				d = d + incE;
				x++;
			} else {
				// Escolhe NE
				d = d + incNE;
				x++;
				y--;
			}
			
			System.out.println("x = " + x);
			System.out.println("y = " + y);
			
			Primitiva.drawPixel(g, x, y);
			
			ponto = new Integer[2];
			ponto[0] = x;
			ponto[1] = y;
			
			conjPontosDaReta.add( ponto );
		} 
		
		return conjPontosDaReta;
	}
	
}
