package com.knoldus.dao.components

import scala.concurrent.Future

import com.knoldus.dao.connection.{ DBComponent, MySqlDbComponent }
import com.knoldus.models.Item
import slick.jdbc.MySQLProfile.api._

trait ItemComponent extends ItemTable with DBComponent {

  def insert(item: Item): Future[Int] = {
    db.run(itemQuery += item)
  }

  def getAllItems: Future[List[Item]] = {
    db.run(itemQuery.to[List].result)
  }

  def addItem(item: Item): Future[Int] = {
    db.run(itemQuery += item)
  }

  def deleteItem(itemId: Int): Future[Int] = {
    db.run(itemQuery.filter(_.itemId === itemId).delete)
  }

}

object ItemComponent extends ItemComponent with MySqlDbComponent

trait ItemTable {

  class ItemTable(tag: Tag) extends Table[Item](tag, "item_detail") {

    def itemId = column[Int]("item_id", O.PrimaryKey, O.AutoInc)
    def itemName = column[String]("item_name")
    def price = column[Double]("price")

    def * = (itemId, itemName, price) <> (Item.tupled, Item.unapply)

  }

  val itemQuery = TableQuery[ItemTable]
}
