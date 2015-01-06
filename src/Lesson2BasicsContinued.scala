/**
 * Object Lesson2BasicsContinued
 * Basic Scala concepts continued
 * @author Vivan
 */
object Lesson2BasicsContinued {
  def main(args: Array[String]) = {
    // Apply methods
    val newFoo = FooMaker()
    println(Timer.currentCount())

    val bar = Bar("hello")
    println(bar.showFoo())

    println(addOne(3))

    val plusOne = new Add()
    println(plusOne(3))

    // Pattern matching
    val times = 1
    def timesMatch(x: Int): String = x match {
        case 1 => "one"
        case 2 => "two"
        case _ => "some other number"
      }
      println(timesMatch(times))

    // Matching with guards
    def timesMatchWithGuards(x: Int): String = x match {
      case i if x == 1 => "one"
      case i if x == 2 => "two"
      case _ => "something else"
    }
    println(timesMatchWithGuards(8))

    // Matching on type
    def bigger(o: Any): Any = {
      o match {
        case i: Int if i < 0 => i - 1
        case i: Int => i + 1
        case d: Double if d < 0.0 => d - 0.1
        case d: Double => d + 0.1
        case text: String => text + "s"
      }
    }

    println(bigger(1)) // Int
    println(bigger("viv")) // String

    // Case class
    val hp20b = Calculator("hp", "20b")

    // Case classes with pattern matching
    def calcType(calc: Calculator) = calc match {
      case Calculator("hp", "20b") => "financial"
      case Calculator("hp", "48g") => "scientific"
      case Calculator("hp", "30b") => "business"
      case Calculator(someBrand, someModel) => "Unknown type %s %s".format(someBrand, someModel)
    }
    println(calcType(hp20b))
  }
}

class Foo {}

object FooMaker {
  def apply() = new Foo
}

// Objects
object Timer {
  var count = 0

  def currentCount(): Long = {
    count += 1
    count
  }
}

// Companion Objects
class Bar(foo: String) {
  def showFoo(): String = { foo }
}

object Bar {
  def apply(foo: String) = new Bar(foo)
}
// Functions are objects
object addOne extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}

class Add extends Function1[Int, Int] {
  def apply(m: Int): Int = m + 1
}

// Shorthand
class AddS extends (Int => Int) {
  def apply(m: Int): Int = m + 1
}

// Case classes
case class Calculator(brand: String, model: String)
