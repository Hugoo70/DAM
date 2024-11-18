package PracticaFinal;

import java.io.Serializable;

public class Gasolinera implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String ubicacion;
    private int litros95;
    private int litrosDiesel;
    private double precio95;
    private double precioDiesel;

    public Gasolinera(String nombre, String ubicacion, int litros95, int litrosDiesel, double precio95, double precioDiesel) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.litros95 = litros95;
        this.litrosDiesel = litrosDiesel;
        this.precio95 = precio95;
        this.precioDiesel = precioDiesel;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getLitros95() {
        return litros95;
    }

    public int getLitrosDiesel() {
        return litrosDiesel;
    }

    public double getPrecio95() {
        return precio95;
    }

    public double getPrecioDiesel() {
        return precioDiesel;
    }

    public boolean repostar95(int litros) {
        if (litros <= litros95) {
            litros95 -= litros;
            return true;
        }
        return false;
    }

    public boolean repostarDiesel(int litros) {
        if (litros <= litrosDiesel) {
            litrosDiesel -= litros;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Gasolinera{" +
                "nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", precio95=" + precio95 +
                ", precioDiesel=" + precioDiesel +
                '}';
    }
}

