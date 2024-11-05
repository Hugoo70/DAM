open class Vehiculo(val marca: String, val modelo: String, val año: Int, val propietario: Persona?) {
    companion object {
        var contadorVehiculos = 0 
    }
    
    init {
        contadorVehiculos++ 
    }
}

class Coche(marca: String, modelo: String, año: Int, propietario: Persona?, val numeroPuertas: Int) :
    Vehiculo(marca, modelo, año, propietario)

class Camion(marca: String, modelo: String, año: Int, propietario: Persona?, val capacidadCarga: Int) :
    Vehiculo(marca, modelo, año, propietario)

class Persona(val nombre: String)

fun main() {
    val persona1 = Persona("Juan")
    val persona2 = Persona("Ana")
    
    val vehiculos = listOf(
        Coche("Toyota", "Corolla", 2020, persona1, 4),
        Coche("Honda", "Civic", 2021, persona2, 4),
        Coche("Ford", "Focus", 2022, null, 4),
        Camion("Mercedes", "Actros", 2019, persona1, 18)
    )

    println("Total de vehículos creados: ${Vehiculo.contadorVehiculos}")
    
    var cocheNumero = 1
    vehiculos.forEach { vehiculo ->
        if (vehiculo is Coche) {
            val propietarioInfo = vehiculo.propietario?.nombre ?: "no tiene propietario"
            println("Coche $cocheNumero propietario: $propietarioInfo")
            cocheNumero++
        }
    }
}

