package com.rockthejvm.part2oop

object _05AccessModifier {
    
    class Person(val name: String) {
        // protected == access only to inside the class + children classes
        protected def sayHi(): String = s"Hi, my name is $name. "

        // private == only accessible inside the class
        // We can only call this method from within the class, not from the sub class or outside world.
        private def watchNetflix(): String = "I'm binge watching my favorite series..."
    }

    // we have to override with name if we want to it as a field
    //      i.e. override val name
    //      reason: the parent class already have a field name as name
    // For another case it couldn't be override if it was like this:
    //          class Person(name: String)  // only constructor params not a field
    // or keep it as such
    //      class Kid(name:String..) // not a field, just a parameter passed in constructor
    class Kid(override val name: String, age: Int) extends Person(name) {
        // no modifier = "public"
        def greetPolitely(): String =
            // can access protected sayHi(), as it's in a child class
            sayHi() + "I love to play !"

        val hola: String = sayHi()
    }
    
    val aPerson: Person = new Person("Alice")
    val aKid = new Kid("Hari", 5)
    val anotherKid: Person = new Kid("Radha", 4)


    // Complication
    class KidWithMom(override val name: String, age: Int, momName: String) extends Person(name) {

        val mom = new Person(momName)

        def everyoneSayHi(): String =
            // below give error: 
            // reason: we cannot access private sayHi() through any of the instance, except for "this"
            // mom.sayHi()
            
            // but we can do below:
            // reason: sayHi() is inherited from Person to KidWithParents, so we can call sayHi from it's instance
            // i.e. "this"
            this.sayHi()
    }
    
    val aKidWithMom = new KidWithMom("Hari", 48, "Neelam")
    // below gives error:
    // reason: we can't access protected sayHi(), outside of the class it is defined in 
    //          or if not within inherited class
    // aKidWithMom.sayHi()
    
    def main(args: Array[String]): Unit = {
        
        println(aKid.greetPolitely())
        // Compiler warning: sayHi() can't be accessed as a member of anotherKid
        // println(anotherKid.sayHi())
        // println(aKid.sayHi())
        println(aKid.hola)
    }
}
