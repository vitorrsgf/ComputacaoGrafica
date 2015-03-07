package utils;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Primitiva {

	public static void drawPixel(Graphics g, int x, int y) {
		g.drawLine(x, y, x, y);
	}

	public static void drawHLine(Graphics g, int x1, int x2, int y, JPanel painel) {

		y = painel.getHeight() - y;
		
		for (int i = x1; i <= x2; i++) {
			Primitiva.drawPixel(g, i, y);
		}
	}
	
	public static void drawVLine(Graphics g, int x, int y1, int y2, JPanel painel) {

		y1 = painel.getHeight() - y1;
		y2 = painel.getHeight() - y2;
		
		for (int i = y1; i >= y2; i--) {
			Primitiva.drawPixel(g, x, i);
		}
	}
	//Primitiva.drawLine(g, 20, 5, 200, 185);
	//Começar no ponto (20,5)
	//y = mx + c
	//m = ( y - y1 ) / ( x - x1 )
	//c = y1 - ( m * x1 )
	
	public static void drawLine(Graphics g, int x1, int y1, int x2, int y2) {

		double m = (double)( y2 - y1 ) / (double)( x2 - x1 );
		
		System.out.println(m);
		
		double b = y1 - (double)( m * x1 );
		
		System.out.println(b);
		
		for (int i = x1; i <= x2; i++) {
			Primitiva.drawPixel(g, i, (int)(m * i + b));
		}
	}
	//
	/*
	 * ORIGINAL
	 * 		R2  = (7, 7) (-120, 89)
	 * 
	 * 0. DESLOCA EIXO DAS COORDENADAS
	 * 		R2 = ( (7 + xCentro), (7 + yCentro) ) ( (-120 + xCentro), (89 + yCentro) )
	 * 
	 * 1. ORDENA X 
	 * 		R2' = (-120, 7) (7, 89)
	 * 
	 * 2. TRANSFERE X INICIAL PARA O CENTRO -- NÃO TEM NECESSIDADE
	 * 		R2'' = (0, 7) (127, 89)
	 * 
	 * 3. CONVERTE Ys PARA HEMISFÉRIO SUL
	 * 		y = painel.height - y
	 * 
	 * 4. ORDENA Y INVERSO
	 * 		R2''' = (0, (500 - 7)) (127, (500 - 89)) => R2''' = (0, 493) (127, 411)
	 * 
	 * 5. TRANSFERE Y INICIAL PARA O CENTRO -- NÃO TEM NECESSIDADE
	 * 		R2'''' = (0, 0) (127, 82)
	 * 
	 * 6. CALCULA COEFICIENTE ANGULAR DA RETA
	 * 		m = (y2) / (x2)
	 * 
	 * 7. SE m MAIOR QUE 1 (2o OCTANTE)
	 * 		x2Aux = x2 
	 * 		x2 = y2
	 * 		y2 = x2Aux
	 * 
	 * 8. DESENHA A RETA COM BRESENHAN
	 * 		int[][] conjPontosDaReta = aplicaBresenhan(g, x1, y1, x2, y2, painel)
	 * 
	 * */
	public static void drawLineBresenhan(Graphics g, int x1, int y1, int x2, int y2, int[] pontoCentral, JPanel painel) {
		
		int xCentro = pontoCentral[0];
		int yCentro = pontoCentral[1];
		
		int x1Novo = x1; //coordenadasAjustadasParaOCentro[0];
		int y1Novo = y1; //coordenadasAjustadasParaOCentro[1];
		int x2Novo = x2; //coordenadasAjustadasParaOCentro[2];
		int y2Novo = y2; //coordenadasAjustadasParaOCentro[3];
		
		// PASSO 1 - ORDENA X
		int[] arrayX = retornaCoordenadasAjustadasDaMenorParaAMaior(x1Novo, x2Novo, false);
		
		x1Novo = arrayX[0];
		x2Novo = arrayX[1];
		
		// TRANSFERE X1 PARA O CENTRO
		/*if (x1Novo != xCentro) {
			
			if (x1Novo < xCentro) {
			
				x2Novo = x2Novo + (xCentro - x1Novo);
				
				x1Novo = xCentro;
			
			} else {
				
				x2Novo = x2Novo - (x1Novo - xCentro);
				
				x1Novo = xCentro;
			}
		}*/
		
		// PASSO 2 - ORDENA Y
		int[] arrayY = retornaCoordenadasAjustadasDaMenorParaAMaior(y1Novo, y2Novo, false);
		
		y1Novo = arrayY[0];
		y2Novo = arrayY[1];
		
		// TRANSFERE Y1 PARA O CENTRO
		/*if (y1Novo != yCentro) {
			
			if (y1Novo < yCentro) {
			
				y2Novo = y2Novo + (yCentro - y1Novo);
				
				y1Novo = yCentro;
			
			} else {
				
				y2Novo = y2Novo - (y1Novo - yCentro);
				
				y1Novo = yCentro;
			}
		}*/
		
		// PASSO 3 - CALCULA COEFICIENTE ANGULAR DA RETA
		double m = (double)(y1Novo - y2Novo) / (double)(x2Novo - x1Novo);
		
		// PASSO 4 - SE m MAIOR QUE 1 (2o OCTANTE)
		if (m > 1.0) {
			int x2Aux = x2Novo;
			x2Novo = x1Novo + (y1Novo - y2Novo);
			y2Novo = y1Novo - (x2Aux - x1Novo);
		}

		// PASSO 5 - DESLOCA EIXO DAS COORDENADAS
		int[] coordenadasAjustadasParaOCentro = deslocaEixoDasCoordenadasDeAcordoComPontoCentral(x1, y1, x2, y2, pontoCentral);
		
		x1Novo = coordenadasAjustadasParaOCentro[0];
		y1Novo = coordenadasAjustadasParaOCentro[1];
		x2Novo = coordenadasAjustadasParaOCentro[2];
		y2Novo = coordenadasAjustadasParaOCentro[3];
		
		// PASSO 7 - DESENHA A RETA COM BRESENHAN
		ArrayList<Integer[]> conjPontosDaReta = aplicaBresenhan(g, x1Novo, y1Novo, x2Novo, y2Novo, painel, pontoCentral);
		
		// PASSO 6 - CONVERTE Ys PARA HEMISFÉRIO SUL
		y1Novo = converteCoordenadaYParaHemisferioSulDoPainel(y1Novo, yCentro, painel);
		y2Novo = converteCoordenadaYParaHemisferioSulDoPainel(y2Novo, yCentro, painel);
		
		// PASSO 9 - REBATE RETA
		//ArrayList<Integer[]> conjPontosDaRetaNovo = rebateReta(g, conjPontosDaReta); 
	}
	
	private static ArrayList<Integer[]> aplicaBresenhan(Graphics g, int x1, int y1, int x2, int y2, JPanel painel, int[] pontoCentral) {
	
		ArrayList<Integer[]> conjPontosDaReta = new ArrayList<Integer[]>(0); 
		
		Integer[] ponto = null;
		
		int dx = Math.abs( x2 - x1 );
		int dy = Math.abs( y2 - y1 );
		int d = Math.abs( 2 * dy - dx ); /* Valor inicial de d */
		
		int incE = 2 * dy; /* Incremento de E */
		int incNE = 2 * dy - dx; /* Incremento de NE */
		int x = x1;
		int y = y1;
		
		int yCentro = pontoCentral[1];
		
		System.out.println("dx = " + dx);
		System.out.println("dy = " + dy);
		System.out.println("d = " + d);
		System.out.println("incE = " + incE);
		System.out.println("incNE = " + incNE);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		
		drawPixel(g, x, converteCoordenadaYParaHemisferioSulDoPainel(y, yCentro, painel));
		
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
			
			drawPixel(g, x, converteCoordenadaYParaHemisferioSulDoPainel(y, yCentro, painel));
			
			ponto = new Integer[2];
			ponto[0] = x;
			ponto[1] = y;
			
			conjPontosDaReta.add( ponto );
		} 
		
		return conjPontosDaReta;
	}
	
	private static ArrayList<Integer[]> rebateReta(Graphics g, ArrayList<Integer[]> conjPontosDaReta) {
		
		ArrayList<Integer[]> conjPontosDaRetaNovo = new ArrayList<Integer[]>(0);
		
		Integer[] ponto = null;
		
		for (int i = 0; i < conjPontosDaReta.size(); i++) {
			
			ponto = new Integer[2];
			ponto[0] = conjPontosDaReta.get(i)[1];
			ponto[1] = conjPontosDaReta.get(i)[0];
			
			drawPixel(g, ponto[0], ponto[1]);
			
			conjPontosDaRetaNovo.add(ponto);
		}
		
		return conjPontosDaRetaNovo;
	}
	
	private static int[] deslocaEixoDasCoordenadasDeAcordoComPontoCentral(int x1, int y1, int x2, int y2, int[] pontoCentral) {
		
		int x1Novo = x1;
		int x2Novo = x2;
		int y1Novo = y1;
		int y2Novo = y2;
		
		int xCentro = pontoCentral[0];
		int yCentro = pontoCentral[1];
		
		x1Novo += xCentro; 
		x2Novo += xCentro;
		
		y1Novo += yCentro;
		y2Novo += yCentro;
		
		int[] novaCoordenadas = {x1Novo, y1Novo, x2Novo, y2Novo};
		
		return novaCoordenadas;
	}
	
	private static int retornaOctanteDaReta(int x1, int y1, int x2, int y2, JPanel painel) {
		
		int octante = 1;

		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		
		/*
		 * Q1: x1 < x2 && y1 < y2
		 * Q2: x1 > x2 && y1 < y2
		 * Q3: x1 > x2 && y1 > y2
		 * Q4: x1 < x2 && y1 > y2
		 * */
		
		if (x1 > x2 && y1 < y2) {
			
			if (dx < dy) {
				
			}
			
		}
		
		return octante;
	}
	
	private static int converteCoordenadaYParaHemisferioSulDoPainel(int coord, int yCentro, JPanel painel) {

		int difParaCentro = Math.abs(coord - yCentro);
		
		if (coord == yCentro) {
			return coord;
		}
		
		int coordNovo = painel.getHeight() - coord;
		
		int difParaCentroNovo = Math.abs(coordNovo - yCentro);
		
		if (difParaCentroNovo == difParaCentro) {
			return coordNovo;
		}
		
		if (difParaCentroNovo > difParaCentro) {
			coordNovo += Math.abs(difParaCentro - difParaCentroNovo);
		} else {
			coordNovo -= Math.abs(difParaCentro - difParaCentroNovo);
		}
		
		return coordNovo;
	}
	
	private static int[] retornaCoordenadasAjustadasDaMenorParaAMaior(int coord1, int coord2, boolean inverso) {
	
		int aux;
		
		if (inverso) {
			
			if ( coord2 > coord1 ) {
			
				aux = coord2;
				coord2 = coord1;
				coord1 = aux;
			}
			
		} else if ( coord1 > coord2 ) {
			
			aux = coord1;
			coord1 = coord2;
			coord2 = aux;
		}
		
		int[] novoX = {coord1, coord2}; 
		
		return novoX;
	}
}
