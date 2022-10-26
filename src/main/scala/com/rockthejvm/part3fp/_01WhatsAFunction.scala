package com.rockthejvm.part3fp

object _01WhatsAFunction {

    // FP: style of programming where functions are "first-class" citizens
    //  - it means we operate with functions as we do with any kind of values in programming
    //  - they can be passed around as argument, return them as result
    //  - using apply method, we can invoke instance of a trait/class, as if we are invoking method
    // Scala is FP lang, while JVM on which Scala is run is made for OO Lang.
    // JVM - objects(instances of classes) were treated as first class citizens.
    //  - All Scala functions are objects 
    //  - Scala uses function traits, up to 22 params
    //  - syntactic sugar function types 
    //  - i.e. Function2[Int, String, Int] { .. } as
    //     as  (Int, String) => Int

    // two type arguments A, B.
    // One as argument type and other as result type
    trait MyFunction[A, B] {
        // takes a value of type A and return value of type B
        def apply(arg: A): B
    }

    // doubler is an instance of MyFunction, defined using an anonymous class
    val doubler = new MyFunction[Int, Int] {
        override def apply(arg: Int) = arg * 2
    }

    val meaningOfLife = 42
    val meaningDoubled = doubler.apply(meaningOfLife)
    // apply method is special here,
    // as it allows the instance of a class to be invoked as method
    // in simple terms, when Instance() is called, it invokes the apply method of the Instance.
    val meaningDoubled_v1 = doubler(meaningOfLife)  // same as above

    /*--- BELOW CODE IS NOT AN EXTENSION OF THE TRAIT MyFunction AND ABOVE CODE-------------------*/
    // function types in Scala
    // In Scala, All functions are instances of family of FunctionX Traits with apply methods

    // function1 takes two type argument, first as parameter type and second as return type
    val doublerStandard = new Function1[Int, Int] {
        override def apply(arg: Int) = arg * 2
    }
    val meaningDoubled_v2 = doublerStandard(meaningOfLife)

    // similarly there are FunctionN with 'n' argument types and 1 return type
    val adder = new Function2[Int, Int, Int] {
        override def apply(v1: Int, v2: Int) = v1 + v2
    }

    val anAddition = adder(3, 4)  // 7

    // using shorthand notation for defining FunctionX, Function4 here
    val aThreeArgFunction = new ((Int, Double, Boolean, Int) => String) {
        override def apply(v1: Int, v2: Double, v3: Boolean, v4: Int):String = "Hello"
    }

    /**
     * Exercise :
     * 1. A function which takes 2 strings and concatenates them
     * TODO: 2. Replace Predicate/Transformer with the appropriate type if necessary
     * 3. Define a function which takes an Int as an argument and return ANOTHER FUNCTION as a result
     */

    // 1
    // if we hover mouse over the variable concatenator, compiler infers it as below =
    // val concatenator: (String, String) => String
    // we call it function value
    val concatenator = new ((String, String) => String) {
        override def apply(firstName: String, lastName:  String)  = firstName + " " + lastName
    }
    val myName = concatenator("Hari Shankar", "Bhatt")

    // 3
    val superAdder = new Function1[Int, Function1[Int, Int]] {
        override def apply(x: Int) = new Function1[Int, Int] {
            override def apply(y: Int) = x + y
        }
    }

    // currying
    // below, we are invoking superAdder with argument 2
    // then, the result of above we are invoking on argument 67
    val adder2 = superAdder(2)
    val anAddition_v2 = adder2(67)
    val anAddition_v3 = superAdder(2)(67)
    
    // def(methods) vs function values
    // def are methods of classes or traits
    // function values are instances of Function1, Function2.... FunctionsN

    val returnSame = new (String => String) {
        override def apply(v1: String)  = v1
    }

    val returnFunctionAsArg = new (Int => String) {
        override def apply(v1: Int) = returnSame("Hari Shankar")
    }

    def main(args: Array[String]): Unit = {
        println(anAddition)
        println(aThreeArgFunction(1, 2, true, 4))
        println(myName)

    }
}
