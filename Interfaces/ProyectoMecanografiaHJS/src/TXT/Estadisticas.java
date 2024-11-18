package TXT;

public class Estadisticas {
	private String user;
	private int dif;
	private int tiempoTranscurrido;
	private int ppm;
	private int errores;
	
	
	public Estadisticas(String user, int dif, int tiempoTranscurrido, int ppm, int errores) {
		//super();
		this.user = user;
		this.dif = dif;
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.ppm = ppm;
		this.errores = errores;
	}
	

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public int getDif() {
		return dif;
	}


	public void setDif(int dif) {
		this.dif = dif;
	}


	public int getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}


	public void setTiempoTranscurrido(int tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}


	public int getPpm() {
		return ppm;
	}


	public void setPpm(int ppm) {
		this.ppm = ppm;
	}


	public int getErrores() {
		return errores;
	}


	public void setErrores(int errores) {
		this.errores = errores;
	}


	@Override
	public String toString() {
		String D = null;
		if(dif==0) {
			 D = "Nivel Fácil";
		}else if(dif==1) {
			 D = "Nivel Dificil";
		}
		return "Estadisticas [Dificultad=" + D + ", Tiempo transcurrido=" + tiempoTranscurrido + ", PPM=" + ppm + ", Errores="
				+ errores + "]";
	}
	
	
}
