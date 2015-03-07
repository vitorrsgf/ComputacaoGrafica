package utils;

import javax.swing.JPanel;

public class Utils {

	public static int[] deslocaEixoDasCoordenadasDeAcordoComPontoCentral(int x1, int y1, int x2, int y2, int[] pontoCentral) {
		
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
	
	public static int inverteCoordenadaY(int coord, int yCentro, JPanel painel) {

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
	
	public static int[] ordenaCoordenadas(int coord1, int coord2, boolean inverso) {
		
		int aux;
		
		if ( coord1 > coord2 ) {
			
			aux = coord1;
			coord1 = coord2;
			coord2 = aux;
		}
		
		int[] novoX = {coord1, coord2}; 
		
		return novoX;
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
}
