fun main() {
    // Celsius a Fahrenheit
    printFinalTemperature(18.0, "Celsius", "Fahrenheit") { celsius -> (celsius * 9 / 5) + 32 }
    
    // Kelvin a Celsius
    printFinalTemperature(310.0, "Kelvin", "Celsius") { kelvin -> kelvin - 273.15 }
    
    // Fahrenheit a Kelvin
    printFinalTemperature(75.0, "Fahrenheit", "Kelvin") { fahrenheit -> (fahrenheit - 32) * 5 / 9 + 273.15 }
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}
