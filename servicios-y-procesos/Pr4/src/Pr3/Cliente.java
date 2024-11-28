package Pr3;

public class Cliente {
	private String id;
	private String nombre;
	private double saldo;
	private String numeroCuenta;
	private String direccion;
	
	public Cliente(String id, String nombre, double saldo, String numeroCuenta, String direccion) {
		//super();
		this.id = id;
		this.nombre = nombre;
		this.saldo = saldo;
		this.numeroCuenta = numeroCuenta;
		this.direccion = direccion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
    public void ingresarSaldo(double monto) {
        this.saldo += monto;
    }

    public void retirarSaldo(double monto) {
        this.saldo -= monto;
    }

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", saldo=" + saldo + ", numeroCuenta=" + numeroCuenta
				+ ", direccion=" + direccion + "]";
	}
	
	
}
