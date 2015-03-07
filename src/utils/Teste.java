package utils;

import java.util.ArrayList;

public class Teste {

	public static void escreveMatriz( Integer [][] matriz, String nomeMatriz ) {
		System.out.println("Matriz " + nomeMatriz + ":");
		System.out.println();
		
		for (int i = 0; i < matriz.length; i++) {
			
			System.out.print("| ");
			
			for (int j = 0; j < matriz[i].length; j++) {

				if ( j == ( matriz[i].length - 1 ) ) {
					System.out.print( matriz[i][j] );
				} else {
					System.out.print( matriz[i][j] + " " );
				}
			}
			
			System.out.print( " |" );
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		ArrayList<Integer[][]> arrayMatriz1 = new ArrayList<Integer[][]>(0);
		ArrayList<Integer[][]> arrayMatriz2 = new ArrayList<Integer[][]>(0);
		
		Integer[][] matriz1_1 = { {2, 3}, {1, 2} };
		Integer[][] matriz1_2 = { {1, 1}, {1, 1} };
		
		arrayMatriz1.add( matriz1_1 );
		arrayMatriz2.add( matriz1_2 );
		
		
		Integer[][] matriz2_1 = { {2, 3}, {1, 2}, {10, 20} };
		Integer[][] matriz2_2 = { {1, 1}, {1, 1} };
		
		arrayMatriz1.add( matriz2_1 );
		arrayMatriz2.add( matriz2_2 );
		
		
		Integer[][] matriz3_1 = { {2, 3}, {1, 2}, {10, 20} };
		Integer[][] matriz3_2 = { {1, 1}, {1, 1}, {1, 1} };
		
		arrayMatriz1.add( matriz3_1 );
		arrayMatriz2.add( matriz3_2 );
		
		
		Integer[][] matriz4_1 = { {2, 3, 4}, {1, 2, 3} };
		Integer[][] matriz4_2 = { {1, 1}, {1, 1}, {1, 1} };
		
		arrayMatriz1.add( matriz4_1 );
		arrayMatriz2.add( matriz4_2 );
		
		
		Integer[][] matriz5_1 = { {2, 3, 4}, {1, 2, 3} };
		Integer[][] matriz5_2 = { {1, 1, 1}, {1, 1, 1}, {1, 1, 1} };
		
		arrayMatriz1.add( matriz5_1 );
		arrayMatriz2.add( matriz5_2 );
		
		for (int i = 0; i < arrayMatriz1.size(); i++) {
			System.out.println("##### CASO DE TESTE " + (i+1) + " #####");
			System.out.println();
			OperacaoMatriz.testaMultiplicacao(arrayMatriz1.get(i), arrayMatriz2.get(i));
		}
	}
}
