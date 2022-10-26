package com.rockthejvm.part3fp

object _04HOFsCurryingExercise1 {

    /**
     * Exercise: 
     * TODO: all the best exercise is based on LList
     * 1. LList exercises
     *      - foreach(A => Unit): Unit
     *      [1, 2, 3].foreach(x => println(x))
     *      
     *      - sort((A, A) => Int): LList[A]
     *      [3, 2, 4, 1].sort((x, y) => x - y) = [1, 2, 3, 4]
     *      (hint: use insertion sort)
     *      
     *      - zipWith[B](LList[A], (A, A) => B): LList[B]
     *      [1, 2, 3].zipWith([4, 5, 6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]
     *      
     *      - foldLeft[B](start: B)((A, B) => B): B
     *      [1, 2, 3, 4]. foldLeft[Int](0)(x + y) = 10
     *      0 + 1 = 1
     *      1 + 2 = 3
     *      3 + 3 = 6
     *      6 + 4 = 10
     */
    
    def main(args: Array[String]): Unit = {
        
    }
}
