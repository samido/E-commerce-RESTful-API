package com.scala.models

case class Product(id: Long, name: String, price: Double, description: String)
case class User(id: Long, username: String, password: String)
case class Order(id: Long, userId: Long, productId: Long, quantity: Int)

