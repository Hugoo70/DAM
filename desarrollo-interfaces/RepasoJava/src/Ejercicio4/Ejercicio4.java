package Ejercicio4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ejercicio4 {
	static Scanner teclado = new Scanner(System.in);
	static Random r = new Random();
	
	static int numRandom() {
		
		return r.nextInt(0,10);
		
	}

	static void imprimir(int[][] matriz) {
		for(int i = 0; i<matriz.length; i++)
		
		System.out.println(Arrays.toString(matriz[i]));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Dime el tamaño de la matriz:");
		int num = teclado.nextInt();
		int suma = 0;
		
		int[][] matriz = new int[num][num];
		
		
		for (int i = 0; i<matriz.length; i++) 
			for(int j =0; j<matriz[i].length; j++) {
				matriz[i][j]=numRandom();
				suma += matriz[i][j];
			}
			imprimir(matriz);
			
			System.out.println("La suma es: " + suma);
			
			
	}

}
