package Ejercicio3;

public class Ejercicio3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] array = {1,2,3,4,5,6,7,8};
		int cont = 0;
		
		for (int i : array) {
			if(i % 2 == 0) {
				cont++;
			}
		}
		System.out.println("Hay " + cont + " números pares");
	}

}
