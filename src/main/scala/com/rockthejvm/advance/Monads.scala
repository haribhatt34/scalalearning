package com.rockthejvm.advance

object Monads {
    
    // Monads = 
    // 1. ability to chain dependent combinations
    // 2. computation or data structures that can wrap existing existing value into a bigger data structure
    //    such a list, option, try such that the flatMap method on those data types uphold the property 1, 2 & 3

    def listStory(): Unit = {
        val aList = List(1, 2, 3)
        val listMultiply = for {
            x <- List(1, 2, 3)
            y <- List(4, 5, 6)
        } yield x * y
        println(listMultiply)

        // for comprehension = chains and maps + flatMap
        val listMultiply_v2 = List(1, 2, 3).flatMap(x => List(4, 5, 6).map(y => x * y))
        println(listMultiply_v2)

        val f = (x: Int) => List(x, x + 1)
        val g = (x: Int) => List(x, 2 * x)
        val pure = (x: Int) => List(x)  // same as List "constructor"

        // Properties on List

        // Property #1: left identity
        // pure(42).flatMap(f) = List(42).flatMap(f) = List(42, 43)
        // f(42) = List(42, 43)
        val leftIdentity = pure(42).flatMap(f) == f(42) // for every x, for every f
        // for every List this property holds true

        // Property #2: right identity
        // aList.flatMap(pure) = List(1, 2, 3).flatMap(pure) = List(1, 2, 3)
        // aList = List(1, 2, 3)
        val rightIdentity = aList.flatMap(pure) == aList    // for every List this property holds true

        // Property #3: associativity
        // list(1, 2, 3).flatMap(f).flatMap(g) = list(1,2, 2,3, 3,4).flatMap(g)
        //                                     = list(1,2, 2,4,     2,4, 3,6,   3,6, 4,8)
        // the above final list can be broken down into three sublist as below.
        // f(1).flatMap(g) = [1,2, 2,4]
        // f(2).flatMap(g) = [2,4, 3,6]
        // f(3).flatMap(g) = [3,6, 4,8]
        // ++ is concatenation here
        // [1,2, 2,4,  2,4, 3,6  3,6, 4,8] = f(1).flatMap(g) ++ f(2).flatMap(g) ++ f(3).flatMap(g)
        // [1,2,3].flatMap(x => f(x).flatMap(g))

        // The above breakdown was to prove below
        val associativity = aList.flatMap(f).flatMap(g) == aList.flatMap(x => f(x).flatMap(g))
        // for every List this property holds true

        // Options are applicable for "For Comprehension" as they contain Map and FlatMap.
        def optionStory(): Unit = {
            val anOption = Option(42)
            val optionString = for {
                lang <- Option("Scala")
                ver <- Option(3)
            } yield s"$lang-$ver"
            // identical
            val optionString_v2 = Option("Scala").flatMap(lang => Option(3).map(ver => s"$lang-$ver"))
            
            val f = (x: Int) => Option(x + 1)
            val g = (x: Int) => Option(2 * x)
            val pure = (x: Int) => Option(x) // same as Option "constructor"
            
            // For any Option and f and g
            
            // Property #1: Left-identity
            // pure(42) = wrap 42 in an opton
            // pure(42).flatmap(f) = Option(42).flatMap(f) = Option(43)
            val leftIdentity = pure(42).flatMap(f) == f(42) // true for anyx, for any f
            
            // Property #2: right identity
            val rightIdentity = anOption.flatMap(pure) == anOption  // true for any Option

            // Property #3: associativity
            // anOption.flatMap(f).flatMap(g) = Option(42).flatMap(f).flatMap(g)
            //                                = Option(43).flatMap(g)
            //                                = Option(86)
            // 
            // anOption.flatMap(x => f(x).flatMap(g)
            // Option(42).flatmap(x => 2*(x+1))
            // Option(42).flatMap(x => 2x + 2)
            // Option(2*42 + 2)
            // Option(86)
            val associativity = anOption.flatMap(f).flatMap(g) == anOption.flatMap(x => f(x).flatMap(g))
            // for any option, f and g
        }
    }

    def main(args: Array[String]): Unit = {
        listStory()
    }
}
