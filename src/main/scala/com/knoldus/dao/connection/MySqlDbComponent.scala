package com.knoldus.dao.connection

import slick.jdbc.MySQLProfile

trait MySqlDbComponent extends DBComponent {
  override val driver = MySQLProfile

  import driver.api._

  override val db = Database.forConfig("db")

}
