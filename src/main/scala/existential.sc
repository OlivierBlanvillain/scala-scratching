
// Existential types allow you to specify only part of the type signature you care about
def printContents(list: List[_]): Unit ={
  list.foreach(println)
}

val x = List(1, 2, 3)
val y = List("a", "b")

printContents(x)
printContents(y)