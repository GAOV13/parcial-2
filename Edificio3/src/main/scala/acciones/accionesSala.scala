package acciones

import elemento._
import scala.util._

abstract class AccionesSala extends Accion{
    def encederLuz(sala1 : Salon) : Unit 

    def apagarLuz(sala2 : Salon) : Unit 

    def encenderTemp(sala3 : Salon) : Unit

    def apagarTemp(sala4 : Salon) : Unit
    
    def reservar(sala5 : Salon, new_reserva : Array[Array[String]]) : Unit
}
