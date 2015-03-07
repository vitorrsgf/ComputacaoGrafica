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
	
	public static void drawLine(Graphics g, int x1, int y1, int x2, int y2) {

		double m = (double)( y2 - y1 ) / (double)( x2 - x1 );
		
		double b = y1 - (double)( m * x1 );
		
		for (int i = x1; i <= x2; i++) {
			Primitiva.drawPixel(g, i, (int)(m * i + b));
		}
	}

	public static void drawLineBresenhan(Graphics g, int x1, int y1, int x2, int y2, int[] pontoCentral, JPanel painel) {
		
		int xCentro = pontoCentral[0];
		int yCentro = pontoCentral[1];
		
		int x1Novo = x1; //coordenadasAjustadasParaOCentro[0];
		int y1Novo = y1; //coordenadasAjustadasParaOCentro[1];
		int x2Novo = x2; //coordenadasAjustadasParaOCentro[2];
		int y2Novo = y2; //coordenadasAjustadasParaOCentro[3];
		
		// PASSO 1 - ORDENA X
		int[] arrayX = ordenaCoordenadas(x1Novo, x2Novo, false);
		
		x1Novo = arrayX[0];
		x2Novo = arrayX[1];
		
		// PASSO 2 - ORDENA Y
		int[] arrayY = ordenaCoordenadas(y1Novo, y2Novo, false);
		
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
		y1Novo = inverteCoordenadaY(y1Novo, yCentro, painel);
		y2Novo = inverteCoordenadaY(y2Novo, yCentro, painel);
		
		// PASSO 9 - REBATE RETA
		//ArrayList<Integer[]> conjPontosDaRetaNovo = rebateReta(g, conjPontosDaReta); 
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
	
}
