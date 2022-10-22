package com.rockthejvm.part2oop

object _13Exceptions {

    // Exception: Special instances that can crash the application
    // always avoid using null
    // below crashes with a NPE
    // val aString: String = null
    // println(aString.length)

    // 1 - throw excpetions - expression like everything in Scala
    // Nothing can assigned to any type. Nothing is proper replacement of any type in Scala
    // commenting below line for running main()
    // val aWeirdValue: Int = throw new NullPointerException   // returns Nothing

    // type Throwable
    //      Error: e.g. StackOverflow Error (Specific error of JVM, infinite recursion),
    //                  Out of Memory OOMError (JVM doesn't have any memory to allocate to the object
    //      Exception: Error in logic, e.g. NPException, NoSuchElementException (NSEException), ...

    def getInt(withExceptions: Boolean): Int =
        // construction contained string, will come in stack trace when JVM throws error
        if (withExceptions) throw new NullPointerException("No int for you")
        else                42

    // the potentialFail will infer to that type which is the local common ancestor of all the result
    // like getInt(true) => Int and case e => String, ... ====> Any
    val potentialFail = try {
        // code that might fail
        getInt(true)
    } catch {
        // good practice to write the most specific exception first
        // as according to compiler, whichever exception is caught first will be executed
        case e: RuntimeException => "54"
        case e: NullPointerException => 35
    } finally {
        // finally section is optional
        // things in finally will be executed, no matter what
        // used to close open resource
        //      - suppose we opened a file, and started working with it, and got expection, since the flow will
        //          break and exception is thrown, we won't be able to close the file, we can do it here.
        // finally doesn't have an impact on the return type, hence Unit return type is used
    }

    class MyException extends RuntimeException {
        // fields or methods
        override def getMessage = "MY EXCEPTION"
    }

    val myException = new MyException
    // Since MyException extends RuntimeException which extends Throwable,
    // we can throw new MyException i.e. myException, just like we can throw new RuntimeException()
    // comment out the below line to run the file
//    val throwingMyException = throw myException

    /**
     * Exercise
     * 1. Crash the application with SOError
     * 2. Crash the application with OOMError
     * // TODO - LLIst part yet to do
     * 3. Find an element matching a predicate in LList
     */

    //1.
    def infiniteRecursion(element: Int): Int =
        if (element == 5) 1 + infiniteRecursion(element)
        else             42

    //2.
    def outOfMemoryHelper(times: Int, acc: String): Int =
        if (times == 0) acc
        outOfMemoryHelper(times-1, acc + acc + acc + acc + acc)

    def main(args: Array[String]): Unit = {
        println(potentialFail)
//        println(infiniteRecursion(5))
        println(outOfMemoryHelper(600000, "Hari Shankar Bhatt"))
    }
}
