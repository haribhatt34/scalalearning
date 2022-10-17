package com.rockthejvm.part1basics

import scala.annotation.tailrec

object _03Functions {

    // function = reusable piece of code that you can invoke with some/no argument and return a result
    def aFunction(firstName: String, lastName: String): String = {
        firstName + " " + lastName
    }
    def aFunctionInOneExpression(a: Int, b: Int): Int =
        a + b
    def noArgumentFunctionOneRepresentation(): Double = 3.14
    def noArgumentFunctionOtherRepresentation: Double = 2.718

    // function invocation
    val aFunctionInvocation = aFunction("Hari Shankar", "Bhatt")
    val addTwoNumbers = aFunctionInOneExpression(5, 6)
    val pi = noArgumentFunctionOneRepresentation()
    val euler = noArgumentFunctionOtherRepresentation

    // recursive function - stack recursive
    // see the symbol at the left pane
    def factorialStackRec(a: Int): Int = {
        if (a <= 1)         1
        else                a * factorialStackRec(a-1)
    }
//    println(factorialStackRec(5))   //120

    // recursive function - tail recursive
    // see the symbol at the left pane
    /*
    ftr(5, 1)
    ftr(4, 5)
    ftr(3, 20)
    ftr(2, 60) =
    ftr(1, 120)
    */
    @tailrec
    def factorialTailRec(a: Int, res: Int): Int = {
        if (a <= 1)     res
        else factorialTailRec(a-1, res * a)
    }
    println(factorialTailRec(5, 1))

    def stringConcatenation(str: String, times: Int): String = {
        if      (times == 0)    ""
        else if (times == 1)    str
        else    str + stringConcatenation(str, times-1)
    }
    println(stringConcatenation("Hari", 5))

    // pure function :  i). deterministic-returns same output every time.
    //                  ii) has no side effect  - doesn't perform I/O
    //                                          - doesn't change global variables
    //                                          - doesn't change outside world

    // pure function
    def isEven(num: Int): Boolean = {
        num % 2 == 0
    }

    // side effects:
    def randomGen(aInt: Int): Int = {
        val rand = new scala.util.Random
        rand.nextInt(aInt)
    }
    println(randomGen(5))

    // another side effects :
    def aVoidFunction(aString: String): Unit = {
        println(aString)
    }
    // also side effects:
    def doubleTheString(aString: String): String = {
        aVoidFunction(aString)
        aString + aString
    }
    println(doubleTheString("Hari"))

    /** Side effect is discouraged in Scala or in funcation programming language **/

    def aBigFunction(num: Int): Int = {
        //Big function implemented using smaller auxiliary functions
        def aSmallFunction(a: Int, b: Int): Int = a + b

        aSmallFunction(num, num+1)
    }
    println(aBigFunction(5))

    /*** Exercise ***/
    def greeting(name: String, age: Int): String =
        "Hi, my name is " + name + " and I am " + age + " years old"

    def fibonacci(num: Int): Int = {
        if (num < 2)       num
        else fibonacci(num - 1) + fibonacci(num - 2)
    }

    @tailrec
    def checkPrimeNumbersInRange(start: Int, end: Int): Unit = {

        def isPrime(num: Int): Boolean = {

            @tailrec
            def isDivisible(i: Int): Boolean = {
                if (i < 2) false
                else if (num % i == 0) true
                else isDivisible(i - 1)
            }

            if (isDivisible(num / 2)) false
            else true
        }

        if (start <= end) {
            println("Is " + start + " prime ? " + isPrime(start))
            checkPrimeNumbersInRange(start + 1, end)
        }
    }

    def main(args: Array[String]): Unit = {
        println(greeting("Hari", 30))
        val num: Int = 6
        println(num + "th fibonacci number is " + fibonacci(num))   // 8
        checkPrimeNumbersInRange(2, 7)
    }
}
