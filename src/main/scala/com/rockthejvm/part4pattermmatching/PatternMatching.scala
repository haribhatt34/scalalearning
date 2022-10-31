package com.rockthejvm.part4pattermmatching

object PatternMatching {

    // Pattern matching is available mostly for case classes
    //      to make it work with the normal class, lots of work needs to be done.
    // pattern matching is an expression, like everything in Scala
    // switch expression
    val anInteger = 55
    val order = anInteger match {
        case 1 => "first"
        case 2 => "second"
        case 3 => "third"
        case _ => anInteger + "th"  // default case
    }

    // PM can deconstruct a data structure into its constituent parts
    case class Person(name: String, age: Int)
    val bob = Person("Bob" , 43)    // same as Person.apply("Bob", 43)

    // Case class decomposition using pattern matching
    val personGreeting = bob match {
        case Person(n, a) => s"Hi my name is $n and I am $a years old."
        case _  => "Something else"
    }

    // tuples deconstruction/decomposition
    val aTuple = ("Bon Jovi", "Rock", 1990)
    val bandDescription = aTuple match {
        // if the 'aTuple' matches the below pattern, we want to use its constituents parts as band & genre respectively
        // if we hover over the band, genre, year below, the type is already inferred by the compiler
        case (band, genre, year) => s"$band belongs to the genre $genre, formed in $year"
        case _ => "I don't know what you're talking about"
    }
    
    // lists decomposing
    val aList = List(1, 2, 4)
    val  listDescription = aList match {
        case List(_, 2, _) => "List containing 2 on its second position"
        // below is not mandatory
        //  if underscore/default not provided compiler will throw a MatchError
        case _ => "unknown list"
    }
    
    // Pattern matching evaluates all cases in sequence.
    // below will evaluates the first case
    val aList_v2 = List(1, 2, 4)
    val listDescription_v2 = aList_v2 match {
        case _ => "unknown list"
        case List(_, 2, _) => "List containing 2 on its second position"
    }
    def main(args: Array[String]): Unit = {
        println(order)
        println(personGreeting)
        println(bandDescription)
        println(listDescription)
        println(listDescription_v2)
    }
}
