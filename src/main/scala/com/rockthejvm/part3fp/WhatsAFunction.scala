package com.rockthejvm.part3fp

object WhatsAFunction {

    // FP: style of programming where functions are "first-class" citizens
    // JVM

    // two parameters A, B. One as argument type and other as result type
    trait MyFunction[A, B] {
        def apply(arg: A): B
    }

    val doubler = new MyFunction[Int, Int] {
        override def apply(arg: Int) = arg * 2
    }

    val meaningOfLife = 42
    val meaningDoubled = doubler.apply(meaningOfLife)
    // apply method is special here,
    // as it allows the instance of a class to be invoked as method
    // in simple terms, when Instance() is called, it invokes the apply method of the Instance.
    val meaningDoubled_v1 = doubler(meaningOfLife)  // same as above
    
    // function types
    val doublerStandard = new Function1[Int, Int] {
        override def apply(arg: Int) = arg * 2
    }
    val meaningDoubled_v2 = doublerStandard(meaningOfLife)
    
    // In Scala, All functions are instances of FunctionX with apply methods
    
    // 1st two arguments
    val adder = new Function2[Int, Int, Int]:
    
    

    def main(args: Array[String]): Unit = {

    }
}
