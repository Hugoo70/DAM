package PracticaFinal;

public class Cliente {
    private String numeroCliente;
    private String nombre;
    private double DineroGastado;
    
    
	public Cliente(String numeroCliente, String nombre, double dineroGastado) {
		//super();
		this.numeroCliente = numeroCliente;
		this.nombre = nombre;
		DineroGastado = dineroGastado;
	}


	public String getNumeroCliente() {
		return numeroCliente;
	}


	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public double getDineroGastado() {
		return DineroGastado;
	}


	public void setDineroGastado(double d) {
		DineroGastado = d;
	}


	@Override
	public String toString() {
		return "Cliente [numeroCliente=" + numeroCliente + ", nombre=" + nombre + ", DineroGastado=" + DineroGastado
				+ "]";
	}


    
    
}
