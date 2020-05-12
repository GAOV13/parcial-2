/* package pruebas

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import scala.io._
import acciones._
import elemento._
import otros._
import scala.util._
import edificio._
import Array._

object Tempo extends App{
    var edificio : Edificio = new Edificio
    var sala : Salon = new Salon("1.1")
    edificio.anadirSala(sala)
    def verificar() : Unit ={
        var t : Int = 0
        var i : Int = 0
        val now = Calendar.getInstance()
        val currentMinute = now.get(Calendar.MINUTE)
        val currentHour = now.get(Calendar.HOUR_OF_DAY)
        var tiempo = currentHour * 100 + currentMinute
        if(tiempo <= 1900 && tiempo >= 700){
            i = tiempo - 700
            var resto : Int = i % 100
            i = i / 100
            if(resto >= 30){
                i = i * 2 + 1
                println(i)
            }

            else{
                i = i * 2
                println(i)
            }
        }
        for(n <- edificio._elementos){
            var temp : String = n.asInstanceOf[Salon]._reserva.horario(0)(i)
            if(temp == null){
                t = 700 + (((i)/2) * 100)  
                if((i)%2 == 0){t = t} 
                else{t = t + 30}
                println(t)
                println(tiempo)
                println((tiempo - t))
                
                if(edificio.tiempo_temp_off >= tiempo - t){edificio.apagarTemp(n.asInstanceOf[Salon])} 
                if(edificio.tiempo_luz_off >= tiempo - t){edificio.apagarLuz(n.asInstanceOf[Salon])} 
                if(edificio.tiempo_apertura >= tiempo - t){edificio.abrir(n.id)}
                println(edificio.tiempo_apertura + " >= " + (tiempo - t))
                println(n.asInstanceOf[Salon].cerradura)
            }

            if(temp != null){
                temp = n.asInstanceOf[Salon]._reserva.horario(0)(i + 1)
                t = 700 + (((i + 1)/2) * 100)  
                if((i + 1)%2 == 0){t = t - 40} 
                else{t = t + 30}

                println(t)
                println(tiempo)
                println((t - tiempo))
                if(edificio.tiempo_luz_on <= t - tiempo){edificio.encederLuz(n.asInstanceOf[Salon])} 
                if(edificio.tiempo_temp_on <= t - tiempo){edificio.encenderTemp(n.asInstanceOf[Salon])} 
                if(edificio.tiempo_apertura <= t - tiempo){edificio.abrir(n.id)}
            }
        }
    }
} */