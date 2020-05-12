package elemento

import Array._

class Reserva(id : String){
    /*Atributos*/
    var _id_sala : String = id
    var _horario : Array[Array[String]] = Array.ofDim[String](6, 24)

    /*Setters*/

    def horario_= (nuevo_horario : Array[Array[String]]) = _horario = nuevo_horario

    /*Getter*/

    def id_sala = _id_sala
    def horario = _horario
}