package com.rockthejvm.part1basics

import java.time.Year

object _08StringOps {

    val aString: String = "Hello, I am learning Scala"

    // string functions
    val secondChar = aString.charAt(1)      // e
    val firstWord = aString.substring(0, 5) // Hello
    val words = aString.split("")   // Array("Hello", "I", "am", "learning", "Scala"
    val startWithHello = aString.startsWith("Hello")    // true
    val allDashes = aString.replace(' ', '-')
    val allUpperCase = aString.toUpperCase()
    val allLowerCase = aString.toLowerCase()
    // length is an accessor, so it doesn't have any ()
    val nChars = aString.length
    
    // other functions
    val reversed = aString.reverse
    // first n chars from String, alternative to substring, no need to check the indices
    val aBunchOfChars = aString.take(5)
    val bString = "3"
    val parseNumberFromString = bString.toInt

    // interpolation
    val name = "Alice"
    val age = 30
    val birthYear = 1992
    val currentYear = Year.now.getValue
    val greeting = "Hello, I'm " + name + " and I am " + age + "years old."
    val greeting_v2 = s"Hello, I'm $name and I am $age years old."
    val greeting_v3 = s"Hello, I'm $name and I will be turning ${currentYear - birthYear} years old."
    
    // f-interpolation (format interpolater)
    val speed = 1.2f
    val myth = f"$name can eat $speed%2.2f burgers per minute"

    // raw-interpolation - can ignore escape sequence like /n, /t
    val escapes = "This is a \n newline \t tab"
    val rawEscapes = raw"This is a \n newline \t tab"

    def main(args: Array[String]): Unit = {
        
        println(greeting)
        println(greeting_v2)
        println(greeting_v3)
        println(myth)
        println(escapes)
        println(rawEscapes)
    }
}
