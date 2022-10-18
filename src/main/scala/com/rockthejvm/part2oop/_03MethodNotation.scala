package com.rockthejvm.part2oop

import scala.language.postfixOps

object _03MethodNotation {
    
    class Person(val name: String, val age: Int, favoriteMovie: String) {
        
        // infix Notation
        // - only available with method with one arg
        // - use modifier infix
        //  mary likes "inceptino"
        //  object method parameter
        infix def likes(movie: String): Boolean = 
            movie == favoriteMovie
            
        infix def +(person: Person): String =
            s"${this.name} is hanging out with ${person.name}"
            
        // every method with a single parameter in < scala 3.x had infix modifier keyword as optional
        // scala 3.x borrowed it as such, better habit to use infix explicitly
        infix def !!(progLanguage: String): String =
            s"$name wonders how can $progLanguage be so cool!"
            
        // prefix notation 
        // i). only available for these 4 unary operators: -, +, ~, !
        // ii). methods should not have any args/parameter
        // iii). maintain the space between - :
        // iv). same structure as below
        def unary_- : String =
            s"$name's alter ego"
            
        // postfix notation
        // i). only for methods with no args/parameters
        // ii). Need to import this => import scala.language.postfixOps
        // iii). discouraged as causes confusion, 
        // iv). deprecated since 2.13
        def isAlive: Boolean = true


        // Apply method 
        // i). special in Scala
        // ii). Allow an object  to be invoked like a function
        //      Eg: val vehicle = new Vehicle
        //          vechicle() => will invoke apply method
        def apply(time: Int): String =
            s"Hi, my name is $name and I really enjoy $favoriteMovie. I've watched it $time times"
            
        // Exercises:
        // infix notation
        infix def +(nickname: String): Person =
            new Person(s"$name, $nickname", age, favoriteMovie)
            
        // prefix notation
        def unary_+ : Person =
            new Person(name, age + 1, favoriteMovie)
    }
    
    val mary = new Person("Mary", 34, "Inception")
    val john = new Person("John", 36, "Fight Club")
    
    val negativeOne = -1

    /**
     * Exercises:
     * - a + operator on the Person class that returns a person with a nickname
     *      mary + "the rockstar" => new Person("Mary the rockstar", same, same)
     * - a UNARY + operator that increases the person's age
     *      +mary => new Person(_, _, age+1)
     * - an apply method with an integer argument
     *      mary.apply(2) => "Mary watched Inception 2 times"
     */

    def main(args: Array[String]): Unit = {
        
        println(mary.likes("Fight Club"))
        // infix notation - for methods with ONE argument
        println(mary likes "Fight Club") // same as above
        
        // "operator" = plain method
        println(mary + john)
        println(mary.+(john)) // same as above
        
        println(2 + 3)
        println(2.+(3)) // same as above
        
        println(mary !! "Scala")
        println(mary.!!("Scala")) // same as above
        
        // prefix position
        println(-mary)
        println(mary.unary_-) // same as above
        
        // postfix notation:
        // only adv is we can omit the . after object 
        println(mary.isAlive)
        println(mary isAlive) // heavily discouraged
        
        println(mary.apply(10))
        println(mary(10)) // same as above
        
        println((mary + "the rockstar").name)
        println((+mary).age)
    }
}
