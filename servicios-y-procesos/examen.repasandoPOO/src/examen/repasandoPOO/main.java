package examen.repasandoPOO;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			persona juan = new persona("Juan");
			persona ana = new persona("Ana");
			
			coche coche1 = new coche("Toyota","Corolla", 2020 , juan,4);
			coche coche2 = new coche("Honda","Civic",2021, ana , 4);
			coche coche3 = new coche("Ford", "Focus", 2022, null , 4);
			
			camion camion1 = new camion("Mercedes", "Actros", 2019, juan, 18);
			
			coche1.setPropietario(juan);
			camion1.setPropietario(juan);
			coche2.setPropietario(ana);
						
			System.out.println("Total de vehículos creados: " + vehiculo.verContador());
			System.out.println("Coche 1:" + coche1.info());
			System.out.println("Coche 2:" + coche2.info());
			System.out.println("Coche 3:" + coche3.info());
			System.out.println("Camion 1:" + camion1.info());




	}



}
