/**
 * Object Lesson4Compositions
 * Scala Function composition concepts
 * @author Vivan
 */
object Lesson4Composition {
  def main(args: Array[String]) = {
    def f(s: String) = "f(" + s + ")"
    def g(s: String) = "g(" + s + ")"

    // Function Composition
    val fComposeG = f _ compose g _
    println(fComposeG("hello"))

    // andThen
    val fAndThenG = f _ andThen g _
    println(fAndThenG("whee"))

    // Partial Functions
    val one: PartialFunction[Int, String] = { case 1 => "one" }
    println(one.isDefinedAt(1)) // True
    println(one.isDefinedAt(2)) // False

    val two: PartialFunction[Int, String] = { case 2 => "two" }
    val three: PartialFunction[Int, String]= { case 3 => "three" }
    val wildcard: PartialFunction[Int, String] = { case _ => "wildcard" }
    val partial = one orElse two orElse three orElse wildcard
    println(partial(3))

    val extensions = List(PhoneExt("vivan", 109), PhoneExt("ak", 200))
    val filteredExt = extensions.filter { case PhoneExt(name, ext) => ext > 110 }
    println(filteredExt)
  }

  case class PhoneExt(name: String,ext: Int)
}
