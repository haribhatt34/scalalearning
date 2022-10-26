package com.rockthejvm.part3fp

import scala.annotation.tailrec

object _03HOFsCurrying {

    /* Beautifully explained the apply method: https://stackoverflow.com/questions/9737352/what-is-the-apply-function-in-scala */

    //---------------------------------------------------------------------------//
    // below example is unrelated to HOF- just to understand apply method in def
    // another self written example:
    def randomDef(a: Int, b: Int, c: Int, d: Int, e: Int): Int => Int =
        e => a / b - c * d + e


    // _ => a + b   // same as above
    // here we don't have any apply method in the randomDef, but still .apply method applies to the randomDef
    // hover over .apply() => "Apply the body of this function to the argument"
    val testTest = randomDef(1, 2, 3, 4, 5).apply(10)
    val testTest_v2 = randomDef(1, 2, 3, 4, 5)(10) // same as above

    def isEven: Int => Boolean = ???
    //---------------------------------------------------------------------------//

    // Higher Order Functions (HOFs)
    // functions that use other functions either as argument or as result

    // below is a composite function type -
    // 1. takes a function type as an argument
    // ||----------------------- Argument Type ---------------------------||---------------- Lambda Expression ------------||
    // ||----------------------- Function Type ----- => ---Result Type----||-----------------Lambda Expression ------------||
    //      (Int,            (Int => Int))           =>      Int          =     (x,                             func)                       =>      x + 1
    // 1st arg type: Int   2nd arg type: Function   return/result type: Int     apply method 1st arg name: x    apply method 2nd arg name: func     return/result
    val aHof: (Int, (Int => Int)) => Int = (x, func) => x + 1

    // 2. returns a function type as an argument
    // ||----------------------- Argument Type ---------------------------||--------------------- Lambda Expression --------------||
    //      Int,            =>      (Int => Int)                          =     x                   =>      (y => y + 2 * x)
    // 1st arg type: Int        return/result type: Function type: Int      apply method only arg: x        return/result (a Function)
    val anotherHof: Int => (Int => Int) = x => (y => y + 2 * x)

    // NOTE:
    // how to find the functions args types and return/result type
    // - look for the outermost opening and closing braces and then locate '=>'
    //

    // quick exercise
    val superfunction:  (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (x, func) => (y => x + y)
    //                  (Int, (String, (Int => Boolean)) => Int) => (Int => Int)    =       (x, func)                  =>   (y => x + y)
    //                  (-----------function arguments---------) => (result type)  (apply method args name is x & func)     (result is another function)
    // Question: Why does result in right most above contains only x & y and not z, how so?
    // Answer:   Since the result is another function, from an Int => Int, we can have at most two different type of integer, one in input (as apply method argument), and other in RHS.
    //              we can assume y as something passed to the apply method as argument, while we already had x previously.

    // something trying out myself
    val intToBoolean: Int => Boolean = (x:Int) => true
    // as in above we are explicitly defining the function args type and result type, we can skip them in the lambda expression, as below
    val intToBoolean_v2: Int => Boolean = x => true // same as above
    val name: String = "hari"
    // below name and func are arguments name of apply method
    // but for rightmost result i.e. we need to use something from the argument or something concrete, we cannot give some random value
    val stringToIntToBoolean: (String, (Int => Boolean)) => Int = (name, func) => 42
    val intToStringToIntToBoolean: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = (anyValue, func) => (y => anyValue + y)

    // more examples
    // we want to apply function f, 'n' times over 'x'
    // f(f(f(f(....(f(x)))), n times.
    @tailrec
    // below param 'x: Int' is acting as an accumulator, we are performing f(x) in that place and in the end we return that value
    // try on rough to understand the algo better.
    def nTimes(f: Int => Int, n: Int, x: Int): Int =
        if (n <= 0) x
        else nTimes (f, n-1, f(x))

    val plusOne = (x: Int) => x + 1
    val tenThousand = nTimes(plusOne, 10000, 0)

    /*  nTimesv2    = ntv2
        po          = plusOne
    
    ntv2(po, 3) = 
    (x: Int)    =>  ntv2(po, 2)(po(x)) = po(po(po(x)))
    // 4. Now, ntv2(po, 2) when takes po(x) will return po(po(po(x)))

    ntv2(po, 2) =
    (x: Int)    =>  ntv2(po, 1)(po(x)) = po(po(x))
    // 3. Now, ntv2(po, 1) when takes po(x) will return po(po(x))

    ntv2(po, 1) =   
    (x: Int)    =>  ntv2(po, 0)(po(x)) = po(x)
    // 2. Now, ntv2(po,0) when take po(x) will return po(x)
    //      hence ntv2(po, 1) = po(x)

    ntv2(po, 0) = (x: Int) => x
    //1.the above line indicates that function instance ntv2(pv, 0) when take x returns x
    //  i.e. return whatever it takes

    */
    // nTimes_v2 have two parameters 1st: f & 2nd: n-1, and return/result a function of type (Int => Int), see declaration
    def nTimes_v2(f: Int => Int, n: Int): Int => Int =
        // in the if condition it is clear that nTimes_v2 return a function of type (Int => Int) as x itself is a Int
        if (n <= 0) (x: Int) => x
        // in the else condition, we again have to return a function of type (Int => Int)
        //      (x: Int) => nTimes_v2(f, n-1)(f(x))
        //  now decoding nTimes_v2(f, n-1)(f(x)) :
        //      nTimes_v2 itself takes two args f & Int those are f & n-1 below
        //      and nTimes_v2 return type is a function i.e. (Int => Int)
        //      so, we when we invoke this def. as nTimes_v2, it will return (Int => Int), but we need an Int as return type
        // TODO: DOUBT  i)  how are we using apply method here &
        //              ii) and why are we passing only a single f(x) as an argument ?
        //              iii). are we currying here, compare with superAdder
        //          
        // else (x: Int) => nTimes_v2(f, n-1).apply(f(x))
        else (x: Int) => nTimes_v2(f, n-1)(f(x))

    // below expression gives SO error, because of lots of calls
    // val plusTenThousand = nTimes_v2(plusOne, 10000) // po(po(po(po........ 10,000 times.
    // as above nTimes_v2 is stack recursive vs nTimes which is tail recursive.
     val plusTenThousand = nTimes_v2(plusOne, 1000)
    val tenThousand_v2 = plusTenThousand(0)
    
    //currying = HOFs returning function instances
    val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
    val add3: Int => Int = superAdder(3)
    val invokeAdd3 = add3(100)
    val invokeSuperAdder = superAdder(3)(100)   // same as above
    
    // curried methods = methods with multiple args list
    // eta expansion - discussed in Advance Scala
    def curriedFormatter(fmt: String)(x: Double): String = fmt.format(x)
    val standardFormat: (Double => String) = curriedFormatter("%4.2f")  // equivalent to (x: Double) => "%4.2f".format(x)
    val preciseFormat: (Double => String) = curriedFormatter("%10.8f")  // equivalent to (x: Double) => "%10.8F".format(x)
    
    // how that format is working:
    val checkStr: String = "%1.20f".format(Math.PI)

    def main(args: Array[String]): Unit = {
        println("tenThousand: " + tenThousand)
        println("testTest: " + testTest)
        println("testTest_v2: " + testTest_v2 )
        println("tenThousand_v2: " + tenThousand_v2)
        println("standardFormat: " + standardFormat(Math.PI))
        println("preciseFormat: " + preciseFormat(Math.PI))
        println("checkStr: " + checkStr)
    }
}
