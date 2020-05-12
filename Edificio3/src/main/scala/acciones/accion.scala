package acciones

import scala.util._

abstract class Accion{
    /*Metodos*/
    def abrir (id : String) : Unit 
    def cerrar (id : String) : Unit 
} 