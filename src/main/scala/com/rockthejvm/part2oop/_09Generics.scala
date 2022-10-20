package com.rockthejvm.part2oop

//noinspection ScalaUnusedSymbol
object _09Generics {

    // "generic" list
    // compare with java : abstract class MyList<A>
    abstract class MyList[A] {
        def head: A
        def tail: MyList[A]
    }

    class Empty[A] extends MyList[A] {
        override def head: A = throw new NoSuchElementException()
        override def tail: MyList[A] = throw new NoSuchElementException()
    }

    class NonEmpty[A](override val head: A, override val tail: MyList[A]) extends MyList[A]

    val listOfIntegers: MyList[Int] = new NonEmpty[Int](1, new NonEmpty[Int](2, new Empty[Int]))

    val firstNumber = listOfIntegers.head
    val adding = firstNumber + 3

    // multiple generic types
    // generic interface named MyMap with type argument "[Key, Value]"
    trait MyMap[Key, Value]

    // generic methods
    object MyList {
        // after using the type argument in the method name,
        // we can use it throughout the method
        def from2Elements[A](elem1: A, elem2: A): MyList[A] =
            new NonEmpty[A](elem1, new NonEmpty[A](elem2, new Empty[A]))
    }

    // calling generic methods
    val first2Number = MyList.from2Elements[Int](1, 2)
    // compiler automatically inferes the generic type, as we are passing integer to the method
    val first2Number_v2 = MyList.from2Elements(1, 2)
    val first2Number_v3 = new NonEmpty(1, new NonEmpty(2, new Empty))
    
    /**
     * Exercise: genericize LList
     * 
     */

    def main(args: Array[String]): Unit = {

    }
}
