package com.rockthejvm.part1basics

object _06CallByNameVsValue {
    
    // CBV = call by value = arguments are evaluated before function invocation
    def aFunction(arg: Int): Int = arg + 1
    val aComputation = aFunction(23 + 67)

    // CBN = call by name = arguments are passed LITERALLY, evaluated at every reference
    def aByNameFunction(arg: => Int) = arg + 1
    val anotherComputation = aByNameFunction(23 + 67)
    
    def printTwiceByValue(x: Long): Unit = {
        println("By value: " + x)
        println("By value: " + x)
    }

    def printTwiceByName(x: => Long): Unit = {
        println("By name: " + x)
        println("By name: " + x)
    }
    
    // Difference, in when the arguments are evaluated
    // in CBV, before
    // in CVN, 
    //    argument is evaluated everytime it is used
    //    delayed evaluation of the argument
    // CVN particularly usually when we want to delay a use of an argument till late or some condition occurs. 
    
    def infinite(): Int = 1 + infinite()
    def printFirst(x: Int, y: => Int): Unit = println(x)

    def main(args: Array[String]): Unit = {
        println(aComputation)
        println(anotherComputation)
        printTwiceByValue(System.nanoTime())
        printTwiceByName(System.nanoTime())
//        will result in stackoverflow
//        printFirst(infinite(), 42)
        printFirst(42, infinite())
        
    }
}
