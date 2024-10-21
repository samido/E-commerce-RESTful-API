package com.scala

import akka.http.scaladsl.Http
import akka.actor.ActorSystem
import com.example.routes.Routes
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContextExecutor

object Main extends App {
  implicit val system: ActorSystem = ActorSystem("ecommerce-system")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val db = Database.forConfig("slick.dbs.default")
  val routes = new Routes(db)

  Http().newServerAt("localhost", 8080).bind(routes.route)

  println("Server online at http://localhost:8080/")
}
