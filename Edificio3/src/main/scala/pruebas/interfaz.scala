package pruebas

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

object Main extends App{
    var edificio = new Edificio
    var sala = new Salon("1.1")
    edificio.anadirSala(sala)
    var flag = true
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

    while(flag){
        verificar()
        var opcion : Int = 0
        var temp : String = ""
        println("===============================")
        println("Bienvenido Al sistema de Palmas")
        println("===============================")
        println("Que desea hacer: ")
        println("[1] reservar una sala")
        println("[2] ver horarios")
        println("[3] administrador")
        println("[4] salir")
        println("===============================")
        var ver : Try[Int] = Try(StdIn.readInt())
        ver match{
            case Success(s) => opcion = s;
            case Failure(f) => println("Hubo un fallo a la hora de escoger")
        }

        def imprimir() : Int ={
            var i : Int = 0
             for (n <- edificio._elementos){
                printf("[%d] Sala identificada como ", i + 1)
                println(n.id)
                i = i + 1
            }
            println("===================================")
            println("Por favor, ingrese el numero de la ")
            println("sala que desea ver.")
            i = -1
            ver = Try(StdIn.readInt()) 
            ver match{
                case Success(s) => i = s;
                case Failure(f) => println("Hubo un fallo a la hora de escoger")
            }
            return i
        }

        if(opcion == 1){
            println("====================================")
            println("Bienvenido Al sistema de Reservar")
            println("====================================")
            println("Ingrese la identificacion de la sala:")
            temp = StdIn.readLine()
            var i : Int = edificio.buscar(temp)
            var fila : Int = 0
            var col : Int = 0
            var ver1 : Try[Int] = Try(0)
            var sala_temp : Try[Salon] = Try(edificio._elementos(i).asInstanceOf[Salon])
            if(sala_temp.isSuccess){
                var horas : Array[Array[String]] = sala_temp.get._reserva.horario
                println("Por favor, ingrese en que dia (1-6)")
                ver = Try(StdIn.readInt())
                if(ver.isSuccess){
                    println("Por favor, ingrese en que bloque del dia (1-24)")
                    ver1 = Try(StdIn.readInt())
                    if(ver1.isSuccess){
                        fila = ver.get
                        col = ver1.get
                        println("Ingrese el nombre de la clase")
                        temp = StdIn.readLine()
                        if(horas(fila - 1)(col - 1) == null){
                            horas(fila - 1)(col - 1) = temp
                            edificio._elementos(i).asInstanceOf[Salon].nuevoHorario(horas)
                            println("Reserva exitosa. tenga presente que esta accion no se puede\ndeshacer. Por favor use la sala.")
                        }
                        else{
                            println("Bloque del dia ocupado")
                        }
                    }
                }
                if(ver.isFailure || ver1.isFailure){
                    println("Hubo un error")
                }
            }
            else{
                println("Esa sala no existe, verifique que escribio el nombre correcto")
            }
        }

        else if(opcion == 2){
            while(flag){
                var i : Int = 0
                println("===================================")
                println("Bienvenido a los horarios de Palmas")
                println("===================================")
                println("[0] salir")
                i = imprimir()
                println(i)
                if(i == 0){
                    flag = false
                }
                else{
                    var sala_temp : Try[Salon] = Try(edificio._elementos(i - 1).asInstanceOf[Salon])
                    if(sala_temp.isSuccess){
                        var horas : Array[Array[String]] = sala_temp.get._reserva.horario
                        println("\tLUNES\tMARTES\tMIERCO\tJUEVES\tVIERNES\tSABADO")
                        for(n <- 0 to 23){
                            println((700 + ((n/2) * 100) + (if(n%2 == 0){0} else{30})) + "\t" + horas(0)(n) + "\t" + horas(1)(n) + "\t" + horas(2)(n) + "\t" + horas(3)(n) + "\t" + horas(4)(n) + "\t" + horas(5)(n) + "\t")
                        }
                        println("Presione para volver")
                        temp = StdIn.readLine()
                    }
                    else{
                        println("Hubo un fallo a la hora de escoger")
                    }
                }
            }
            flag = true
        }

        else if(opcion == 3){
            println("Por favor ingrese la contrasena: ")
            temp = StdIn.readLine()
            edificio._admin.abrir(temp)
            while(edificio._admin.estado){
                opcion = 0
                println("==============================================")
                println("Bienvenido Administrador del sistema de Palmas")
                println("==============================================")
                println("Que desea hacer: ")
                println("[1] Anadir sala")
                println("[2] Cambiar Tiempos")
                println("[3] Modificar Una sala")
                println("[4] salir")
                ver = Try(StdIn.readInt())
                ver match{
                    case Success(s) => opcion = s;
                    case Failure(f) => println("Hubo un fallo a la hora de escoger")
                }
                if(opcion == 1){
                    println("Ingrese el identificadro de la nueva sala")
                    temp = StdIn.readLine()
                    var i : Int = edificio.buscar(temp)
                    var new_salon : Try[Salon] = Try(edificio._elementos(i).asInstanceOf[Salon])
                    if(new_salon.isSuccess){
                        println("Dicha sala ya existe")
                    }
                    else{
                        var sala1 = new Salon(temp)
                        edificio._admin.deshabilitar(sala1)
                        edificio.anadirSala(sala1)
                        println("Sala creada y deshabilitada para el publico.")
                        println("Si desea activarla, porfavo ingrese a la opcion de modificar sala")
                    }
                }
                else if(opcion == 2){
                    println("Los tiempos del sistema son")
                    println("El tiempo de encender las luces es de " + edificio.tiempo_luz_on)
                    println("El tiempo de apagar las luces es de " + edificio.tiempo_luz_off)
                    println("El tiempo de encender las temperaturas es de " + edificio.tiempo_temp_on)
                    println("El tiempo de apagar las temperaturas es de " + edificio.tiempo_temp_off)
                    println("El tiempo de inicio de las reservas es " + edificio.tiempo_reserva_in)
                    println("El tiempo de finalizacion las reservas es " + edificio.tiempo_reserva_out)
                    println("El tiempo de abrir las puertas es de " + edificio.tiempo_apertura)
                    var tiempos : Array[Double] = Array()
                    var cont : Int = 0
                    println("Acontinuacion ingrese los tiempos en el mismo orden de arriba\nAtencion, si no se hace en ese orden se cambiaran otros tiempos")
                    while(flag){
                        ver = Try(StdIn.readInt())
                        ver match{
                            case Success(v) =>{
                                tiempos :+= v.asInstanceOf[Double]
                                cont = cont + 1
                                if(cont == 7){
                                    flag = false
                                }
                            } 
                            case Failure(f) => println("Hubo un fallo, por favor vuelva a ingresar el tiempo")
                        }
                    }
                    flag = true
                    println("Los tiempos son:")
                    for(n <- tiempos){
                        println(n)
                    }
                    edificio._admin.cambiarTiempos(tiempos(0), tiempos(1), tiempos(2), tiempos(3), tiempos(4),
                                                   tiempos(5), tiempos(6), edificio) 
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
                else if(opcion == 3){
                    opcion = 0
                    while(flag){
                        println("===========================================================")
                        println("Bienvenido Administrador de las salas del sistema de Palmas")
                        println("===========================================================")
                        println("Que desea hacer: ")
                        println("[1] habilitar sala")
                        println("[2] deshabilitar sala")
                        println("[3] Modificar temperatura sala")
                        println("[4] salir")
                        ver = Try(StdIn.readInt())
                        ver match{
                            case Success(s) => opcion = s;
                            case Failure(f) => println("Hubo un fallo a la hora de escoger")
                        }
                        if(opcion == 1){
                            var i : Int = imprimir()
                            var sala_temp : Try[Salon] = Try(edificio._elementos(i - 1).asInstanceOf[Salon])
                            if(sala_temp.isSuccess){
                                edificio._admin.habilitar(edificio._elementos(i - 1).asInstanceOf[Salon])
                                println("El mantenimiento de la sala es " + edificio._elementos(i - 1).asInstanceOf[Salon].estado_mantenimiento)
                            }

                            else{
                                println("Hubo un fallo a la hora de escoger")
                            }
                        }
                        else if(opcion == 2){
                            var i : Int = imprimir()
                            var sala_temp : Try[Salon] = Try(edificio._elementos(i - 1).asInstanceOf[Salon])
                            if(sala_temp.isSuccess){
                                edificio._admin.deshabilitar(edificio._elementos(i - 1).asInstanceOf[Salon])
                                println("El mantenimiento de la sala es " + edificio._elementos(i - 1).asInstanceOf[Salon].estado_mantenimiento)
                            }
                            else{
                                println("Hubo un fallo a la hora de escoger")
                            }
                        }
                        else if(opcion == 3){
                            var i : Int = imprimir()
                            var sala_temp : Try[Salon] = Try(edificio._elementos(i - 1).asInstanceOf[Salon])
                            if(sala_temp.isSuccess){
                                var temper : Double = 0.0
                                while(flag){
                                    ver = Try(StdIn.readInt())
                                    ver match{
                                        case Success(v) =>{
                                            temper = v.asInstanceOf[Double]
                                            flag = false
                                        } 
                                        case Failure(f) => println("Hubo un fallo, por favor vuelva a ingresar la temperatura")
                                    }
                                }
                                flag = true
                                edificio._admin.cambiarTemperatura(edificio._elementos(i - 1).asInstanceOf[Salon], temper);
                                println("La nueva temperatura del salon es " + edificio._elementos(i - 1).asInstanceOf[Salon].temperatura)
                            }
                            else{
                                println("Hubo un fallo a la hora de escoger")
                            }
                        }
                        else if(opcion == 4){
                            flag = false
                        }
                    }
                    flag = true
                }
                else if(opcion == 4){
                    temp = StdIn.readLine()
                    edificio._admin.cerrar(temp)
                }
            }
        }
        
        else if(opcion == 4){
            flag = false
            println("==================================")
            println("Gracias por usarnos, Vuelva pronto")
            println("==================================")
        }
    }
}
