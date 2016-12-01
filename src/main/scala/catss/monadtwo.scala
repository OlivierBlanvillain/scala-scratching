package catss

import cats.Monad

import scala.io.StdIn

object monadtwo extends App{

  // console reading as a monadic computation

  trait ConsoleAction[A]{
    def bind[B](f: A => ConsoleAction[B]): ConsoleAction[B]
  }

  case class Read() extends ConsoleAction[String]{
    override def bind[B](f: (String) => ConsoleAction[B]): ConsoleAction[B] = {
      val input = StdIn.readLine()
      f(input)
    }
  }

  case class Write(output: String) extends ConsoleAction[Unit]{
    override def bind[B](f: (Unit) => ConsoleAction[B]): ConsoleAction[B] = {
      println(output)
      f()
    }
  }

  implicit val consoleMonad = new Monad[ConsoleAction] {
    override def pure[A](x: A): ConsoleAction[A] = Read().asInstanceOf[ConsoleAction[A]]

    override def flatMap[A, B](fa: ConsoleAction[A])(f: (A) => ConsoleAction[B]): ConsoleAction[B] = fa.bind(f)

    override def tailRecM[A, B](a: A)(f: (A) => ConsoleAction[Either[A, B]]): ConsoleAction[B] = defaultTailRecM(a)(f)
  }

  import cats.implicits._

  for {
    _ <- Write("Write your name")
    name <- Read()
    _ <- Write(s"Hello $name")
  } yield()
}
