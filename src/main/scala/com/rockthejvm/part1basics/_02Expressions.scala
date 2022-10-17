package com.rockthejvm.part1basics

object _02Expressions {

    // expressions are structures that can be evaluated to value
    val meaningOfLife = 40 + 2

    // mathematical expression: + - / *, bitwise | & << >> >>>
    val mathExpression = 2 + 3 + 4

    // comparison express: < <= > >= == !=
    val equalityTest = 1 == 2

    // boolean expression: ! || &&
    val nonEqualityTest = !equalityTest

    // instructions vs expressions
    // expressions are evaluated, instructions are executed
    // in java, c++, python we think in terms of instructions.
    // in scala we think in terms of expressions
    // and pretty much everything is an expression

    // ifs are expressions, it should evaluate to something, ie. it should return something.
    // we don't have instructions in the 'if' branches, we only have values/expression
    val aCondition = false
    val anIfExpression = if (aCondition) "Value is true" else "value is false"
    
    println("anIfExpression: " + anIfExpression)

    // code blocks - an expression with value as the last expression
    val aCodeBlock = {
        // local values
        val aLocalValue = 48
        // expressions..
        // last expression = value of the block
        aLocalValue + 50
    }

    println("aCodeBlock: " + aCodeBlock)

    val printlnResult: Unit = println("hello")
    // println return 'Unit' which means void return type.

    def main(args: Array[String]): Unit = {
        // Exercise
        println(printlnResult) // hello, () -> means void
        println(anIfExpression)
        println(if (aCondition) "Value is true" else "value is false")
    }
}
