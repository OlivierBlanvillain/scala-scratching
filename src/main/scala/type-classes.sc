// Type classes allow you to add behaviour to existing classes instead of inheritance

case class User(name: String, age: Int)

object User{
  implicit val userPrinter = new InfoPrinter[User] {
    override def toInfo(value: User): String = s"[User] ${value.name}, aged ${value.age}"
  }
}

// Type class interface
trait InfoPrinter[T]{
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

  // define an implicit conversion class
  implicit class PrintInfoOps[T](value: T){

    // so that we can now add functions to things, provided there is an info printer in scope
    def printInfo()(implicit printer: InfoPrinter[T]): Unit ={
      println(printer.toInfo(value))
    }

    // heres another
    def butt()(implicit printer: InfoPrinter[T]): Unit ={
      println("Butt " + printer.toInfo(value))
    }
  }

  // Can define functions which accept values which have a typeclass instance too
  def megaPrint[T](value: T)(implicit printer: InfoPrinter[T]): Unit ={
    println("MEGA PRINT " + printer.toInfo(value))
  }
}

import DefaultInfoPrinters._
import Printer._

val number = 100
number.printInfo()
number.butt()

val u = User("Chris", 32)
u.printInfo()

megaPrint(u)