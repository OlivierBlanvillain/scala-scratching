package catss


import cats.{Functor, Monad, MonadReader}
import cats.data.{OptionT, Reader, Xor, XorT}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalaz.concurrent.Task

object monadtransformers extends App{

  // Concerned with dealing with nested monads, like Task[Xor[A,B]]

  class GenerationException(number: Long, message: String) extends Exception(message)

  object NumberProducer{
    def queryNextNumber: Task[Xor[GenerationException, Long]] = Task {
      Xor.catchOnly[GenerationException]{
        val source = Math.round(Math.random * 100)
        if (source <=60) source else throw new GenerationException(source, "The number is too big")
      }
    }
  }

  // dont need monad instance for Xor as it's built-in
  implicit val taskMonad = new Monad[Task] {
    override def pure[A](x: A): Task[A] = Task.now(x)

    override def flatMap[A, B](fa: Task[A])(f: (A) => Task[B]): Task[B] = fa.flatMap(f)

    override def tailRecM[A, B](a: A)(f: (A) => Task[Either[A, B]]): Task[B] = defaultTailRecM(a)(f)
  }

  val maybe1 = NumberProducer.queryNextNumber
  val maybe2 = NumberProducer.queryNextNumber

  val result = for {
    num1 <- XorT(maybe1)
    num2 <- XorT(maybe2)
  } yield num1 + num2

  val tada = result.value.unsafePerformSync
  println("tada = " + tada)
  
}
