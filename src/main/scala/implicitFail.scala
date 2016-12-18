
object implicitFail extends App{

  case class Person(name: String)

  object Person {
    implicit def stringToPerson(str: String) = Person(str)
    implicit val people: List[Person] = List("Chris", "Bob")
  }

  def printPerson(p: Person) {
    println(p.name)
  }

  def printPersons(implicit people: List[Person]) {
    people.foreach(printPerson)
  }

  // Func uses implicit people
  printPersons

  // Func uses implicit conversion stringToPerson to go from List[String] to List[Person]
  printPersons(List("A", "B"))

  // Why does this compile? We're asking for a list of persons and theres no Int -> Person conversion around
  printPersons(List(1, 2))

  /*
  This results in an java.lang.IndexOutOfBoundsException: 2. If I change the numbers to 0, 1 it "works", but it prints
  our previously declared implicit people. For some reason with 1, 2 i guess it's trying to reference indexes of the
  implicit list.
   */


}
