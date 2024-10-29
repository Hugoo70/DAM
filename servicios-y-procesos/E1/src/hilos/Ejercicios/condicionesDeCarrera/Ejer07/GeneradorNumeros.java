package hilos.Ejercicios.condicionesDeCarrera.Ejer07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;


class Generador implements Runnable {
    private GeneradorNumeros generador;

    public Generador(GeneradorNumeros generador) {
        this.generador = generador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            generador.generarNumero();
        }
    }
}


public class GeneradorNumeros {
    private Set<Integer> numeros = new ConcurrentSkipListSet<Integer>();
    private Random random = new Random();

    public synchronized void generarNumero() {
        int numero = random.nextInt(100);
        boolean agregado = numeros.add(null);
        
        if (!numeros.contains(numero)) {
            numeros.add(numero);
            System.out.println("Número generado y agregado: " + numero);
        } else {
            System.out.println("Número duplicado: " + numero);
        }
    }
    
    public static void main(String[] args) {
        GeneradorNumeros generadorNumeros = new GeneradorNumeros();

        Thread hilo1 = new Thread(new Generador(generadorNumeros));
        Thread hilo2 = new Thread(new Generador(generadorNumeros));

        hilo1.start();
        hilo2.start();
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		
    }
}
}