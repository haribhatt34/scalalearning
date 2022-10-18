package com.rockthejvm.practice

import scala.annotation.tailrec

// singly linked list
// [2,3,4] = [1] -> [2] -> [3] -> |
abstract class LList {
    // initialize the head of LL
    def head: Int
    // return the next node of the LL
    def tail: LList
    // tells if is List empty
    def isEmpty: Boolean
    // add element to the beginning
    // below line is replaced the other because implementation of add method was same in both Empty & Non-Empty List
    //  def add(element: Int): LList
    def add(element: Int): LList = new Cons(element, this)

    override def toString = super.toString
}

class Empty extends LList {
    // throwing an exception returns nothing.
    //  if try below, it will crash the JVM
    override def head: Int = throw new NoSuchElementException()
    override def tail: LList = throw new NoSuchElementException()
    override def isEmpty: Boolean = true
    override def toString = "[]"
}

// Why name non empty list named as Cons.
// It comes from the constructor, earlier used in languages like LISP, etc.
// below 3 lines can be compacted to:
// class Cons(value: Int, next: LList) extends LList {
//    override def head = value
//    override def tail = next
class Cons(override val head: Int, override val tail: LList) extends LList {
    override def isEmpty: Boolean = false
    override def toString = {
        @tailrec
        def concatenateElements(remainder: LList, acc: String): String =
            if (remainder.isEmpty)  acc
            else concatenateElements(remainder.tail, s"$acc, ${remainder.head}")

        s"[${concatenateElements(this.tail, s"$head")}]"
    }
}

object LListTest {
    def main(args: Array[String]): Unit = {
        val empty = new Empty
        println(empty.isEmpty)
        println(empty)

        val first3Numbers = new Cons(1, new Cons(2, new Cons(3, empty)))
        println(first3Numbers)
        val first3Numbers_v2 = empty.add(1).add(2).add(3)
        println(first3Numbers_v2)
        println(first3Numbers_v2.isEmpty)
    }
}
