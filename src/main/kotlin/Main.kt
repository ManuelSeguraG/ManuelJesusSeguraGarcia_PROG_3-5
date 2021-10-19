class coche(
    var marcaCoche: String,
    var modeloCoche: String,
    var tanqueCoche: Int,
    var velocidadMaxima: Int
) {
    var gasolinaCoche: Int = 0
    var colorCoche: String = "Blanco"
    var velocidadIncremento: Int = 5
    var velocidadDecremento: Int = 5
    var velocidadMarcha: Int = 0
    var velocidadActual: Int = 0
    var cocheEncendido: Boolean = false

    init {
        require(marcaCoche.trim().length > 0) { "Especifique una marca." }
        require(velocidadMaxima >= 10 && velocidadMaxima <= 250) { "Velocidad minima de 10 y maxima de 250" }
    }

    fun especificacionesCoche(): String =
        "El coche es un $marcaCoche $modeloCoche color $colorCoche. Tiene un tanque de $tanqueCoche litros, actualmente con $gasolinaCoche litros de gasolina. alcanza una velocidad maxima de $velocidadMaxima"

    fun encenderCoche(): String {
        return if (cocheEncendido.equals(false) && velocidadActual == 0 && gasolinaCoche > 0) {
            cocheEncendido = true
            "Coche encendido"
        } else {
            "Algo fallo, no se ha encendido el coche"
        }
    }

//    fun apagarCoche(): String {
//        return if (cocheEncendido.equals(true) && velocidadMarcha < 0 && velocidadMarcha == 0) {
//            cocheEncendido = true
//            "Coche apagado"
//        } else {
//            "Algo fallo, no se ha apagado el coche"
//        }
//    }

    fun iniciarCoche(): String {
        return if (cocheEncendido.equals(true)) {
            velocidadMarcha = 1
            acelerarCoche()
        } else {
            "Algo fallo, no se ha apagado el coche"
        }
    }

    fun acelerarCoche(): String {
        return if (velocidadActual == velocidadMaxima) {
            gasolinaCoche -= 6 - velocidadMarcha
            "Velocidad maxima de $velocidadMaxima (marcha $velocidadMarcha y $gasolinaCoche l. de gasolina restantes)"
        } else return if ((gasolinaCoche - (6 - velocidadMarcha)) > 0 && velocidadMarcha > 0) {
            gasolinaCoche -= 6 - velocidadMarcha
            velocidadActual += 5
            "Velocidad actual $velocidadActual (marcha $velocidadMarcha y $gasolinaCoche l. de gasolina restantes)"
        } else {
            "No se ha podido acelerar"
        }
    }
}

fun main(args: Array<String>) {
    var cocheTest: coche = coche(
        "Automanu",
        "Custom",
        65,
        50
    )

    cocheTest.colorCoche = "Gris"

    println(cocheTest.especificacionesCoche())
    println("")
    println(cocheTest.encenderCoche())
    cocheTest.velocidadMarcha = 1
    cocheTest.gasolinaCoche = cocheTest.tanqueCoche
    println(cocheTest.encenderCoche())
    println("")
    println(cocheTest.iniciarCoche())
    repeat(50) {
        println(cocheTest.acelerarCoche())
    }

}