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

    fun apagarCoche(): String {
        return if (cocheEncendido.equals(true)  && velocidadActual == 0) {
            velocidadMarcha = 0
            cocheEncendido = false
            "Coche apagado"
        } else {
            "Algo fallo, no se ha apagado el coche"
        }
    }

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
            "Velocidad maxima de $velocidadMaxima (marcha $velocidadMarcha y $gasolinaCoche l. de gasolina restantes)"
        } else return if ((gasolinaCoche - (6 - velocidadMarcha)) > 0 && velocidadMarcha > 0) {
            gasolinaCoche -= 6 - velocidadMarcha
            velocidadActual += 5
            "Velocidad actual $velocidadActual (marcha $velocidadMarcha y $gasolinaCoche l. de gasolina restantes)"
        } else {
            "No se ha podido acelerar"
        }
    }

    fun cambiarMarcha(marcha: Int): String {
        return if (marcha in 1..5) {
            velocidadMarcha = marcha
            "Marcha actual: $velocidadMarcha"
        } else {
            "No se ha podido cambiar la marcha (rango entre 1 y 5)"
        }

    }

    fun frenarCoche(): String {
        return if (velocidadActual == 0) {
            "No se puede frenar, ya estamos quietos"
        } else {
            velocidadActual -= 5
            "Velocidad actual $velocidadActual (marcha $velocidadMarcha y $gasolinaCoche l. de gasolina restantes)\""
        }
    }

    fun rellenarTanqueCoche(cantidad: Int): String {
        return if (cantidad > tanqueCoche) {
            gasolinaCoche = tanqueCoche
            "Tanque rellenado al maximo"
        } else return if (cantidad == 0) {
            "No se puede rellenar el tanque solo con aire"
        } else {
            gasolinaCoche = cantidad
            "Tanque rellenado con $cantidad litros de gasolina"
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
    println(cocheTest.rellenarTanqueCoche(60))
    println(cocheTest.encenderCoche())
    println("")
    println(cocheTest.iniciarCoche())
    repeat(10) {
        println(cocheTest.acelerarCoche())
    }
    cocheTest.cambiarMarcha(5)
    repeat(10) {
        println(cocheTest.acelerarCoche())
    }
    repeat(20) {
        println(cocheTest.frenarCoche())
    }
    println(cocheTest.apagarCoche())

}