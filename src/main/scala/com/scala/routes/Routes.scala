package com.scala.routes

import akka.http.scaladsl.server.Directives._
import com.example.models._
import slick.jdbc.H2Profile.api._
import scala.concurrent.ExecutionContext

class Routes(db: Database)(implicit ec: ExecutionContext) {
  val products = TableQuery[Products]

  val route =
    pathPrefix("products") {
      pathEndOrSingleSlash {
        get {
          // Get all products
          complete {
            db.run(products.result)
          }
        } ~
          post {
            // Create a new product
            entity(as[Product]) { product =>
              complete {
                db.run(products += product)
              }
            }
          }
      } ~
        path(LongNumber) { id =>
          get {
            // Get a specific product by id
            complete {
              db.run(products.filter(_.id === id).result.headOption)
            }
          } ~
            put {
              // Update a product
              entity(as[Product]) { product =>
                complete {
                  db.run(products.filter(_.id === id).update(product))
                }
              }
            } ~
            delete {
              // Delete a product
              complete {
                db.run(products.filter(_.id === id).delete)
              }
            }
        }
    }
}

