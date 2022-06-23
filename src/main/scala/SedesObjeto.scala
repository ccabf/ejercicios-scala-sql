import java.sql.Date

case class SedesObjeto(
                        var id: Int,
                        var direccion:String,
                        var tlf:String,
                        var activa:Boolean,
                        var fecha_alta:Date,
                        var fact_anual:Float
                        ) {

  override def toString = s"SedesObjeto(id=$id, direccion=$direccion, tlf=$tlf, activa=$activa, fecha_alta=$fecha_alta, fact_anual=$fact_anual)"

}

