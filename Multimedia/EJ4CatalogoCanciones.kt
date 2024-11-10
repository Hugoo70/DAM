fun main() {
    val s1 = Song("cantandote al oido", "Kyr4", 2022, 1500)
    val s2 = Song("Cancion", "a", 2019, 898)
    
    s1.printDescription()
    s2.printDescription()
    
    println("¿Es popular? ${s1.isPopular}") 
    println("¿Es popular? ${s2.isPopular}")
    
}

class Song(
    val titulo: String, 
    val artista: String, 
    val AnoLanzamiento: Int, 
    val Reproducciones: Int
){

        val isPopular: Boolean
        get() = Reproducciones >= 1000
    
        fun printDescription() {
        println("$titulo, cantada por $artista, se lanzó en $AnoLanzamiento")
    }

}
