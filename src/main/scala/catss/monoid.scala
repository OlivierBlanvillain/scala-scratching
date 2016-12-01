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

  /*
  from the book:

  Cats defines Monoid as an extension to another category theory abstraction called semigroup.
Semigroups must have the combine method, therefore in Cats integer addition is defined in IntGroup
(see the the first import). The second import adds a rule for combining Map s. In particular, it specifies
that this operation actually boils down to grouping by keys and combining (here adding) their values.
   */

}
