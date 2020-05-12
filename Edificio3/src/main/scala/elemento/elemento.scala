package elemento

abstract class Elemento{
    /*Atributos*/
    var _id : String

    /*Setter*/
    def id_= (nuevo_id : String) = _id = nuevo_id

    /*Getter*/
    def id = _id
}