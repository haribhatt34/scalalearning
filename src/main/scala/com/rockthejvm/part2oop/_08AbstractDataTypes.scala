package com.rockthejvm.part2oop

object _08AbstractDataTypes {
    
    abstract class Animal {
        val creatureType: String    // abstract        
        def eat(): Unit
        // non abstract fields/method allowed
        // accessor methods: methods without args/parenthesis
        // eligible for overriding with field
        def preferredMeal: String = "anything"
    }
    
    // Abstract classes can't instantiated
    // val anAnimal: Animal = new Animal
    
    // non-abstract classes must implement the abstract fields/methods
    class Dog extends Animal {
        override val creatureType = "domestic"
        override def eat(): Unit = println("crunching this bone")

        // overriding is legal for everything
        // we can override a method with a field
        //      its only possible for accessor like methods - don't have any args & parenthesis
        // reason: my guess would be :
        //              since they don't have any params, we don't have to pass anything
        //              just need to match their return type and override, thats all
        override val preferredMeal = "bones"
    }
    
    val aDog: Animal = new Dog
    
    // traits - defines behaviour
    //      similar to java Interfaces
    //      can have abstract fields/methods
    //      can have constructor args, like abstract classes - added in Scala 3
    //      can have all their methods & fields defined
    trait Carnivore {
        // we can either define below method or leave it for sub classes
        def eat(animal: Animal): Unit
    }
    
    class TRex extends Carnivore {
        override def eat(animal: Animal): Unit = println("I'm a T-Rex, I eat animals")
    }
    
    // practical difference Abstract classes and traits
    // abstract class :
    //      can inherit only from one class, but can inherit from multiple traits
    // trait :
    //      can inherit from multiple traits
    
    trait ColdBlooded
    class Crocodile extends Animal with Carnivore with ColdBlooded {
        override val creatureType = "croc"

        override def eat(): Unit = println("I'm a croc, I just crunch stuff")
        override def eat(animal: Animal): Unit = println("croc eating animal")
    }
    
    // Philosophical Difference
    //  abstract classes are THINGS
    //     abstract class or a class in general is description of a thing, like Animal class, crocodile class
    //  traits are BEHAVIOURS
    //     trait describes a behaviour, what that object is supposed to do
    
    /*
    Data type:
        scala.Any
            scala.AnyRef
                All classes we write including String, List, Set..
                    scala.Null (the null reference)
            scala.AnyVal
                Int, Unit, Boolean, Char, Float...
    
                        scala.Nothing
    */
    
    // null reference - to point absence of a value
    //      null is a replacement for a null type
    val aNonExistentAnimal: Animal = null
    
    // nothing is replacement for everything
    //      nothing is a replace for any type
    // we cannot directly instantiate anything scala.nothing type
    // below rhs return scala.Nothing, the only expression
    val anInt: Int = throw new NullPointerException()
    
    // below AnyRef is implicitly used by the compiler, even if we don't use it.
    class MyThing extends AnyRef
    
    def main(args: Array[String]): Unit = {
        
    }
}
