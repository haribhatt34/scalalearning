package com.rockthejvm.part2oop

object _04Inheritance {

    class Animal {
        val creatureType = "wild"
        def eat(): Unit = println("nomnomnom")
    }

    // Cat is an Animal
    class Cat extends Animal {
        def crunch() = {
            eat()
            println("crunch, crunch")
        }
    }

    val cat = new Cat

    class Person(val name: String, age: Int) {
        // auxiliary constructor
        def this(name: String) = this(name, 0)
    }

    // must specify super-constructor while extending Person in the class declaration
    // that which of the super class constructor to be called whenever constructor of subclass is called
    class Adult(name: String, age:Int, idCard: String) extends Person(name)

    // overriding
    class Dog extends Animal {

        override val creatureType = "domestic"
        // below is the default suggested by idea intellij
        // override def eat(): Unit = super.eat()
        override def eat(): Unit = println("mmm, I like this bone")

        // popular overridable method
        override def toString: String = "a dog"
    }

    // subtype polymorphism
    // dog declared as an animal is an Dog
    // at runtime, dog's eat will be called. 
    // i.e. most specific method will be called
    val dog: Animal = new Dog
    
    // overloading vs overriding
    class Crocodile extends Animal {
        override val creatureType = "very wild"

        override def eat(): Unit = println("I can eat anything, I'm a croc")
        
        // overloading: multiple methods with the same name, different signatures
        // different signatures:
        //      different argument list (different no. of args + different args types)
        //      + different return type (optional)
        def eat(animal: Animal): Unit = println("I'm eating this animal")
        
        // Static Binding for method overloads:
        // if Dog is defined as 
        //  1). val dog: Animal = new Dog
        //  2). val dog: Dog = new Dog
        // for 1). eat(animal)  is called, 
        // for 2). eat(dog)     is called
        def eat(dog: Dog): Unit = println("I'm eating this dog")
        
        def eat(person: Person): Unit = println(s"I'm eating a human with the name ${person.name}")
        // not a valid overload
        // def eat(): Int = 45
        def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND the dog")
        def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND the dog")

        /**
         Exercise: Which are correct method overloading 
            1). def eat(dog: Dog): Unit = println("eating a dog")
            2). def eat(person: Person): Unit = println(s"I'm eating a human with the name ${person.name}")
            3). def eat(person: Person, dog: Dog): Unit = println("I'm eating a human AND the dog")
            4). def eat(): Int = 45
            5). def eat(dog: Dog, person: Person): Unit = println("I'm eating a human AND the dog")
          
         Solution: 
            1). true
            2). true
            3). true
            4). false
            5). true
         */
    }
    
    val crocodile = new Crocodile
    val dog2: Dog = new Dog
    val person = new Person("yolo", 99)
    // same as above
    // val dog2 = new Dog 

    def main(args: Array[String]): Unit = {
        
        println("cat.eat(): ")
        cat.eat()
        println("cat.crunch(): ") 
        cat.crunch()
        
        // should return what's inside dog.eat()
        println("dog.eat: ")
        dog.eat()
        
        // whenever printing an instance of a class, we are printing the toString method
        println("dog: " + dog)    // same as println(dog.toString)
        
        println("crocodile.eat(dog): ")
        crocodile.eat(dog)
        crocodile.eat(dog2)
        crocodile.eat(person)
        
        // won't work, as dog is declared as an "Animal" type
        // crocodile.eat(person, dog)
        
        crocodile.eat(person, dog2)
        crocodile.eat(dog2, person)
    }
}
