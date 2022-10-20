package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// [2,3,4] = [1] -> [2] -> [3] -> |
abstract class LList[A] {
    // initialize the head of LL
    def head: A
    // return the next node of the LL
    def tail: LList[A]
    // tells if is List empty
    def isEmpty: Boolean
    // add element to the beginning
    // below line is replaced the other because implementation of add method was same in both Empty & Non-Empty List
    //  def add(element: Int): LList
    def add(element: A): LList[A] = new Cons(element, this)

    def map[B](transformer: Transformer[A, B]): LList[B]
}

class Empty[A] extends LList[A] {
    // throwing an exception returns nothing.
    //  if try below, it will crash the JVM
    override def head: A = throw new NoSuchElementException()
    override def tail: LList[A] = throw new NoSuchElementException()
    override def isEmpty: Boolean = true
    override def toString = "[]"

    override def map[B](transformer: Transformer[A, B]): LList[B]  =
        new Empty[B]
}

// Why name non empt list named as Cons.
// It comes from the constructor, earlier used in languages like LISP, etc.
// below 3 lines can be compacted to:
// class Cons(value: Int, next: LList) extends LList {
//    override def head = value
//    override def tail = next
class Cons[A](override val head: A, override val tail: LList[A]) extends LList[A] {
    override def isEmpty: Boolean = false
    override def toString = {
        @tailrec
        def concatenateElements(remainder: LList[A], acc: String): String =
            if (remainder.isEmpty)  acc
            else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

        s"[${concatenateElements(this.tail, s"$head")}]"
    }

    // didn't understand this implementation
    override def map[B](transformer: Transformer[A, B]): LList[B]  =
        new Cons(transformer.transform(head), tail.map[B](transformer))
}

/**
    * Exercise: LL t extension
 *1. Generic trait Predicate[T] with a little method test(t) = boolean
    *2. Generic trait Transformer[A,B] with a method transform(A) => B
    *3. LList:
        *- map(transformer: Transformer[A, B])       => LList[B]
        *- filter(predicate: Predicate[A])           => LList[A]
        *- flatMap(transformer from A to LList[B])   => LList[B]
 *
 *class EvenPredicate extends Predicate[Int]
        *class StringToIntTransformer extends Transformer[String, Int]
 *
 *[1, 2, 3].map(n * 2)            => [2, 4, 6]
        *[1, 2, 3, 4].filter(n % 2 == 0) => [2, 4]
        *[1, 2, 3].flatMap(n => [n, n+1])=> [1,2, 2,3, 3,4]
 *
 */

trait Predicate[T] {
    def test(element: T): Boolean
}

class EvenPredicate extends Predicate[Int] {
    override def test(element: Int) =
        element % 2 == 0
}

trait Transformer[A, B] {
    def transform(value: A): B
}

class Doubler extends Transformer[Int, Int] {
    override def transform(value: Int) = value * 2
}

class DoublerList extends Transformer[Int, LList[Int]] {
    override def transform(value: Int) =
        new Cons[Int](value, new Cons[Int](value + 1, new Empty[Int]))
}

object LListTest {
    def main(args: Array[String]): Unit = {
        val empty = new Empty[Int]
        println(empty.isEmpty)
        println(empty)

        val first3Numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
        println(first3Numbers)
        val first3Numbers_v2 = empty.add(1).add(2).add(3)
        println(first3Numbers_v2)
        println(first3Numbers_v2.isEmpty)

        val someStrings = new Cons("dog", new Cons("cat", new Empty))
        println(someStrings)

        val evenPredicate = new Predicate[Int] {
            override def test(element: Int) =
                element * 2 == 0
        }

        val doubler = new Transformer[Int, Int] {
            override def transform(value: Int) =
                value * 2
        }
        
        val numbersDoubled = first3Numbers.map(doubler)
        println(numbersDoubled)
        
        val numbersNested = first3Numbers.map(new DoublerList)
        println(numbersNested)
    }
}
