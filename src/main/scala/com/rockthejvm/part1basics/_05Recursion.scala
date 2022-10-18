package com.rockthejvm.part1basics

import scala.annotation.tailrec

object _05Recursion {

    def sumUntil(num: Int): Int =
        if (num < 1)    num
        else num + sumUntil(num-1)      // STACK recursion
        // can cause Stack Overflow(SO), when large no. of stack frames are created.

    def sumUntil_v2(num: Int): Int = {
        @tailrec
        def sumUntilTailrec(n: Int, accumulator: Int): Int = {
            if (n < 1)    accumulator
            else sumUntilTailrec(n-1, accumulator + n)      // TAIL recursion = recursive call occurs LAST in the path 
            // In TAIL recursion, scala compiler optimises the recursive calls to use the same stack frame.
            // no further stack frames necessary = no more risk of SO
        }
        sumUntilTailrec(num, 0)
    }

    // stack recursive
    def sumNumbersBetween(a: Int, b: Int): Int = {
        if (a == b)     a
        else if (a > b) 0
        else            a + b + sumNumbersBetween(a+1, b-1)
    }
    println(sumNumbersBetween(8,10))

    // tail recursive
    @tailrec
    def sumNumbersBetweenTailRecursive(a:Int, b:Int, res: Int): Int = {
        if (a > b)          res
        else if (a == b)    sumNumbersBetweenTailRecursive(a+1, b, res + a)
        else                sumNumbersBetweenTailRecursive(a+1, b-1, res + a + b)
    }
    println(sumNumbersBetweenTailRecursive(8,10,0))

    // STACK recursion vs TAIL recursion
    // in stack recursion, after each function call, their value is added to the num and then returned.
    // where in tail recursion, function call is the only thing happening in the end.


    def stringConcatenation(str: String, times: Int): String = {
        def stringConcatenationTailRecursive(times: Int, result: String): String = {
            if (times <= 0) result
            else stringConcatenationTailRecursive(times - 1, result + str)
        }
        stringConcatenationTailRecursive(times, "")
    }
    println(stringConcatenation("Hari", 5))

    // assuming that we'll pass valid input starting from 1.
    def fibTailRec (num: Int): Int = {
        @tailrec
        def fibTailRecHelper(prev: Int, accumulator: Int, times: Int): Int = {
            if (times <= 2)     accumulator
            else                fibTailRecHelper(accumulator, accumulator + prev, times-1)
        }
        
        if (num <=2)    1
        else            fibTailRecHelper(1, 1, num)
    }

    println(fibTailRec(1))
    println(fibTailRec(2))
    println(fibTailRec(3))
    println(fibTailRec(4))
    println(fibTailRec(5))
    println(fibTailRec(6))

    def main(args: Array[String]): Unit = {
        println("sum until 5: " + sumUntil(5))    // 15
        println("sum until 50000000: " + sumUntil_v2(50000000))    // 15
    }
}
