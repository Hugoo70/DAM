package PracticaFinalHJS;

import java.io.Serializable;

public class Gasolinera implements Serializable {
    private String nombre;
    private String ubicacion;
    private double gasolina95;
    private double diesel;
    private double precioGasolina95;
    private double precioDiesel;

    public Gasolinera(String nombre, String ubicacion, double gasolina95, double diesel, double precioGasolina95, double precioDiesel) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.gasolina95 = gasolina95;
        this.diesel = diesel;
        this.precioGasolina95 = precioGasolina95;
        this.precioDiesel = precioDiesel;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public double getGasolina95() {
		return gasolina95;
	}

	public void setGasolina95(double gasolina95) {
		this.gasolina95 = gasolina95;
	}

	public double getDiesel() {
		return diesel;
	}

	public void setDiesel(double diesel) {
		this.diesel = diesel;
	}

	public double getPrecioGasolina95() {
		return precioGasolina95;
	}

	public void setPrecioGasolina95(double precioGasolina95) {
		this.precioGasolina95 = precioGasolina95;
	}

	public double getPrecioDiesel() {
		return precioDiesel;
	}

	public void setPrecioDiesel(double precioDiesel) {
		this.precioDiesel = precioDiesel;
	}

    
}
