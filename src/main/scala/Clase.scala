import java.sql.DriverManager

object Clases {
  def main(args: Array[String]): Unit = {
    class Cliente(var id: String = "", var nombre: String = "", var tlf: String = "", var email: String = "") {
      override def toString: String =
        s"(Datos de cliente: ID: $id, Nombre: $nombre, Tlf: $tlf, Email: $email)"
    }

    //Conexion a mysql

    import java.sql.{Connection, DriverManager}


    // connect to the database named "mysql" on port 8889 of localhost
    val url = "jdbc:mysql://localhost:3307/test"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "root"
    var connection: Connection = null
    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      println("Conectado a BBDD")
    } catch {
      case e: Exception => e.printStackTrace
    }


    try {
      val dao = new ClientesDao(connection)
      val meta = connection.getMetaData
      var res = meta.getTables("test", null, "clients", null)
      if (res.next()) {
        println("Tabla ya creada")
      } else {
        val res = dao.createTable
        println("Tabla creada?: " + res)
      }
    }
    connection.close
  }
}
