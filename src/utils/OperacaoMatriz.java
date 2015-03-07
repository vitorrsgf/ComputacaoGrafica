package utils;

public class OperacaoMatriz {

	public static void escreveMatrizNoConsole( Integer [][] matriz, String nomeMatriz ) {
		
		System.out.println("Matriz \"" + nomeMatriz + "\":");
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
	
	public static void testaMultiplicacao(Integer[][] matriz1, Integer[][] matriz2) {
		
		System.out.println("- Ordem da Matriz 1: " + matriz1.length + " x " + matriz1[0].length);
		System.out.println("- Ordem da Matriz 2: " + matriz2.length + " x " + matriz2[0].length);
		System.out.println("- Ordem da Matriz Resultante: " + matriz1.length + " x " + matriz2[0].length);
		System.out.println();
		
		Integer[][] matrizResultante = OperacaoMatriz.multiplicaMatrizes(matriz1, matriz2);
		
		OperacaoMatriz.escreveMatrizNoConsole(matriz1, "Matriz 1");
		OperacaoMatriz.escreveMatrizNoConsole(matriz2, "Matriz 2");
		OperacaoMatriz.escreveMatrizNoConsole(matrizResultante, "Matriz Resultante");
	}
	
	public static Integer[][] multiplicaMatrizes(Integer[][] matriz1, Integer[][] matriz2) {
		
		if (matriz1[0].length != matriz2.length) {
			System.out.println("!!!!!!!!!!! ERRO !!!!!!!!!!!!");
			System.out.println("Número de colunas da Matriz 1 é diferente do número de linhas da Matriz 2");
			Integer[][] matrizResultante = new Integer[matriz1.length][matriz2[0].length];
			return matrizResultante;
		}
		
		Integer[][] matrizResultante = new Integer[matriz1.length][matriz2[0].length];
		
		int elemento = 0; 
		
		for (int i = 0; i < matriz1.length; i++) {
			
			for (int colunaDaSegundaMatriz = 0; colunaDaSegundaMatriz < matriz2[0].length; colunaDaSegundaMatriz++) {
			
				for (int j = 0; j < matriz1[i].length; j++) {
					
					elemento += matriz1[i][j] * matriz2[j][colunaDaSegundaMatriz];
				}
				
				matrizResultante[i][colunaDaSegundaMatriz] = elemento;
				
				elemento = 0;
			}
		}
		
		return matrizResultante;
	}
}
