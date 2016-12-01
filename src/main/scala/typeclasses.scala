/*
Type class interface

https://github.com/mpilquist/simulacrum uses macro funtimes to make writing type classes significantly less painful

Without this you have to write some kind of implicit class to convert your T to an InfoPrinter so you can call toInfo
 */

@simulacrum.typeclass trait InfoPrinter[T]{
  def toInfo(value: T): String
}

// Some defaults that as a library producer we can do
object DefaultInfoPrinters{

  implicit val stringPrinter = new InfoPrinter[String] {
    override def toInfo(value: String): String = s"[String] $value"
  }

  implicit val intPrinter = new InfoPrinter[Int] {
    override def toInfo(value: Int): String = s"[Int] $value"
  }
}

object Printer{
  def megaPrint[T](value: T)(implicit printer: InfoPrinter[T]): Unit ={
    println("MEGA PRINT " + printer.toInfo(value))
  }
}

case class User(name: String, age: Int)

object User{
  implicit val userPrinter = new InfoPrinter[User] {
    override def toInfo(value: User): String = s"[User] ${value.name}, aged ${value.age}"
  }
}

import InfoPrinter.ops._
import DefaultInfoPrinters._
import Printer._

object typeclasses extends App{
  val number = 100
  println(number.toInfo)

  val u = User("Chris", 32)
  println(u.toInfo)

  megaPrint(u)

}
