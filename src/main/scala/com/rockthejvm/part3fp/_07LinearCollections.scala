package com.rockthejvm.part3fp

import scala.util.Random

object _07LinearCollections {

    // Seq (Sequence) - its a trait that describes linear collections of objects that are ordered + indexed
    def testSeq(): Unit = {
        val aSequence = Seq(4, 1, 3, 2)
        // we are using apply method of the companion object of seq trait
        // val aSequence_v2 = Seq.apply(1, 2, 3, 4) // same as above
        // Main APIs
        // index an element
        val thirdElement = aSequence.apply(2)    // element 3
        val reversed = aSequence.reverse
        val concatenation = aSequence ++ Seq(5, 6, 7)
        val sortedSequence = aSequence.sorted   // [1, 2, 3, 4]
        // sum of the sequence
        val sum = aSequence.foldLeft(0)((x, y) => x + y)
        val sum_v2 = aSequence.foldLeft(0)(_ + _)  // same as above
        val sum_v3 = aSequence.sum // same as above
        // create a string representation of the sequence
        val stringRep = aSequence.mkString("[", ",", "]")

        // map/flatMap/ filter/for
        val anIncrementedSequence = aSequence.map(_ + 1)    // [5, 2, 4, 3]
        val aFlatMappedSequence = aSequence.flatMap(x => Seq(x, x+1, x+2))  // [4, 5, 6, 1, 2, 3, 3, 4, 5, 2, 3, 4]
        val aFilteredSequence = aSequence.filter(_ % 2 == 0) // [4, 2]
        // since we have map flatMap and filter, so we are eligible for 'for comprehension'

        println("\n--------------Sequence: --------------")
        println("aSequence: " + aSequence)
        println("thirdElement: " + thirdElement)
        println("reversed: " + reversed)
        println("concatenation: " + concatenation)
        println("sortedSequence: " + sortedSequence)
        println("anIncrementedSequence: " + anIncrementedSequence)
        println("aFlatMappedSequence: " + aFlatMappedSequence)
        println("aFilteredSequence: " + aFilteredSequence)
        println("sum: " + sum)
        println("sum_v2: " + sum_v2)
        println("sum_v3: " + sum_v3)
        println("stringRep: " + stringRep)
    }

    // lists
    def testList(): Unit = {
        val aList = List(1, 2, 3)
        // same API as Seq
        val firstElement = aList.head
        val rest = aList.tail
        // prepending(adding element at the beginning) and appending(at the end of the list)
        // element will added that which faces '+' sign
        val aBiggerList = 0 +: aList :+ 4
        // prepending to the list
        // :: is a non empty case class.
        // :: is also the apply method of the same case class
        val prepending = 0 :: aList

        // utility methods
        // create a list with size 5 containing Scala
        val scalaX5 = List.fill(5)("Scala")

        println("\n--------------Lists: --------------")
        println("aList: " + aList)
        println("firstElement: " + firstElement)
        println("rest: " + rest)
        println("aBiggerList: " + aBiggerList)
        println("prepending: " + prepending)
        println("scalaX5: " + scalaX5)
    }

    // ranges
    // they don't contains all the range number, but when processing behaves, as if they did.
    def testRanges(): Unit = {
        val anInclusiveRange: Seq[Int] = 1 to 10 // to is a method in Int type
        val aNonInclusiveRange = 1 until 10 // 10 is not included
        // same Sequence API, we can perform all the above operations
        (1 to 3).foreach(i => println("Scala"))
        // (1 to 3).foreach(_ => println("Scala")) // same as above
        val twoByTwo = anInclusiveRange.map(_ * 2)  // twoByTwo is a sequence of elements
        val twoByTwoList = anInclusiveRange.map(_ * 2).toList   // twoByTwo is a List of elements
        println("anInclusiveRange: " + anInclusiveRange)
        println("aNonInclusiveRange: " + aNonInclusiveRange)
        println("twoByTwo: " + twoByTwo)
    }

    // arrays are mutable
    // all other collections we discussed above are immutable - obtain a new collection when modified
    def testArrays(): Unit = {
        //creating an array with a companion object
        val anArray = Array(1, 2, 3, 4, 5, 6)   // int[] on the JVM
        println("anArray: " + anArray)

        // most Seq API works on Array
        // arrays are not Seqs
        // convert an array into Seq
        val aSequence: Seq[Int] = anArray.toSeq
        println("aSequence: " + aSequence)
        anArray.update(2, 20)   // no new array is allocated
        println("anArray: " + anArray)
    }

    // vectors = fast Seqs fr a large amount of data
    // vectors are linear collection
    def testVectors(): Unit = {
        // creating a vector through a companion object
        val aVector = Vector(1, 2, 3, 4)
        println("aVector: " + aVector)
        // Contains same Seq APIs
    }

    // sets = no duplicates
    //      order not important
    def testSets(): Unit = {
        val aSet = Set(1, 2, 3, 4, 1, 2, 2) // Set(1, 2, 3, 4)
        val setHas5 = aSet.contains(5)  // false
        val anAddedSet = aSet + 5   // Set(1, 2, 3, 4, 5)
        val aRemovedSet = aSet - 3  // Set(1, 2, 4)
        println("aSet: " + aSet)
        println("setHas5: " + setHas5)
        println("anAddedSet: " + anAddedSet)
        println("aRemovedSet: " + aRemovedSet)
    }

    // tuples = groups of vlaues under the same value
    def testTuples(): Unit = {
        val aTuple = ("Bon Jovi", "rock", 1982)
        println("aTuple: " + aTuple)
    }

    // maps
    //  Map() => apply method on the companion object
    //  can accept use two argument tuples
    def testMaps(): Unit = {
        val aTuple = ("Hari", 123456)
        val aPhonebook: Map[String, Int] = Map(("Daniel", 23432489), "Jane" -> 327285) // same as below
        val aPhonebook_v2: Map[String, Int] = Map(
            ("Daniel", 23432489),
            "Jane" -> 327285,    // same as ("Jane", 327285)
            aTuple  // a two argument similar type of tuple
        )
        println("aPhonebook: " + aPhonebook)
        println("aPhonebook_v2: " + aPhonebook_v2)
    }

    def smallBenchmark(): Unit = {
        val maxRuns = 1000
        val maxCapacity = 1000000

        def getWriteTime(collection: Seq[Int]): Double = {
            val random = new Random()
            // times will indexed sequence collection of long type
            val times = for {
                // range, it is also a sequence
                i <- 1 to maxRuns
            } yield {
                // generate a random number until maxCapacity
                val index = random.nextInt(maxCapacity)
                val element = random.nextInt()
                val currentTime = System.nanoTime()
                val updatedCollection = collection.updated(index, element)
                System.nanoTime() - currentTime
            }

            // times.sum * 1.0 convert the datatype to double
            // comput average
            times.sum * 1.0 / maxRuns
        }
        val numberList = (1 to maxCapacity).toList
        val numberVectors = (1 to maxCapacity).toVector
        
        // vectors are fast to work with, when capacity is huge, for small capacity not much difference is there
        println("numberList: " + getWriteTime(numberList))  // 5279078.093 nanoseconds
        println("numberVectors: " + getWriteTime(numberVectors)) // 9359.223 nanoseconds
    }

    def main(args: Array[String]): Unit = {
        testSeq()
        testList()
        testRanges()
        testSets()
        testTuples()
        testMaps()
        // uncomment below to the benchmark between vector vs list
//        smallBenchmark()
    }
}
