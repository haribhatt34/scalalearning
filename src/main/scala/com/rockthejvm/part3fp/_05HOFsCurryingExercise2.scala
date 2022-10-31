package com.rockthejvm.part3fp

object _05HOFsCurryingExercise2 {

    /**
     * Exercise:
     * // convert a curried function to non-curried function and vice-versa
     * 1. toCurry(f: (Int, Int) => Int): Int => Int => Int
     *    fromCurry(f: (Int => Int => Int)): (Int, Int) => Int
     *
     * 2. compose(f, g) => x => f(g(x))
     *    andThen(f, g) => x => g(f(x))
     */

    // 2.
    // generalizing the types
    def toCurry[A, B, C](f: (A, B) => C): A => B => C = {
    // def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
        x => y => f(x, y)
    }

    //currying = HOFs returning function instances
    val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
    val add3: Int => Int = superAdder(3)
    val invokeAdd3 = add3(100)
    val invokeSuperAdder = superAdder(3)(100) // same as above

    // using toCurry:
    // below will not work with Generic types, as with generics types, we haven't defined '+' on them.
    // val superAdder_v2 = toCurry((x, y) => x + y)
    val superAdder_v2 = toCurry[Int, Int, Int]((x, y) => x + y)
    val superAdder_v3 = toCurry[Int, Int, Int](_ + _) // same as above

    def fromCurry[A, B, C](f: A => B => C): (A, B) => C = {
        (x, y) => f(x)(y)
    }

    val simpleAdder = fromCurry(superAdder_v3)

    // 3.
    // writing the composition in a generalized way
    def composition[A, B, C](f: B => C, g: A => B): A => C = {
//    def composition(f: Int => Int, g: Int => Int): Int => Int = { // same as above, but in Int types instead of generics
        x => f(g(x))
        // (x: Int) => f(g(x)) // same as above
        // we can skip the type of x, as compiler can judge it from the return type of method. i.e. Int => Int
    }
    val incrementer = (x: Int) => x + 1
    val doubler = (x: Int) => x * 2
    val composedApplication = composition(incrementer, doubler)
    
    // below are the method from Scala standard library with similar functionality, when applied on incrementer
    val doubleThenIncrement = incrementer.compose(doubler)
    val incrementThenDouble = incrementer.andThen(doubler)
    simpleAdder.curried
    
    def andThen[A, B, C](f: A => B, g: B => C): A => C = {
        x => g(f(x))
    }
    val aSequencedApplication = andThen(incrementer, doubler)

    def main(args: Array[String]): Unit = {
        println("simpleAdder: " + simpleAdder(3,4))
        println("composedApplication: " + composedApplication(10))
        println("composedApplication_v2: " + aSequencedApplication(10))
        println("incrementer.compose(doubler): " + doubleThenIncrement(10))
        println("incrementer.compose(doubler): " + incrementThenDouble(10))
    }
}
