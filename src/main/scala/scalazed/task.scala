package scalazed

import scalaz.concurrent.Task

object task extends App{

  def performAction(num: Int): Unit ={
    println(s"The task ${num} is executing in ${Thread.currentThread().getName}")
  }

  // Task.now lifts a value to a task, executing in current thread
  val result1 = Task.now{
    performAction(1)
  }

  // Schedules execution in a thread pool, wont run until manually started.
  // It is a pure computation without side-effects
  val result2 = Task{
    // doesn't run
    performAction(2)
  }

  // lazy task, guaranteed to be stack safe so good for recursive algos
  val result3 = Task.delay{
    println("i am not lazy")
    performAction(3)
  }

  println("i am printed before result 3's body because it's lazy")
  println(result3.unsafePerformSync)


}
