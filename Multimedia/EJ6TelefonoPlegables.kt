open class Phone(var isScreenLightOn: Boolean = false){
    open fun switchOn() {
        isScreenLightOn = true
    }

    fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class Plegar(var plegado: Boolean = true): Phone() {
    override fun switchOn() {
        if (!plegado) {
            isScreenLightOn = true
        }
    }

    fun plegar() {
        plegado = true
    }

    fun noPlegar() {
        plegado = false
    }
}

fun main() {
    val phone = Plegar()

    phone.switchOn()
    phone.checkPhoneScreenLight()
    phone.noPlegar()
    phone.switchOn()
    phone.checkPhoneScreenLight()
}