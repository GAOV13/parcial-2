/* package pruebas

import acciones._
import elemento._
import otros._
import scala.util._
import edificio._
import Array._

object Prueba extends App{
    var sala : Salon = new Salon("A_001");
    var edificio : Edificio = new Edificio
    println("Pruebas del salon")
    println(sala.id)
    /*
    sala.estado_luz = true
    sala.temperatura = 25.0
    sala.estado_mantenimiento = true
    sala.estado_ocupado = true
    sala.cerradura = true
    sala.estado_temp = true 
    println(sala.estado_luz)
    println(sala.temperatura)
    println(sala.estado_mantenimiento)
    println(sala.estado_ocupado)
    println(sala.cerradura)
    println(sala.estado_temp) */
    
    
    
    var myMatrix : Array[Array[String]] = Array.ofDim[String](6, 24)
    myMatrix(0)(0) = "Fisica"
    sala.nuevoHorario(myMatrix)
    println(sala._reserva._horario(0)(0))
    println(sala._reserva._horario(0)(1))
    println(Try(edificio._elementos(0)))
    edificio.anadirSala(sala)
    println(Try(edificio._elementos(0)))
    var valor : Try[Salon] = edificio.buscarSala("1")
    println(valor)
    valor = edificio.buscarSala("A_001")
    println(valor)

    valor match{
        case Success(s) => sala.id = s.id
        case Failure(v) => "Sala no Encontrada"
    }

    edificio.encenderTemp(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la temperatura del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_temp)
    edificio.apagarTemp(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la temperatura del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_temp)

    edificio.encederLuz(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la luz del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_luz)
    edificio.apagarLuz(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la luz del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_luz)
    
    edificio.abrir(sala.id)
    printf("El estado de la puerta del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].cerradura)
    edificio.cerrar(sala.id)
    printf("El estado de la puerta del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].cerradura)

    edificio._admin.habilitar(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la sala es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_mantenimiento)
    edificio._admin.deshabilitara(edificio._elementos(0).asInstanceOf[Salon])
    printf("El estado de la sala es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].estado_mantenimiento)
    
    edificio._admin.cambiarTemperatura(edificio._elementos(0).asInstanceOf[Salon], 8.0)
    printf("La temperatura del salon es: ")
    println(edificio._elementos(0).asInstanceOf[Salon].temperatura)

    println("\nPruebas Edifico")
    edificio._admin.cambiarTiempos(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, edificio)
    
    printf("El tiempo de inicio de la luz es: ")
    println(edificio.tiempo_luz_on)
    printf("El tiempo de apagado de la luz es: ")
    println(edificio.tiempo_luz_off) 
    printf("El tiempo de inicio de la temp es: ")
    println(edificio.tiempo_temp_on)
    printf("El tiempo de apagado de la temp es: ")
    println(edificio.tiempo_temp_off) 
    printf("El tiempo de inicio de las reservas es: ")
    println(edificio.tiempo_reserva_in)
    printf("El tiempo de finalizacion de las reservas es: ")
    println(edificio.tiempo_reserva_out)
    printf("El tiempo de inicio de la apertura de las salas es: ")
    println(edificio.tiempo_apertura)
}
 */