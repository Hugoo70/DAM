package factorial;

import java.util.Scanner;

public class factorial {
	public static int Factorial(int num) {
		int resultado = 0;

		if (num == 0) {
			System.out.println("Fin");
		} else {
			System.out.println(num);
			resultado = num * Factorial(num - 1);

		}
		return resultado;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el número");
		int numero = teclado.nextInt();
		System.out.println("El resultado es : " + Factorial(numero));
	}

}