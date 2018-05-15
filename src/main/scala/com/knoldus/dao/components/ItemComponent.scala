package com.knoldus.dao.components

import com.knoldus.dao.connection.{ DBComponent, MySqlDbComponent }
import com.knoldus.models.Item
import slick.jdbc.MySQLProfile.api._

trait ItemComponent extends ItemTable with DBComponent {

}

object ItemComponent extends ItemComponent with MySqlDbComponent

trait ItemTable {

  class ItemTable(tag: Tag) extends Table[Item](tag, "item_detail") {

    def itemId = column[Int]("item_id", O.PrimaryKey)
    def itemName = column[String]("item_name")
    def price = column[Double]("price")

    def * = (itemId, itemName, price) <> (Item.tupled, Item.unapply)

  }

  val itemQuery = TableQuery[ItemTable]
}
