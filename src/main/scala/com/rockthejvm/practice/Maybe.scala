package com.rockthejvm.practice

// Maybe contains at most 1 element.
abstract class Maybe[A] {

    def map[B](f: A => B): Maybe[B]
    def filter(predicate: A => Boolean): Maybe[A]
    def flatMap[B](f: A => Maybe[B]): Maybe[B]
}

// empty collection
// MaybeNot equals to nothing
case class MaybeNot[A]() extends Maybe[A] {
    // returns MayBeNot[B] with empty argument constructor
    override def map[B](f: A => B): Maybe[B] = MaybeNot[B]()
    // filter does nothing on empty collection, so return this instance
    override def filter(predicate: A => Boolean): Maybe[A] = this
    // flatMap returns MaybeNot[B] with no args
    override def flatMap[B](f: A => Maybe[B]): Maybe[B]  = MaybeNot[B]()
}

// empty collection - similar to empty list
// Just borrowed by haskell
case class Just[A](val value: A) extends Maybe[A] {
    override def map[B](f: A => B): Maybe[B] = Just(f(value))
    // if predicate satisfy, then return the value, else return MaybeNot
    // here predicate is the name of the function like f above
    override def filter(predicate: A => Boolean): Maybe[A] =
        if (predicate(value)) this
        else    MaybeNot[A]()
    // flatMap here applies f on the value, f(value) returns Maybe[B]
    override def flatMap[B](f: A => Maybe[B]): Maybe[B]  = f(value)
}

object MaybeTest {
    def main(args: Array[String]): Unit = {
        val maybeInt:Maybe[Int] = Just(3)
        val maybeInt2: Maybe[Int] = MaybeNot()
        val maybeIncrementedInt = maybeInt.map(x => x + 1)
        // val maybeIncrementedInt = maybeInt.map(_ + 1) // same as above
        val maybeIncrementedInt2 = maybeInt2.map(_ + 1)
        println(maybeIncrementedInt)
        println(maybeIncrementedInt2)
        
        // MaybeInt currently contains 3, previous increment returned a new List, didn't changed the existing list
        val maybeFilteredInt = maybeInt.filter(x => x % 2 == 0)
        val maybeFilteredInt2 = maybeInt2.filter(_ % 2 == 0)
        println(maybeFilteredInt)
        println(maybeFilteredInt2)
        
        val maybeFlatMapped = maybeInt.flatMap(x => Just(x+1))
        println(maybeFlatMapped)
    }
}