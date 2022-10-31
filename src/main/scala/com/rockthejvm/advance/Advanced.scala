package com.rockthejvm.advance

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

object Advanced extends App {

    /**
     * Lazy Evaluation
     */
    // Lazy evaluation - an expression is evaluated when it is first used
    lazy val aLazyValue = 2
    // console will print - "I am not lazy" cuz whole block is evaluated
    val valueWithSideEffect = {
        println("I am not lazy")
        43
    }

    // console doesn't print anything yet, as code block is not evaluated
    lazy val lazyValueWithSideEffect = {
        println("I am so very lazy")
        43
    }

    // now as we use lazyValueWithSideEffect val, so it will get evaluated
    //  and print "I am so very lazy" in the console
    val eagerValue = lazyValueWithSideEffect + 1

    // useful in infinite collections

    // "pseudo-collections": Option, Try

    /**
     * "Pseudo-collections": Option, Try
     */
    //  they have access to map, flatMap and filter like normal collections
    // useful in large code bases, when we have unsafe method
    def methodWhichCanReturnNull(): String = "hello, Scala"
    // defensive code against null
    //    if (methodWhichCanReturnNull()) {
    //        .....
    //    }

    // option = "collection" with contain at most one element
    // using Option we can omit above null checks
    //  similar to Optional of Java
    //      if methodWhichCanReturnNull() returns a valid value => Some("hello, Scala")
    //      if methodWhichCanReturnNull() return a null         => None
    //          None is a singleton object, it is a normal, so no risk in accessing illegal members and methods
    val anOption = Option(methodWhichCanReturnNull())

    val stringProcessing = anOption match {
        case Some(str) => "I have obtained a valid string: str"
        case None => "I obtained nothing"
    }

    println("stringProcessing: " + stringProcessing)

    // we can either using Option with pattern matching like above to avoid null check
    // or we can use map, filter and flatMap

    // guards against methods, which throw exceptions
    def methodWhichCanThrowException(): String = throw new RuntimeException
    try {
        methodWhichCanThrowException()
    } catch {
        case e: Exception => "defend against this evil exception"
    }
    // Above increases codebase and multiple cases in catch match makes code complex and unreadable
    // to overcome this Scala uses "Pseudo collection"
    // we use Try to avoid defensiveness with try and catch block
    // and instead use Try()
    // Try() = "collections" with either a value if the code went well, or an exception if the code threw one
    val aTry = Try(methodWhichCanThrowException())
    val anotherStringProcessing = aTry match {
        case Success(validValue) => s"I have obtained a valid string: $validValue"
        case Failure(ex) => s"I have obtained an exception: $ex"
    }

    /**
     * Evaluate something on another thread
     * (asynchronous programming)
     */

    // Future is another Pseudo Collection

    // the below code runs on a separate thread.
    //  now what happens, when we run this application, the main thread finishes
    //  even before aFuture had a chance evaluate below line
    //      println("I have computed a value.")
    // So why is "Loading.." is printing in the console?
    //  because it evaluated before main thread finished, but then since we are putting
    //      a sleep on the aFuture thread, the main threads finishes, before "I had computed a value" could be evaluated
    // In below syntax we can omit Future({...}) by Future{}
    //      as whatever is in {..} will be passed as parameter to Future.apply()
    val aFuture = Future ({     // same as Future.apply()
        println("Loading...")
        // block the current thread for 1000ms
        Thread.sleep(1000)
        println("I have computed a value.")
        67
    })

    // blocking the main thread for 2000ms, so to lets the aFuture thread to complete its processing
    // now everything in aFuture should print on the console
    Thread.sleep(2000)
    
    // future is a "collection" which contains a value after it's evaluated, until then it contains nothing
    // future is composable with map, flatMap and filter
    
    // Future, Try and Option Types are called Monads

    /**
     * Implicits basics
     */
    
    // Use Case #1: implicit arguments
    // implicit is a keyword
    def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
    implicit val myImplicitInt: Int = 46
    // No need to pass an argument to the method call, 
    //      compiler automatically figures out that method takes an implicit arg
    //      and tries to the find of type of Int that it can inject there.
    println(aMethodWithImplicitArgs)    // aMethodWithImplicitArgs(myImplicitInt)
    
    // Use Case #2: implicit conversions
    // use this very carefully
    //      usually we do implicit conversion to add methods to existing types 
    //      over which we don't have any control over the code
    implicit class MyRichInteger(n: Int) {
        def isEven() = n % 2 == 0
    }
    println(23.isEven())
    // how does it works : 
    // compiler check to find an implicit wrapper over this which is an int, and check maybe that class has isEven method 
    //      then call its isEven method on that
    // for above compiler generates below code:
//    class MyRichInteger(n: Int) {
//        def isEven(): Boolean = n % 2 == 0
//    }
//    implicit final def MyRichInteger(n: Int): MyRichInteger = new MyRichInteger(n)
//    println(MyRichInteger(23).isEven())


}
