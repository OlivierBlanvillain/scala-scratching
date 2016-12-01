package scalaz

import scalaz._, Scalaz._

object Random {
  def queryNextNumber: Exception \/ Long = {
    val source = Math.round(Math.random * 100)
    if(source < 50) \/.right(source)
    else \/.left(new Exception("the generated number is too big"))
  }
}

object disjunction extends App{

  val numberUnder100 = for {
    first <- Random.queryNextNumber
    second <- Random.queryNextNumber
  } yield(first + second)

  println("numberUnder100 = " + numberUnder100)

}
