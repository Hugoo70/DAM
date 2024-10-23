package Ejercicio6;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Administracion Luis = new Administracion("01A", "Luis", "Sanchez", 2500, "SMR", 23);
		Administracion Javier = new Administracion("02A", "Javier", "Gonzalez", 2400, "SMR", 20);

		Profesor SergioS = new Profesor("11P", "Sergio", "Sanchez", 2300, 2, true);
		Profesor Victor = new Profesor("12P", "Victor", "De Juan", 2200, 1, true);

		Directivo Jose = new Directivo("21D", "Jose", "Luis", 2100, true, 'M');
		Directivo Mario = new Directivo("22D", "Mario", "Soler", 2100, false, 'T');

		System.out.println(Luis.toString());
		System.out.println(Javier.toString());
		System.out.println();

		System.out.println(SergioS.toString());
		System.out.println(Victor.toString());
		System.out.println();

		System.out.println(Jose.toString());
		System.out.println(Mario.toString());
		System.out.println();

		Modulo DdI = new Modulo("Desarrollo de Interfaces", 6, SergioS, true);
		Modulo PMYDM = new Modulo("Programación Muiltimedia", 3, Victor, false);
		Modulo GE = new Modulo("Gestión Empresarial", 4, SergioS, true);

		Alumno Hugo = new Alumno("31H", "Hugo", "Jiménez", "09/26/2005", 'H', false);
		Alumno Sergio = new Alumno("32S", "Sergio", "Mostacero", "03/30/2003", 'H', false);

		Hugo.modulos.add(GE);
		Hugo.modulos.add(PMYDM);
		Hugo.modulos.add(DdI);
		
		Sergio.modulos.add(PMYDM);
		Sergio.modulos.add(GE);
		
		System.out.println(Hugo.toString());
		System.out.println(Sergio.toString());
	}

}