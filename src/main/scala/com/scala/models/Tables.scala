package com.scala.models

import slick.jdbc.H2Profile.api._

class Products(tag: Tag) extends Table[Product](tag, "PRODUCT") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def price = column[Double]("PRICE")
  def description = column[String]("DESCRIPTION")

  def * = (id, name, price, description) <> (Product.tupled, Product.unapply)
}

class Users(tag: Tag) extends Table[User](tag, "USER") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def username = column[String]("USERNAME")
  def password = column[String]("PASSWORD")

  def * = (id, username, password) <> (User.tupled, User.unapply)
}

class Orders(tag: Tag) extends Table[Order](tag, "ORDER") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def userId = column[Long]("USER_ID")
  def productId = column[Long]("PRODUCT_ID")
  def quantity = column[Int]("QUANTITY")

  def * = (id, userId, productId, quantity) <> (Order.tupled, Order.unapply)
}
