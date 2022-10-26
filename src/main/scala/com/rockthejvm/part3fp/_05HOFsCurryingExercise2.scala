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

    def adder(x: Int, y: Int): Int =
        x + y

    def toCurry(f: (Int, Int) => Int): Int =
        52

    val test: ((Int, Int) => Int) = (x: Int, y: Int) => x + y

//    def toCurry(f: (Int, Int) => Int): Int => Int => Int = {
//
//    }

    def main(args: Array[String]): Unit = {
        println(test)
    }
}
