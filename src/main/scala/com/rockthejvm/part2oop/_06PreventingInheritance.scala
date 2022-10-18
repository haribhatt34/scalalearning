package com.rockthejvm.part2oop

object _06PreventingInheritance {
    
    class Person(name: String) {
        // not want it to extensible, overridden
        final def enjoyLife(): Int = 42
    }
    class Adult(name: String) extends Person(name) {
        // can't override final method
        // override def enjoyLife() = 999
    }
    
    final class Animal
    // final class cannot be inherited/extended
    // class cat extends Animal

    // Scala discourages use of heavy inheritance.
    
    // sealing a type hierarchy = inheritance only permitted inside this file
    sealed class Guitar(nStrings: Int)
    class ElectricGuitars(nStrings:Int) extends Guitar(nStrings)
    class AcousticGuitars extends Guitar(6)
    
    // Good Practice
    // i). no modifier = "not encouraging" inheritance
    //          indicates that the class shouldn't be inherited
    // ii). open modifier = specifically marked for inheritance
    //          non mandatory to use, but good practice,
    //          indicates that the class is open for inheritance/extension
    open class ExtensibleGuitar(nStrings: Int)

    def main(args: Array[String]): Unit = {
        
    }
}
