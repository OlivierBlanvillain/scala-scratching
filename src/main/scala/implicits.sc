case class Person(name: String)

// companion object stuff is important for implicit resolutions
object Person {

  // and obviously implicit conversions can be fun
  // and it will convert our List[String] -> List[Person]
  implicit def stringToPerson(str: String) = Person(str)

}

def printPerson(p: Person) {
  println(p.name)
}

def printPersons(p: List[Person]) {
  p.foreach(printPerson)
}

printPerson("Baktash")
printPersons(List("A", "B"))