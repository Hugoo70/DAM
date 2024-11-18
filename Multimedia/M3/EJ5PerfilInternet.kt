fun main() {
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    amanda.showProfile()
    atiqah.showProfile()
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
       print("Name: $name\nAge: $age\n")
      
       if(hobby!=null){
           print("Likes to $hobby.")
       }
       if(referrer!=null){
           println(" Has a refferer named ${referrer.name}, who likes to play ${referrer.hobby}\n")
       }else{
           println("doesn't have a refferer\n")
       }
       
       
    }
}