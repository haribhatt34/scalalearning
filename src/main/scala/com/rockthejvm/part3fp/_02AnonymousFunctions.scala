package com.rockthejvm.part3fp

object _02AnonymousFunctions {

    // Function instances: instances of FunctionN traits which comes in Scala language standard libraries
    val doubler: Int => Int = new Function1[Int, Int]:
        override def apply(x: Int) = x * 2

    // lambdas = anonymous function instances
    val doubler_v2: Int => Int = (x: Int) => x * 2  // same as above
    val doubler_v3 = (x: Int) => x * 2  // same as above

    // new Function(Int, Int, In] { override def apply...}
    val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y

    // zero args functions
    // () => not Unit, but Zero arg function
    val justDoSomething: () => Int = () => 45
//    val anInvocation = justDoSomething()

    // alternative syntax with curly braces mostly used in Production
    val stringToInt = { (str:String) =>
        // implementation : code block
        // can define local variables inside as its a code block
        // mostly used when lambda has complex implementation
        str.toInt
    }

    // same as above
    val stringToIntBoring = (str: String) => {
        // code block str.toInt
    }

    // type inference
    // compiler can automatically infer the function type as well as argument types
    // since we have defined function type already, compiler can automatically deduce the argument type
    val doubler_v4: Int => Int = x => x * 2
    val adder_v2: (Int, Int) => Int = (x, y) => x + y

    // shortest lambdas
    val doubler_v5: Int => Int = _ * 2  // same as x = x * 2
    // each underscore is a different argument, you can't reuse them
    val adder_v3: (Int, Int) => Int =  _ + _   // same as (x, y) => x + y

    /**
     * Exercise:
     * TODO: 1. Replace all FunctionN, instantiations with lambdas in LList implementation
     * 2. Rewrite the "special" adder from WhatsAFunction using lambdas.
     */

    //2 .
    val superAdder = new Function1[Int, Function1[Int, Int]] {
        override def apply(x: Int) = new Function1[Int, Int] {
            override def apply(y: Int) = x + y
        }
    }

    // see how doubler_v3 is derived from doubler in top
    val superAdder_v2 = (x: Int) => (y: Int) => x + y
    // currying
    val adding2 = superAdder_v2(2)                  // result into another function as (y: Int) => 2 + y
    val addingInvocation = adding2(43)              //45
    val addingInvocation_v2 = superAdder_v2(2)(43)  //same as above

    def main(args: Array[String]): Unit = {
        // invoking justDoSomething function instance
        // internally call apply method
        // just like calling justDoSomething.apply()
        println(justDoSomething())  // 45

        // justDoSomething is an function value, prints its address/Ref something
        println(justDoSomething) // com.rockthejvm.part3fp._02AnonymousFunctions$$$Lambda$18/0x0000000800092040@26a7b76d
        println(doubler_v5(10))
    }
}
