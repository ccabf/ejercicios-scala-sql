class ClienteObjeto (
  var id: Int ,
  var name:String ,
  var tlf:String ,
  var email:String
  ) {

    override def toString = s"ClienteObjeto(id=$id, name=$name, tlf=$tlf, email=$email)"

}
