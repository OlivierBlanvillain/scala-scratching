package catss

import cats.Monad
import cats.syntax.flatMap._
import cats.syntax.functor._

object monad extends App{

  case class Box[A](value: A){

    // dont need to define map as cats will give for free
    def bind[B](f: A => Box[B]) = f(value)
  }

  // by providing our box monad we now get to use cats.Monad methods
  implicit val boxMonad = new Monad[Box] {
    override def pure[A](x: A): Box[A] = Box(x)

    override def flatMap[A, B](fa: Box[A])(f: (A) => Box[B]): Box[B] = fa.bind(f)

    override def tailRecM[A, B](a: A)(f: (A) => Box[Either[A, B]]): Box[B] = defaultTailRecM(a)(f)
  }

  val newBox = Box(10).flatMap(a=> Box(a+1))

  println("newBox = " + newBox)

  val adding = for {
    b1 <- Box(1)
    b2 <- Box(2)
  } yield b1 + b2

  println("adding = " + adding)
}
