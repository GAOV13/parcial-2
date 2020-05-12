package edificio

import acciones._
import elemento._
import otros._
import scala.util._
import Array._

class Edificio extends AccionesSala{
    var _elementos : Array[Elemento] = Array()
    var _tiempo_luz_on : Double = 5.0 //Minutos
    var _tiempo_luz_off : Double = 10.0 //Minutos
    var _tiempo_temp_on : Double = 10.0 //Minutos
    var _tiempo_temp_off : Double = 5.0 //Minutos
    var _tiempo_reserva_in : Double = 7.00 //Hora militar
    var _tiempo_reserva_out : Double = 19.00 //Hora militar
    var _tiempo_apertura : Double = 15.0 //Minutos
    var _admin : Admin = new Admin

    //Getters
    def tiempo_luz_on = _tiempo_luz_on 
    def tiempo_luz_off = _tiempo_luz_off 
    def tiempo_temp_on = _tiempo_temp_on 
    def tiempo_temp_off = _tiempo_temp_off 
    def tiempo_reserva_in = _tiempo_reserva_in 
    def tiempo_reserva_out = _tiempo_reserva_out 
    def tiempo_apertura = _tiempo_apertura

    //Seters
    def tiempo_luz_on_= (new_tiempo_luz_on : Double) = _tiempo_luz_on = new_tiempo_luz_on  
    def tiempo_luz_off_= (new_tiempo_luz_off : Double) = _tiempo_luz_off = new_tiempo_luz_off
    def tiempo_temp_on_= (new_tiempo_temp_on : Double) = _tiempo_temp_on = new_tiempo_temp_on
    def tiempo_temp_off_= (new_tiempo_temp_off : Double) = _tiempo_temp_off = new_tiempo_temp_off
    def tiempo_reserva_in_= (new_tiempo_reserva_in : Double) = _tiempo_reserva_in = new_tiempo_reserva_in
    def tiempo_reserva_out_= (new_tiempo_reserva_out : Double) = _tiempo_reserva_out = new_tiempo_reserva_out
    def tiempo_apertura_= (new_tiempo_apertura : Double) = _tiempo_apertura = new_tiempo_apertura

    //Auxiliares
    def buscar(new_id : String) : Int ={
        var cont = 0
        var dev = -1
        for (n <- _elementos){
            if(n.id == new_id){
                dev = cont
            }
            cont = cont + 1
        }
        return dev
    }

    //metodos
    def anadirSala (sala : Salon) : Unit ={
        _elementos :+= sala
    }

    def buscarSala (new_id : String) : Try[Salon] ={
        var dev = buscar(new_id)
        var valor : Try[Salon] = Try(_elementos(dev).asInstanceOf[Salon])  
        return valor
    }

    override def encenderTemp(sala : Salon) : Unit ={
        sala.estado_temp = true
    }

    override def apagarTemp(sala : Salon) : Unit ={
        sala.estado_temp = false
    }

    override def encederLuz(sala1 : Salon) : Unit ={
       sala1.estado_luz = true
    }

    override def apagarLuz(sala2 : Salon) : Unit ={
       sala2.estado_luz = false
    }

    override def abrir(id : String) : Unit ={
        var sala : Try[Salon] = buscarSala(id);
        sala match{
            case Success(s) => s.cerradura = true 
            case Failure(s) => ""
        }
    }

    override def cerrar(id : String) : Unit ={
        var sala : Try[Salon] = buscarSala(id);
        sala match{
            case Success(s) => s.cerradura = false 
            case Failure(s) => ""
        }
    }

    override def reservar(sala5 : Salon, new_horario : Array[Array[String]]) : Unit ={
        sala5.nuevoHorario(new_horario)
    }
}
