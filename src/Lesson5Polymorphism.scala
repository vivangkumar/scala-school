/**
 * Object Lesson5Polymorphism
 * Scala Polymorphism concepts
 * @author Vivan
 */
object Lesson5Polymorphism {
  def main(args: Array[String]) = {
    // Type variables - Parametric polymorphism
    def drop1[A](l: List[A]) = l.tail
    println(drop1(List(1, 2, 3)))

    // Type inference
    def id[T](x: T) = x
    val x = id(32)
    println(x)

    // Covariance
    val cv: Covariant[AnyRef] = new Covariant[String]
    // Contravariance
    val contrav: Contravariant[String] = new Contravariant[AnyRef]

    // Contravariant
    val getTweet: (Bird => String) = (a: Animal) => a.sound
    // Covariance
    val hatch: (() => Bird) = () => new Chicken
  }
}

// Covariance
class Covariant[+A]
class Contravariant[-A]

class Animal { val sound = "rustle" }
class Bird extends Animal { override val sound = "call" }
class Chicken extends Bird { override val sound = "cluck" }