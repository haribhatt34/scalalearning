package com.rockthejvm.part2oop

object _07Objects {

    // singleton pattern in Scala
    // object in scala are singletons
    // Instance(object) of Classes are different from the object in Scala

    // type + the only instance of this type
    object MySingleton {
        val aField = 45
        def aMethod(x: Int) = x + 1
    }

    val theSingleton = MySingleton
    val anotherSingleton = MySingleton
    val isSameSingleton = theSingleton == anotherSingleton  // true

    // objects can have fields and methods
    val theSingletonField = theSingleton.aField
    val theSingletonMethodCall = MySingleton.aMethod(99)

    class Person(name: String) {
        def sayHi(): String = s"Hi, my name is $name"
    }

    // Companions = class + object with the same name in the same file
    // [see the symbol on the left pane ahead of class Person and object Person
    // Using companions in code, serves a purpose:
    // 1). In class person, we define fields & methods that related to instances of class Person
    //      whereas, in Object Person, we define fields and members that are not dependent on the instance of class Person
    //      eg. we can define constant for all Persons
    // 2). The class and object can access each other's private fields and methods

    object Person {     // Companion Object
        val N_EYES = 2
        def canFly(): Boolean = false
    }

    // methods and fields in classes are used for instance-dependent functionality
    val mary = new Person("Mary")
    val mary_v2 = new Person("Mary")
    val marysGreeting = mary.sayHi()

    // methods and fields in objects are used for instance-independent functionality - static
    val humansCanFly = Person.canFly()
    val nEyesHuman = Person.N_EYES

    // as seen above, we need to create an instance of Person, to use its members,
    // while we can directly access members of Person object, as its singleton
    // similar to "static", static is not present in Scala

    // equality
    // 1 - equality of reference - usually defined as ==
    //      two instance are equal in terms of reference, if they refer to the same object in memory
    val isSameMary = mary eq mary_v2    // false, different instance
    val isTheSameSingleton = MySingleton eq anotherSingleton    // true
    // 2 - equality of "sameness" - in Java defined as obj1.equals(obj2)
    //          up to us to decide the basis of sameness
    //      by default basis of equality is "equality of reference", but we can override it to
    //          change according to our need.
    val isSameMary_v2 = mary.equals(mary_v2)    // false
    val isSameMary_v3 = mary equals mary_v2     // same as above
    val isSameMary_v4 = mary == mary_v2         // same as above
    val isSameTheSingleton_v2 = MySingleton equals anotherSingleton // true
    
    // object can extend classes
    object BigFoot extends Person("Big Foot")
    
    // Scala application = object + def main(args: Array[String]): Unit
    def main(args: Array[String]): Unit = {
        println(isSameSingleton)
        println(isSameMary)
        println(isTheSameSingleton)
        println(isSameMary_v2)
        println(isSameMary_v3)
        println(isSameMary_v4)
        println(isSameTheSingleton_v2)
    }
}
