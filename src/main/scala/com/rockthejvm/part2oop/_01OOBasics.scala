package com.rockthejvm.part2oop

object _01OOBasics {

    // classes
    // constructor argument directly embedded in class declaration
    // class parameter != class field
    // constructor args can be used within class, but outside of it directly
    // but to make a class parameter it's field as well,
    // say 1st parameter name, we can write
    //  val name: String
    //      instead of
    //  name: String
    class Person(val name: String = "Hari", age: Int = 30) {  // constructor signature
        // fields
        val allCaps = name.toUpperCase()

        // methods
        def greet(name: String): String =
            s"${this.name} say: Hi $name. I am $age years old !!!"

        // method overloading: same name, different signature
        def greet(): String =
            s"Hi, my name is $name"

        // auxiliary constructor
        // rarely used in scala
        // reason: the implementation must be another constructor call 
        // solution: instead just use default parameters for situation like below 
        def this(name: String) =
            this(name, 30)
            // will give error, implementation must have this(..)
            // println("hola amigo")
            
        def this() =
            this("Hari", 30)
    }

    val aPerson: Person = new Person("Hari", 30)
    val hari = aPerson.name
    val hariYelling = aPerson.allCaps
    val hariSayHiToRaj = aPerson.greet("Raj")
    val hariSayHi = aPerson.greet()
    val aAnotherPerson: Person = new Person("Hari")
    val hariSayHiToAjay = aAnotherPerson.greet("Ajay") 
    val genericPerson = new Person()
    val hariSayHiToRam = aAnotherPerson.greet("Ram") 

    def main(args: Array[String]): Unit = {
        println(hariSayHiToRaj)
        println(hariSayHi)
        println(hariSayHiToAjay)
        println(hariSayHiToRam)
    }
}
