package com.rockthejvm.part1basics

object _01ValuesAndTypes {

  def main(args: Array[String]): Unit = {

    // values - immutable
    val meaningOfLife:  Int = 43; // final int meaningOfLife = 43

    // reassignment is not allowed
    // meaningOfLife = 56

    // short hand notation using 'type inference'
    val anInteger = 34            // Data type is optional

    // common types
    val aBoolean: Boolean = false
    val aChar: Char = 'a'
    val aShort: Short = 12          // 2 bytes
    val aInt: Int = 123             // 4 bytes
    val aLong: Long = 12345671723L  // 8 bytes
    val aFloat: Float = 1.4f        // 4 bytes
    val aDouble: Double = 3.1428    // 8 bytes

    // string
    val aString: String = "Hari"
  }
}
