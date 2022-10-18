package com.rockthejvm.part1basics

import scala.annotation.tailrec

object _07DefaultAndNamedArgs {

    // below are passing accumulator a value 0
    // we don't want to use provide the second argument when calling the method
    // here comes default argument
    @tailrec
    def sumUntilTailrec(n: Int, accumulator: Int = 0): Int = {
        if (n < 1) accumulator
        else sumUntilTailrec(n - 1, accumulator + n) // TAIL recursion = recursive call occurs LAST in the path 
    }
    val sumUntil10 = sumUntilTailrec(10)
    
    // similarly here, we want to use default format as JPEG, width as 1920, height as 1080 when not provided
    def savePicture(dirPath: String, name: String, format: String = "jpeg", width: Int = 1920, height: Int = 1080): Unit = {
        println("Saving picture in dir: " + dirPath + "name: " + name + ", format: " + format + ", width: " + width + 
                ", height: " + height)
    }

    def main(args: Array[String]): Unit = {
        println(sumUntil10)
        // default args are injected
        savePicture("/users/hari/photos", "mypic")
        // pass explicit different values for default args
        savePicture("/users/hari/photos", "mypic", "png")
        // pass named argument in sequence
        savePicture("/users/hari/photos", "mypic", width = 1280, height = 720)
        // pass named argument in random order
        savePicture("/users/hari/photos", "mypic", height = 720, width = 1280) 
    }
}
