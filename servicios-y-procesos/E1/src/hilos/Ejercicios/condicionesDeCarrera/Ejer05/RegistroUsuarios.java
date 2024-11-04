package hilos.Ejercicios.condicionesDeCarrera.Ejer05;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

class Registro implements Runnable {
    private RegistroUsuarios registro;
    private String nombreUsuario;

    public Registro(RegistroUsuarios registro, String nombreUsuario) {
        this.registro = registro;
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void run() {
        registro.registrarUsuario(nombreUsuario);
    }
}



public class RegistroUsuarios {
    private Set<String> usuarios = new ConcurrentSkipListSet<>();

    public void registrarUsuario(String nombreUsuario) {
    	boolean agregado = usuarios.add(nombreUsuario);
        if (agregado) {
            System.out.println("Usuario registrado: " + nombreUsuario);
        } else {
            System.out.println("El usuario " + nombreUsuario + " ya existe.");
        }
    }
    

    public static void main(String[] args) {
        RegistroUsuarios registro = new RegistroUsuarios();

        Thread hilo1 = new Thread(new Registro(registro, "usuario1"));
        Thread hilo2 = new Thread(new Registro(registro, "usuario1"));


        hilo1.start();
        hilo2.start();


        try {
        	// Se ordenan para que el padre las ejecute en este orden, empiza una y espera a que termine para lanzar la otra.
        	// WIFEXITED
            hilo1.join();
            hilo2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}