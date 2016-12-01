package catss

object functor extends App{

  case class Cell[A](value: A){
    def map[B](f: A => B): Cell[B] = Cell(f(value))
  }

  // Now we have defined map, can now import Cats functor and get lots of things for free-ish
  import cats.Functor

  implicit val cellFunctor: Functor[Cell] = new Functor[Cell] {
    override def map[A, B](fa: Cell[A])(f: (A) => B): Cell[B] = fa.map(f)
  }

  // Lets play with option
  import cats.implicits.catsStdInstancesForOption
  val maybeName = Option("Chris")

  println(Functor[Option].map(maybeName)(_.toUpperCase))

  // lift can transform an A=>B to F[A] => F[B]
  def greet(name: String) = s"Hello $name"
  val weCanBeLifted = Functor[Option].lift(greet)
  println(weCanBeLifted(maybeName))

  // product takes F[A] applying A=>B returning F[A, B]
  println(Functor[Option].fproduct(maybeName)(greet))

  // and you can compose functors
  import cats.implicits.catsStdInstancesForList
  val optUsers = List(Some("Chris"), None, Some("Pepper"))
  val listOptionFunctor = Functor[List].compose(Functor[Option])
  println(listOptionFunctor.map(optUsers)(greet))

}
