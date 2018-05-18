package com.knoldus.models

case class Item(itemId: Int, itemName: String, price: Double)
case class ItemJson(itemId: Int, itemName: String, price: Double, itemQuantity: Int, amount: Double)

