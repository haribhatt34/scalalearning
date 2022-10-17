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
    
    // STACK recursion vs TAIL recursion
    // in stack recursion, after each function call, their value is added to the num and then returned.
    // where in tail recursion, function call is the only thing happening in the end.

    def main(args: Array[String]): Unit = {
        println(sumUntil(5))    // 15
        println(sumUntil_v2(50000000))    // 15
    }
}
