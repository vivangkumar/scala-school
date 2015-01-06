/**
 * Object Lesson1Basics
 * Basic Scala concepts
 * @author Vivan
 */
object Lesson1Basics {
  def main(args: Array[String]) = {
    // Expressions
    println(1 + 1)

    // Values - Bindings of values cannot be changed
    val two = 1 + 1
    println(two)

    // Variables - Bindings can be changed
    var name = "Vivan"
    println(name)

    // Functions
    def addFive(m : Int): Int = m + 5
    val added = addFive(2)
    println(added)

    // Functions without arguments
    def three() = 1 + 2
    println(three())

    // Anonymous Functions
    val addOne = (x: Int) => x + 1
    val addOneToThree = addOne(3)
    println(addOneToThree)

    // Multiple expressions
    def timesTwo(i: Int): Int = {
      println("Hello World")
      i * 2
    }

    val multiTwo = timesTwo(2)
    println(multiTwo)

    // Partial application
    def adder(m: Int, n: Int) = m + n
    val add2 = adder(2, _:Int) // _ is a wildcard
    println(add2(3)) // Apply 3 after applying 2

    // Function currying - new function definition
    def multiply(m: Int)(n: Int): Int = m * n
    println(multiply(2)(3)) // Apply together
    val twoTimes = multiply(2) _
    println(twoTimes(3))

    // Predefined
    val curriedAdd = (adder _).curried
    val curriedAddTwo = curriedAdd(2)
    println(curriedAddTwo(4))

    // Variable length arguments
    def capitalizeAll(args: String*) = {
      args.map {
        arg => arg.capitalize
      }
    }

    println(capitalizeAll("java", "scala", "python"))

    // Classes
    val calc1 = new Calculator1
    println(calc1.add(5, 6))
    println(calc1.brand)

    val calc2 = new Calculator2("HP")
    println(calc2.color)

    val c = new C
    println(c.minc)
    println(c.finc)

    val scientificCalc = new ScientificCalculator("TI")
    println(scientificCalc.log(10.0, 10.0))
    println(scientificCalc.color)

    val circle = new Circle(5)
    println(circle.getArea())

    val bugatti = new Bugatti
    println(bugatti.speed)

  }
}

// No constructor
class Calculator1 {
  val brand: String = "HP"
  def add(m :Int, n: Int): Int = m + n
}

class Calculator2(brand: String) {
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // Instance method
  def add(m :Int,n :Int): Int = m + n
}

// Func vs Methods
class C {
  var acc = 0
  def minc = { acc += 1 }
  val finc = { () => acc += 1 }
}

// Inheritance
class ScientificCalculator(brand: String) extends Calculator2(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

// Method overloading
class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
  def log(m: Int): Double = log(m, math.exp(1))
}

//Abstract Classes
abstract class Shape {
  def getArea(): Int
}

class Circle(r: Int) extends Shape {
  def getArea(): Int = {
    r * r * 3
  }
}

// Traits
trait Car {
  val brand: String
}

trait Shiny {
  val shineRefraction: Int
}

trait Speed {
  val speed: Float
}

class Bugatti extends Car with Shiny with Speed {
  val brand = "Bugatti"
  val shineRefraction = 12
  val speed = 412.23F
}

// Types
trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def del(key: K)
}
