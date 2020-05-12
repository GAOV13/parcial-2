package acciones

import elemento._
import edificio._
import scala.util._

abstract class AccionesAdmin extends Accion{
    /*Metodos*/
    def habilitar (sala6 : Salon) : Unit 

    def deshabilitar (sala7 : Salon) : Unit 

    def cambiarTiempos (tiempo1 : Double, tiempo2 : Double, tiempo3 : Double,
                       tiempo4 : Double, tiempo5 : Double, tiempo6 : Double, 
                       tiempo7 : Double, edificio : Edificio) : Unit 

    def cambiarTemperatura(sala8 : Salon, temp : Double) : Unit 
}