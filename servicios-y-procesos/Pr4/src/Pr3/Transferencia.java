package Pr3;

public class Transferencia {
	private String origen;
	private String destino;
	private double monto;
	
	public Transferencia(String origen, String destino, double monto) {
		//super();
		this.origen = origen;
		this.destino = destino;
		this.monto = monto;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

    @Override
    public String toString() {
        return "{" +
                "origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", monto=" + monto +
                '}';
    }
	
}


