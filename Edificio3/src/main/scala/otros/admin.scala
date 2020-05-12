package otros

import elemento._
import acciones._
import scala.util._
import edificio._

class Admin extends AccionesAdmin {
    var _constraseña : String = "madremiawilly"
    var _estado : Boolean = false

    //Getters
    def estado = _estado

    override def abrir(id : String) : Unit ={
        if(id == _constraseña){_estado = true}
    }

    override def cerrar(id : String) ={
        if(id == "1234"){_estado = false}
    }

    override def habilitar (sala6 : Salon) : Unit ={
        sala6.estado_mantenimiento = false
    }

    override def deshabilitar (sala7 : Salon) : Unit ={
        sala7.estado_mantenimiento = true
    } 

    override def cambiarTiempos (tiempo1 : Double, tiempo2 : Double, tiempo3 : Double,
                       tiempo4 : Double, tiempo5 : Double, tiempo6 : Double, 
                       tiempo7 : Double, edificio : Edificio) 
                       : Unit = {
        
        edificio.tiempo_luz_on = tiempo1
        edificio.tiempo_luz_off = tiempo2
        edificio.tiempo_temp_on = tiempo3
        edificio.tiempo_temp_off = tiempo4
        edificio.tiempo_reserva_in = tiempo5
        edificio.tiempo_reserva_out = tiempo6
        edificio.tiempo_apertura = tiempo7
    }

    override def cambiarTemperatura(sala8 : Salon, temp : Double) : Unit ={
        sala8.temperatura = temp
    }
}