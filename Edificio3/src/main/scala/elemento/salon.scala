package elemento

class Salon (id : String) extends Elemento{
    /*Atributos*/
    override var _id : String = id
    var _reserva : Reserva = new Reserva(id)
    var _estado_luz : Boolean = false
    var _temperatura : Double = 23.0
    var _estado_temp : Boolean = false
    var _estado_mantenimiento : Boolean = false
    var _estado_ocupado : Boolean = false
    var _cerradura : Boolean = false

    /*Setters*/
    def estado_luz_= (nuevo_est : Boolean) = _estado_luz = nuevo_est
    def temperatura_= (temp : Double) = _temperatura = temp
    def estado_mantenimiento_= (nuevo_est : Boolean) = _estado_mantenimiento = nuevo_est
    def estado_ocupado_= (nuevo_est : Boolean) = _estado_ocupado = nuevo_est
    def cerradura_= (cerradura : Boolean) = _cerradura = cerradura
    def estado_temp_= (temp : Boolean) = _estado_temp = temp

    /*Getters*/

    def estado_luz = _estado_luz
    def temperatura = _temperatura
    def estado_mantenimiento = _estado_mantenimiento
    def estado_ocupado = _estado_ocupado
    def cerradura = _cerradura
    def estado_temp = _estado_temp

    /*Metodos*/
    def nuevoHorario(temp : Array[Array[String]]) : Unit ={
        _reserva.horario = temp
    }
}