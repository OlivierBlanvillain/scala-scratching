package scalazed

import scalaz._, Scalaz._


object disjunction extends App{

  def queryNextNumber: Exception \/ Long = {
    val source = Math.round(Math.random * 100)
    if(source < 60) \/.right(source)
    else \/.left(new Exception("the generated number is too big"))
  }

  // for syntax that i remember
  val lowishNumbers = for {
    first <- queryNextNumber
    second <- queryNextNumber
  } yield(first + second)

  println("lowish total = " + lowishNumbers)

  // more awesome version
  val operations = List(queryNextNumber, queryNextNumber, queryNextNumber)
  val result = operations.sequenceU
  println("result = " + result)

}
