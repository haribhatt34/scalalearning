package com.rockthejvm.extras

object miscellaneous {


  //----------------------------------------------------------------------//

  /*******  Val vs Def Functions in Scala ******/
  //  src : https://www.youtube.com/watch?v=mqJ1W6w5ZZM

  // 1. Def functions are more readable in IDE compared to Val functions
  // 2. In Def Functions, IDE gives us hint about the name & type of args
  // 3. In Def Functions, we can use named argument in different orders
  // 4. Def functions are not data/values like val functions
  //      We can't put them inside a Data Structure like List
  //      We can't assign them to a variable
  // 5. We can convert a def functions to a val functions using Eta Expansion
  //      val replicate_v0 = replicate_v4 _ 
  // 6. Better to use Def Function for Top Level APIs.
  //      and it comes to using Val function, it can be converted by compiler automatically in Scala 3
  //      or we may need to use a eta expansion _ after it.
  
  /*
    Val Functions:
    1. named anonymous function
    2. under the hood they are they are a trait with single abstract method (SAM)
    3. In simple terms, a method packaged inside an object.
  */

  def testValSsDef(): Unit = {
    
    // (n: Int, name: String) => List.fill(n)(name).mkString is an anonymous function
    
    val replicate =
      (n: Int, name: String) =>
        List.fill(n)(name).mkString
        
    // same as above
    val replicate_v1: (Int, String) => String =
      (n: Int, name: String) =>
        List.fill(n)(name).mkString

    // same as above (SAM Syntax)
    val replicate_v2: Function2[Int, String, String] =
      (n: Int, name: String) =>
        List.fill(n)(name).mkString

    // same as above
    val replicate_v3: Function2[Int, String, String] =
      new Function2[Int, String, String] {
        def apply(n: Int, name: String): String =
          List.fill(n)(name).mkString
    }

    // For Scala compiler, if we have .apply(), we can omit .apply() and directly use ()
    println(replicate(3, "Hello"))
    println(replicate_v2.apply(3, "Hello"))

    // Function2 above is a trait, which has only one unimplemented or abstract method => SAM(Single Abstract Method) type
    // Whenever we have SAM type in JVM, we can use SAM syntax instead of a standard syntax.
    // ** SAM Type ** //
    trait Printer {
      def print(message: String): Unit
    }

    // Standard syntax for implementation this trait
    val console: Printer = new Printer {
      override def print(message: String): Unit =
        println(message)
    }

    // SAM syntax
    val console_v2: Printer =
      (message: String) => println(message)


    // storing functions in List. We can do it in Scala, not in other languages, because in Scala, Functions are objects
    // listOfFunction: List[Int => Int] = List(<function1>, <function2>, <function3>)
    val listOfFunctions = List(
      (x: Int) => x + 1,
      (x: Int) => x - 1,
      (x: Int) => x * 2
    )
    val valueOfFirstFunction = listOfFunctions(0)(10) // 11
    val valueOfLastFunction = listOfFunctions(2)(10)  // 20
    println(valueOfFirstFunction)
    println(valueOfLastFunction)

    /* scalac -decompile ./target/scala-3.0.0/classes/com/rockthejvm/extras/miscellaneous.tasty

      val listOfFunctions_v2: List[Function1[Int, Int]] =
        List.apply[Function1[Int, Int]](
          ((x: Int) => x.+(1)),
          ((x: Int) => x.-(1)),
          ((x: Int) => x.*(2))
        )
      val valueOfFirstFunction_v2: scala.Int = listOfFunctions.apply(0).apply(10)
    */
  }

  //----------------------------------------------------------------------//
  
  // Def functions
  
  def replicate_v4(n: Int, name: String): String =
    List.fill(n)(name).mkString 
  
  val replicatedName = replicate_v4(name = "Hari", n = 5)
  
  val replicateDefToVal = replicate_v4 _
  // In Scala 3, it is automatically eta expanded.
  val replicateDefToVal_v2 = replicate_v4

  //----------------------------------------------------------------------//
  // syntactic sugar:

  val replicate_v5: Function2[Int, String, String] = (n: Int, name: String) => List.fill(n)(name).mkString
  // same as above - syntactic sugar is used
  val replicate_v6: (Int, String) => String = (n: Int, name: String) => List.fill(n)(name).mkString



  def main(args: Array[String]): Unit = {
    testValSsDef()
  }

}
