package catss

import cats.kernel.Monoid
import cats.implicits.catsKernelStdMonoidForString
import cats.instances.int.catsKernelStdGroupForInt
import cats.instances.map.catsKernelStdMonoidForMap

object monoid extends App{

  // ez pz
  val result = Monoid[String].combineAll(List("a", "b", "c"))
  println("result = " + result)

  // holy awesome
  val scores = List(Map("Chris" -> 10, "Kat" -> 20, "Ruth" -> 50), Map("Chris" -> 9001))
  val totals = Monoid[Map[String, Int]].combineAll(scores)
  println("totals = " + totals)

}
