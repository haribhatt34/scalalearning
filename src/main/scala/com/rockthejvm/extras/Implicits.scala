package com.rockthejvm.extras

object Implicits extends App {

    // 1. implicit parameters
    def add(x: Int, y: Int): Int = x + y
    val addition = add(10, 2)
    println(addition)

    implicit val defaultValue: Int = 2
    def addImplicit(x: Int) (implicit y: Int) =
        x + y

    // Cmd + Shift + P = show the implicit argument/value used
    //      - press down arrow + enter => go to the implicit argument/value.
    // Option + Q = show the implicit conversion used
    val addition_v2 = addImplicit(10)
    val addition_v3 = addImplicit(10)(2)
    println(addition_v2)
    println(addition_v3)

    def testPrint(x: Int)(implicit y: String) =
        println(s"$x $y")

    // implicit def functions with implict arguments
    implicit val defaultValue3: String = "Hari"
    // We cannot have two implicits of same type
    // implict val defaultValue4: String = "Shankar Bhatt"  // "ambiguous implicit arguments"
    implicit def defaultValue4(implicit name: String): String =
        s"$name"
    testPrint(5)


    // 2. Implicit Conversion
    // case 1:
    final case class Name(name: String) {
        def announce(): String = s"Hello, my name is $name"
    }
    // this implicit def function converts a string to Name type
    implicit def stringToName (user: String): Name =
        Name(user)
    // we are passing a string where Name type is required
    val userName: Name = "Hari"
    println(userName)

    // case 2:
    val announceUser = "Hari".announce()
    println(announceUser)
}
