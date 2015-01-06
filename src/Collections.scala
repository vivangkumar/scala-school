/**
 * Object Collections
 * Scala Collection concepts
 * @author Vivan
 */
object Collections {
  def main(args: Array[String]) = {
    // Lists
    val numbers = List(1, 2, 3)
    // Sets - no duplicates
    val set = Set(2, 5, 9)
    // Tuples
    val hostPort = ("localhost", 80)
    // Accessing tuples
    println(hostPort._1)

    // Tuple special sauce
    val specialTuple = "localhost" -> 80
    println(specialTuple._2)

    // Maps
    val newMap = Map("foo" -> "bar")
    // Nested Maps
    val nestedMap = Map("foo" -> Map("bar" -> "name"))

    // Function in Map
    def timesTwo(x: Int): Int = x * 2
    val funcMap = Map("timesTwo" -> { timesTwo(_) })
    val func = funcMap.get("timesTwo")

    // Check if timesTwo is defined, get the value and pass an argument
    val result = if(func.isDefined) {
      func.get
    }
    println(result)

    // getOrElse
    val newResult = funcMap.getOrElse("timesTwo", "No key")
    println(newResult)

    // Functional combinators
    // map
    println(numbers.map((i: Int) => i * 2))

    // Foreach - like map but nothing is returned
    numbers.foreach((i: Int) => i * 2)

    // Filter
    numbers.filter((i: Int) => i % 2 == 0)

    def isEven(i: Int): Boolean = i % 2 == 0
    val evenNumbers = numbers.filter(isEven _)
    println(evenNumbers)

    // Zip
    val zipped = numbers.zip(List("a", "b", "c"))
    println(zipped)

    // Partition
    val num = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val numPartition = num.partition(_ % 2 == 0)
    println(numPartition)

    // Find
    val numFind = num.find((i: Int) => i > 5)
    println(numFind)

    // Drop
    val numDrop = num.drop(5)
    println(numDrop)

    // Drop While
    val numDropWhile = num.dropWhile(_ % 2 != 0)
    println(numDropWhile)

    // Fold left
    val numFold = num.foldLeft(0)((m: Int, n: Int) => m + n )
    println(numFold)

    // Flatten
    val nestedNum = List(List(1, 2, 3, 4), List(5, 6, 7))
    val flatNum = nestedNum.flatten
    println(flatNum)

    // Generalized functional combinators
    def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
      numbers.foldRight(List[Int]()) { (x: Int, xs: List[Int]) =>
        fn(x) :: xs
      }
    }
    println(ourMap(numbers, timesTwo(_)))
  }
}
