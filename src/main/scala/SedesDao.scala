import java.sql.{Connection, Date, PreparedStatement, Statement}

case class SedesDao(var connection: Connection){ def createTable: Boolean ={
  val createTableSQL="""create table sedes (
                             id int unsigned auto_increment not null,
                             direccion varchar(32) not null,
                             tlf varchar(32) not null,
                             activa boolean,
                             fecha_alta timestamp default now(),
                             fact_anual float,
                             primary key (id)
                         );"""
  val statement=connection.prepareStatement(createTableSQL);
  try{
    statement.execute()
  } catch {
    case e: Exception => {
      false
    }
  }
}

  def save(objeto:SedesObjeto):SedesObjeto ={
    // the mysql insert statement// the mysql insert statement

    val query: String = " insert into sedes (direccion, tlf, activa, fecha_alta, fact_anual)" +
      " values (?, ?, ?, ?, ?)"

    // create the mysql insert preparedstatement
    val preparedStmt: PreparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)
    preparedStmt.setString(1, objeto.direccion)
    preparedStmt.setString(2, objeto.tlf)
    preparedStmt.setBoolean(3, objeto.activa)
    preparedStmt.setDate(4, objeto.fecha_alta)
    preparedStmt.setFloat(5, objeto.fact_anual)

    // execute the preparedstatement

    val res = preparedStmt.executeUpdate()
    val rs = preparedStmt.getGeneratedKeys
    var id = 0
    if (rs.next) id = rs.getInt(1)
    objeto.id=id
    objeto
  }
  def findAll:List[SedesObjeto] ={
    val selectSQL="select * from sedes;"
    val statement=connection.prepareStatement(selectSQL);
    val rs=statement.executeQuery()

    var listado2= List[SedesObjeto]()
    // iterate through the java resultset// iterate through the java resultset
    while ( {
      rs.next
    }) {
      val id = rs.getInt("id")
      val direccion = rs.getString("direccion")
      val tlf = rs.getString("tlf")
      val activa = rs.getBoolean("activa")
      val fecha_alta = rs.getDate("fecha_alta")
      val fact_anual = rs.getFloat("fact_anual")
      // print the results
      //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints)
      listado2 = listado2 ::: List( new SedesObjeto( id, direccion, tlf, activa, fecha_alta, fact_anual))
    }
    listado2
  }
  def findByID(id:Int):SedesObjeto={
    val selectSQL="select * from sedes where id=?;"
    val statement=connection.prepareStatement(selectSQL);
    statement.setInt(1, id)
    val rs=statement.executeQuery()

    var objeto:SedesObjeto=null
    // iterate through the java resultset// iterate through the java resultset
    if ( {
      rs.next
    }) {
      val id = rs.getInt("id")
      val direccion = rs.getString("direccion")
      val tlf = rs.getString("tlf")
      val activa = rs.getBoolean("activa")
      val fecha_alta = rs.getDate("fecha_alta")
      val fact_anual = rs.getFloat("fact_anual")
      // print the results
      //System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints)
      objeto=new SedesObjeto( id, direccion, tlf, activa, fecha_alta, fact_anual)
    }
    objeto
  }
  def update(objeto:SedesObjeto):SedesObjeto ={
    val id=objeto.id
    val query: String = " update users set direccion=?, tlf=?, activa=?, fecha_alta=?, num_points=? " +
      " where id=?"

    // create the mysql insert preparedstatement
    val preparedStmt: PreparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)
    preparedStmt.setString(1, objeto.direccion)
    preparedStmt.setString(2, objeto.tlf)
    preparedStmt.setBoolean(3, objeto.activa)
    preparedStmt.setDate(4, objeto.fecha_alta)
    preparedStmt.setFloat(5, objeto.fact_anual)
    preparedStmt.setInt(6, objeto.id)
    // execute the preparedstatement

    val res = preparedStmt.executeUpdate()
    if (res==null){
      return null
    }
    objeto
  }
  def delete(id:Int):Boolean={

    val query: String = " delete from sedes where id=?"
    val preparedStmt: PreparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)
    preparedStmt.setInt(1, id)
    val res = preparedStmt.executeUpdate()
    if (res==null){
      return false
    }
    true
  }

}
