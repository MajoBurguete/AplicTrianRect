package com.example.aplictrianrect.models

import com.example.aplictrianrect.R
import kotlin.math.*
import kotlin.random.Random

class Pregunta(val textoPregunta: String, val respuesta: Double, val unidades: String, val nombreImagen: Int) {


    fun redondeaA2Decimales(numero: Double) : Double{
        val porCien = numero * 100
        val entero = porCien.toInt()
        val numeroFinal = entero.toDouble() / 100.0

        return numeroFinal
    }

    fun redondeaA1Decimales(numero: Double) : Double{
        val porCien = numero * 10
        val entero = porCien.toInt()
        val numeroFinal = entero.toDouble() / 10.0

        return numeroFinal
    }

    public fun generaPreguntaRandom() : Pregunta{
        var preguntaActual : Pregunta

        when (Random.nextInt(1, 27)){
            1 -> preguntaActual = preguntaTipo1A()
            2 -> preguntaActual = preguntaTipo1C()
            3 -> preguntaActual = preguntaTipo1E()
            4 -> preguntaActual = preguntaTipo2A()
            5 -> preguntaActual = preguntaTipo2C()
            6 -> preguntaActual = preguntaTipo2E()
            7 -> preguntaActual = preguntaTipo3B()
            8 -> preguntaActual = preguntaTipo3D()
            9 -> preguntaActual = preguntaTipo3E()
            10 -> preguntaActual = preguntaTipo4B()
            11 -> preguntaActual = preguntaTipo4C()
            12 -> preguntaActual = preguntaTipo4D()
            13 -> preguntaActual = preguntaTipo5B()
            14 -> preguntaActual = preguntaTipo5D()
            15 -> preguntaActual = preguntaTipo5E()
            16 -> preguntaActual = preguntaTipo6A()
            17 -> preguntaActual = preguntaTipo6D()
            18 -> preguntaActual = preguntaTipo6E()
            19 -> preguntaActual = preguntaTipo7A()
            20 -> preguntaActual = preguntaTipo7B()
            21 -> preguntaActual = preguntaTipo7E()
            22 -> preguntaActual = preguntaTipo8A()
            23 -> preguntaActual = preguntaTipo8B()
            24 -> preguntaActual = preguntaTipo8E()
            25 -> preguntaActual = preguntaTipo9B()
            else -> {
                preguntaActual = preguntaTipo9B()
            }
        }

        return preguntaActual

    }

    // MARK: - Ejercicios de persona

    fun preguntaTipo1A() : Pregunta {
        var angulo : Int
        var largoSombra = Random.nextDouble(2.4, 3.5)
        largoSombra = redondeaA2Decimales(largoSombra)

        if(largoSombra <= 3){
            angulo = Random.nextInt(31, 35)
        }
        else{
            angulo = Random.nextInt(25, 30)
        }

        val texto = "Encuentra la altura de una persona si su sombra mide " + largoSombra.toString() + " m cuando el ??ngulo de elevaci??n del sol es " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble()/180.0
        val altura = largoSombra * tan(radianes)

        return Pregunta(texto, altura, "m", R.drawable.persona)
    }

    fun preguntaTipo1C() : Pregunta {

        var altura = Random.nextDouble(1.4, 2.1)
        altura = redondeaA2Decimales(altura)

        val angulo = Random.nextInt(25, 70)

        val texto = "Encuentra la longitud de la sombra que produce una persona que mide " + altura.toString() + " m cuando el ??ngulo de elevaci??n del sol es " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble()/180.0
        val largoSombra = altura * tan(radianes)

        return Pregunta(texto, largoSombra, "m", R.drawable.persona)
    }

    fun preguntaTipo1E() : Pregunta {

        var altura = Random.nextDouble(1.4, 2.1)
        altura = redondeaA2Decimales(altura)

        var largoSombra = Random.nextDouble(2.4, 3.4)
        largoSombra = redondeaA2Decimales(largoSombra)

        val texto = "Encuentra el ??ngulo de elevaci??n del sol cuando una persona que mide " + altura.toString() + " m muestra una sombra de  " + largoSombra.toString() + " m.  Escribe tu respuesta con 2 decimales."

        val radianes = atan(altura/largoSombra)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.persona)
    }

    // MARK: - Ejercicios de edificio

    fun preguntaTipo2A() : Pregunta {

        var distancia = Random.nextDouble(25.0, 100.0)
        distancia = redondeaA1Decimales(distancia)

        var angulo = Random.nextInt(25, 60)

        val texto = "Un turista se encuentra a " + distancia.toString() + " m de la base de un edificio. El turista observa un ??ngulo de elevaci??n del sol de " + angulo.toString() + "?? desde el punto en el que est?? parado hasta la parte m??s alta del edificio. Calcula la altura del edificio. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble()/180.0
        val altura = distancia * tan(radianes)

        return Pregunta(texto, altura, "m", R.drawable.edificio)
    }

    fun preguntaTipo2C() : Pregunta {

        var altura = Random.nextDouble(9.0, 70.0)
        altura = redondeaA1Decimales(altura)

        var angulo = Random.nextInt(25, 60)

        val texto = "Encuentra la longitud de la sombra que proyecta un edificio de " + altura.toString() + " m de altura cuando en ese momento el sol est?? a un ??ngulo de elevaci??n de " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble()/180.0
        val largoSombra = altura / tan(radianes)

        return Pregunta(texto, largoSombra, "m", R.drawable.edificio)
    }

    fun preguntaTipo2E() : Pregunta {

        var altura = Random.nextDouble(30.0, 200.0)
        altura = redondeaA1Decimales(altura)

        var largoSombra = Random.nextDouble(60.0, 300.0)
        largoSombra = redondeaA1Decimales(largoSombra)

        val texto = "Encuentra el ??ngulo de elevaci??n del sol cuando un edificio de " + altura.toString() + " ft de altura proyecta una sombra de " + largoSombra.toString() + " ft. Escribe tu respuesta con 2 decimales."

        val radianes = atan(altura/largoSombra)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.edificio)
    }

    // MARK: - Ejercicios de avi??n

    fun preguntaTipo3B() : Pregunta {

        var distancia = Random.nextInt(1000, 20000)

        var angulo = Random.nextInt(30, 80)

        val texto = "??A qu?? altura se encuentra un avi??n si est?? a una distancia de " + distancia.toString() + " m del aeropuerto y se dirige al mismo con un ??ngulo de depresi??n de " + angulo.toString() + "??? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = distancia.toDouble() / sin(radianes)

        return Pregunta(texto, altura, "m", R.drawable.avion)
    }

    fun preguntaTipo3D() : Pregunta {

        val altura = Random.nextInt(3000, 60000)

        var angulo = Random.nextInt(30, 80)

        val texto = "Un avi??n vuela a una altura de " + altura.toString() + " ft. Si est?? a un ??ngulo de elevaci??n de " + angulo.toString() + "?? del aeropuerto A, calcula la distancia ente el avi??n y el aeropuerto. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val hipotenusa = altura.toDouble() / sin(radianes)

        return Pregunta(texto, hipotenusa, "ft", R.drawable.avion_v2)
    }

    fun preguntaTipo3E() : Pregunta {

        val altura = Random.nextInt(1000, 7000)

        var hipotenusa = Random.nextInt(7500, 40000)

        val texto = "??Cu??l es el ??ngulo de elevaci??n de un avi??n si se encuentra a una altura de " + altura.toString() + " m y a una distancia del aeropuerto de " + hipotenusa.toString() + " m? Escribe tu respuesta con 2 decimales."

        val radianes = asin(altura.toDouble() / hipotenusa.toDouble())
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.avion_v2)
    }

    // MARK: - Ejercicios de escalera

    fun preguntaTipo4B() : Pregunta {

        var longitud = Random.nextDouble(3.0, 15.0)
        longitud = redondeaA2Decimales(longitud)

        val angulo = Random.nextInt(30, 50)

        val texto = "Una escalera de " + longitud.toString() + " m est?? apoyada formando un ??ngulo entre la escalera y la pared de " + angulo.toString() + "??, determina la altura a la cual est?? apoyada la escalera. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = longitud.toDouble() * cos(radianes)

        return Pregunta(texto, altura, "m", R.drawable.escalera_v2)
    }

    fun preguntaTipo4C() : Pregunta {

        var altura = Random.nextInt(15, 150)

        val angulo = Random.nextInt(20, 40)

        val texto = "Una escalera est?? apoyada sobre un edificio a una altura de " + altura.toString() + " ft. Si el ??ngulo que se forma entre la escalera y el piso es de " + angulo.toString() + "??, determina la distancia entre la base de la escalera y el edificio. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val distancia = altura.toDouble() / tan(radianes)

        return Pregunta(texto, distancia, "ft", R.drawable.escalera)
    }

    fun preguntaTipo4D() : Pregunta {

        var altura = Random.nextInt(18, 45)

        val angulo = Random.nextInt(30, 50)

        val texto = "Una escalera est?? apoyada sobre un edificio a una altura de " + altura.toString() + " ft. Si el ??ngulo que se forma entre la escalera y el piso es de " + angulo.toString() + "??, encuentra la longitud de la escalera. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val hipotenusa = altura.toDouble() / sin(radianes)

        return Pregunta(texto, hipotenusa, "ft", R.drawable.escalera)
    }

    // MARK: - Ejercicios de Rampa

    fun preguntaTipo5B() : Pregunta {

        var longitud = Random.nextDouble(2.0, 20.0)
        longitud = redondeaA2Decimales(longitud)

        val angulo = Random.nextInt(5, 20)

        val texto = "??A qu?? altura de la pared llegar?? una rampa de " + longitud.toString() + " m y que est?? formando un ??ngulo de " + angulo.toString() + "?? con el piso? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = longitud * sin(radianes)

        return Pregunta(texto, altura, "m", R.drawable.rampa)
    }

    fun preguntaTipo5D() : Pregunta {

        var altura = Random.nextDouble(20.0, 40.0)
        altura = redondeaA2Decimales(altura)

        val angulo = Random.nextInt(20, 40)

        val texto = "??Cu??l debe ser la longitud de una rampa si debe alcanzar una altura de " + altura.toString() + " ft y el ??ngulo de elevaci??n es " + angulo.toString() + "??? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val hipotenusa = altura / sin(radianes)

        return Pregunta(texto, hipotenusa, "ft", R.drawable.rampa)
    }

    fun preguntaTipo5E() : Pregunta {

        var altura = Random.nextDouble(3.0, 15.0)
        altura = redondeaA2Decimales(altura)

        var hipotenusa = Random.nextDouble(16.0, 60.0)
        hipotenusa = redondeaA2Decimales(hipotenusa)

        val texto = "Una rampa de " + hipotenusa.toString() + " ft se apoya en una base que tiene una altura de " + altura.toString() + " ft. Encuentra el ??ngulo de elevaci??n. Escribe tu respuesta con 2 decimales."

        val radianes = asin(altura/hipotenusa)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.rampa)
    }

    // MARK: - Ejercicios de Poste

    fun preguntaTipo6A() : Pregunta {

        var hipotenusa = Random.nextDouble(3.0, 5.0)
        hipotenusa = redondeaA2Decimales(hipotenusa)

        var angulo = Random.nextInt(30, 70)

        val texto = "Calcula la altura de un poste si el cable de acero para reforzarlo mide " + hipotenusa.toString() + " m y el ??ngulo que debe formar el cable con el piso es de " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = hipotenusa * sin(radianes)

        return Pregunta(texto, altura, "m", R.drawable.poste)
    }

    fun preguntaTipo6D() : Pregunta {

        var altura = Random.nextDouble(9.0, 20.0)
        altura = redondeaA2Decimales(altura)

        var angulo = Random.nextInt(40, 70)

        val texto = "Se quiere fijar un poste de " + altura.toString() + " ft de altura, con un cable de acero que va desde el extremo superior del poste al suelo. ??Cu??l debe ser la longitud del cable si debe de instalarse a un ??ngulo de elevaci??n de " + angulo.toString() + "??? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val hipotenusa = altura.toDouble() / sin(radianes)

        return Pregunta(texto, hipotenusa, "ft", R.drawable.poste)
    }

    fun preguntaTipo6E() : Pregunta {

        var altura = Random.nextDouble(2.8, 7.0)
        altura = redondeaA2Decimales(altura)

        var distanciaBase = Random.nextDouble(2.0, 5.0)
        distanciaBase = redondeaA2Decimales(distanciaBase)

        val texto = "??Cu??l es el ??ngulo de elevaci??n de un cable de acero si se encuentra a " + distanciaBase.toString() + " m de la base del poste cuya altura es de " + altura.toString() + " m?. Escribe tu respuesta con 2 decimales."

        val radianes = atan(altura / distanciaBase)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.poste)
    }

    // MARK: - Ejercicios de Arbol

    fun preguntaTipo7A() : Pregunta {

        var longitud = Random.nextDouble(2.0, 12.0)
        longitud = redondeaA2Decimales(longitud)

        var angulo = Random.nextInt(20, 60)

        val texto = "Al atardecer, un ??rbol proyecta una sombra con ??ngulo de elevaci??n de " + angulo.toString() + "??. Si la longitud de la sombra es de " + longitud.toString() + " metros. ??Cu??l es la altura del ??rbol? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = longitud * tan(radianes)

        return Pregunta(texto, altura, "m", R.drawable.arbol)
    }

    fun preguntaTipo7B() : Pregunta {

        var hipotenusa = Random.nextDouble(2.0, 12.0)
        hipotenusa = redondeaA2Decimales(hipotenusa)

        var angulo = Random.nextInt(20, 60)

        val texto = "Al atardecer, un ??rbol proyecta una sombra con ??ngulo de elevaci??n de " + angulo.toString() + "??. Si la distancia desde la parte superior del ??rbol al extremo de la sombra es de " + hipotenusa.toString() + " metros. ??Cu??l es la altura del ??rbol? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = hipotenusa.toDouble() * sin(radianes)

        return Pregunta(texto, altura, "m", R.drawable.arbol)
    }

    fun preguntaTipo7E() : Pregunta {

        var altura = Random.nextDouble(2.0, 12.0)
        altura = redondeaA2Decimales(altura)

        var longitudSombra = Random.nextDouble(2.0, 12.0)
        longitudSombra = redondeaA2Decimales(longitudSombra)

        val texto = "Calcula el ??ngulo de elevaci??n del sol cuando la sombra que proyecta el ??rbol es de " + longitudSombra.toString() + " m y dicho ??rbol mide " + altura.toString() + " m. Escribe tu respuesta con 2 decimales."

        val radianes = atan(altura / longitudSombra)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.arbol)
    }

    // MARK: - Ejercicios de Tobogan

    fun preguntaTipo8A() : Pregunta {

        var longitud = Random.nextDouble(3.0, 8.0)
        longitud = redondeaA2Decimales(longitud)

        var angulo = Random.nextInt(40, 70)

        val texto = "Encuentra la altura de un tobog??n que tiene una longitud de " + longitud.toString() + " m y cuyo ??ngulo de elevaci??n es " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = longitud * sin(radianes)

        return Pregunta(texto, altura, "m", R.drawable.tobogan)
    }

    fun preguntaTipo8B() : Pregunta {

        var altura = Random.nextDouble(3.0, 6.0)
        altura = redondeaA2Decimales(altura)

        var angulo = Random.nextInt(40, 70)

        val texto = "Encuentra la longitud de un tobog??n que llega a una altura de " + altura.toString() + " m y cuyo ??ngulo de elevaci??n es " + angulo.toString() + "??. Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val hipotenusa = altura / sin(radianes)

        return Pregunta(texto, hipotenusa, "m", R.drawable.tobogan)
    }

    fun preguntaTipo8E() : Pregunta {

        var altura = Random.nextDouble(2.0, 6.0)
        altura = redondeaA2Decimales(altura)

        var hipotenusa = Random.nextDouble(6.5, 12.0)
        hipotenusa = redondeaA2Decimales(hipotenusa)

        val texto = "Un tobog??n llega a una altura de " + altura.toString() + " m y tiene una longitud de " + hipotenusa.toString() + " m. ??Cu??l es su ??ngulo de elevaci??n? Escribe tu respuesta con 2 decimales."

        val radianes = asin(altura / hipotenusa)
        val angulo = radianes * 180 / PI.toDouble()

        return Pregunta(texto, angulo, "??", R.drawable.tobogan)
    }

    // MARK: - Preguntas de Papalote

    fun preguntaTipo9B() : Pregunta {

        var alturaNino = Random.nextDouble(1.0, 1.5)
        alturaNino = redondeaA2Decimales(alturaNino)

        var hipotenusa = Random.nextDouble(10.0, 50.0)
        hipotenusa = redondeaA2Decimales(hipotenusa)

        val angulo = Random.nextInt(50, 85)

        val texto = "Un ni??o sostiene la cuerda de un papalote a " + alturaNino.toString() + " m de altura. Si la cuerda se encuentra tensa y mide " + hipotenusa.toString() + " m, formando un ??ngulo de elevaci??n de " + angulo.toString() + "??. ??A qu?? altura del piso se encuentra el papalote? Escribe tu respuesta con 2 decimales."

        val radianes = angulo.toDouble() * PI.toDouble() / 180.0
        val altura = hipotenusa * sin(radianes) + alturaNino

        return Pregunta(texto, altura, "m", R.drawable.papalote)
    }

}