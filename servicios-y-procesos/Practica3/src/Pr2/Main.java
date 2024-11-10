package Pr2;

public class Main {
    public static void main(String[] args) {
        // Creación y ejecución de 4 instancias de RobotExplorador en paralelo
        Thread explorador1 = new Thread(new RobotExplorador("Explorador1", 2));
        Thread explorador2 = new Thread(new RobotExplorador("Explorador2", 3));
        Thread explorador3 = new Thread(new RobotExplorador("Explorador3", 4));
        Thread explorador4 = new Thread(new RobotExplorador("Explorador4", 5));

        explorador1.start();
        explorador2.start();
        explorador3.start();
        explorador4.start();

        // Creación de 3 RobotConstructor y ejecución en paralelo
        Thread constructor1 = new Thread(new RobotConstructor("Constructor1", 1));
        Thread constructor2 = new Thread(new RobotConstructor("Constructor2", 2));
        Thread constructor3 = new Thread(new RobotConstructor("Constructor3", 3));

        constructor1.start();
        constructor2.start();
        constructor3.start();

        // Esperar a que los constructores terminen
        try {
            constructor1.join();
            constructor2.join();
            constructor3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verificación del total de estructuras construidas
        System.out.println("Total de estructuras construidas al final: " + RobotConstructor.getEstructurasConstruidas());
    }
}

