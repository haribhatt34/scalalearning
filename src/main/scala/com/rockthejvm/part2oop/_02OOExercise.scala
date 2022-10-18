package com.rockthejvm.part2oop

object _02OOExercise {

    class Writer(firstName: String = "PlaceHolderAuthorFirstName", lastName: String = "PlaceholderAuthorLastName", val yearOfBirth: Int = 1992) {

        def fullName(): String =
            s"$firstName $lastName"
    }
   
    class Novel(novelName: String = "PlaceHolderName", yearOfRel: Int = 2022, author: Writer = Writer()) {

        // age of author at the time of publishing the novel
        def authorAge(): Int = {
            val birthYear: Int = author.yearOfBirth
            val releaseYear: Int = yearOfRel
            releaseYear - birthYear
        }

        def isWrittenBy(author: Writer): Boolean = {
            this.author == author
        }

        def copy(newReleaseYear: Int): Novel = {
            new Novel(yearOfRel = newReleaseYear)
        }
    }

    val writerHari = new Writer("Hari Shankar", "Bhatt", 1992)
    val novelMyLife = new Novel("My Life", 2022, writerHari)

    /**
     * Exercise #2: an immutable counter class
     * - constructed with an initial count
     * - increment/decrement => NEW instance of counter
     * - increment(n)/decrement(n) => NEW instance of counter
     * - print()
     */

    // We should try to use more immutable classes 
    // + well in distributed environment, as their value don't change by
    //      different threads simultaneously, so they simple to debug
    // + easier to read and understand code
    
    // JVM author's philosophy for getter methods, since no args are passed, omit the args.
    // for actionable methods, do use it, eg. print(), display()

    class Counter (count: Int = 0) {
        def increment(): Counter = 
            new Counter(count + 1)
        // parenthesis can ignored when no args
        def decrement(): Counter =
            if (count == 0)
                this
            else
                new Counter(count - 1)
        def increment(byN: Int): Counter =
            // for case when we have logic in increment()
            // and we want to loop over it, we can do so using recursion
            // increment().increment(n-1)
            // although it is susceptible to SO
            new Counter(count + byN)
        def decrement(byN: Int): Counter =
            if (count - byN <= 0)
                new Counter()
                
            else
                new Counter(count - byN)
        def print(): String=
            s"Current count: $count"
    }
    
    val firstCounter = new Counter()
    val incrementedByOneCounter = firstCounter.increment()
    val decrementedByOneCounter = incrementedByOneCounter.decrement()
    val incrementedByTenCounter = decrementedByOneCounter.increment(10)
    val decrementedByTwentyCounter = incrementedByTenCounter.decrement(20)

    def main(args: Array[String]): Unit = {

        println("Writer's full name: " + writerHari.fullName())
        println("Author's age when publishing novel: " + novelMyLife.authorAge())
        println(novelMyLife.isWrittenBy(writerHari))
        println(novelMyLife.copy(2023).authorAge())
        
        println(firstCounter.print())
        println(incrementedByOneCounter.print())
        println(decrementedByOneCounter.print())
        println(incrementedByTenCounter.print())
        println(decrementedByTwentyCounter.print())
    }
}
