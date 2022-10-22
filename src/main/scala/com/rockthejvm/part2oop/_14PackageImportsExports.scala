package com.rockthejvm.part2oop


// top-level
// in Scala 2, we can define only data type as the top level
// with Scala 3, we define fields and methods at top level 
// these are kind of global variable
//  - their use is discouraged, as an anti-pattern
val meaningOfLife = 42
def computeMyLife: String = "Scala"
// Scala compiler will create a synthetic object package known as "part2oop" to wrap these definition

object _14PackageImportsExports {

    // packages

    // fully qualified name
    // throws exception saying "an implementation is missing"
//    val aList: com.rockthejvm.practice.LList[Int] = ???

    // import
    // in Scala we can write import statement anywhere within class, def
    import com.rockthejvm.practice.LList
//    val anotherList: LList[Int] = ???

    // importing under an alias
    // for conflicting name collision
    // import java.util.{List => JList} // Scala 2
    import java.util.{List as JList}    // Scala 3
    val aJavaList: JList[Int] =aJavaList

    // importing everything under a package
    import com.rockthejvm.practice.*
    val aPredicate: Predicate[Int] = ???

    // import multiple symbols
    import PhysicsConstants.{SPEED_OF_LIGHT, EARTH_GRAVITY}
    val c = SPEED_OF_LIGHT
    
    // import everything but something
    object PlayingPhysics {
        // below import will now be hidden
        import PhysicsConstants.{PLACK as _, *}
    }

    // import the meaningOfLife and computeMyLife 
    import com.rockthejvm.part2oop.*
    
    // default imports added by Scala
    // scala.*, scala.Predef.*, java.lang.*
    
    // exports - introduced in Scala 3
    class PhysicsCalculator {
        import PhysicsConstants.*
        def computePhotonEnergy(wavelength: Double): Double =
            PLACK / wavelength
    }
    
    // export
    // define any class/object with any other class/object as its field, like physicsCalculator here
    // when we do : export physicsCalculator.computePhotonEnergy 
    // computePhotonEnergy is now available to ScienceApp Scope
    // so now, whenever we require this method computePhotonEnergy.
    // we can directly use it, without having to call it by fully qualified name.
    // Helpful when ? when we are repeating a symbol many times in our scope, there we can export the field
    // to use it without fully qualified name
    object ScienceApp {
        val physicsCalculator = new PhysicsCalculator
        
        export physicsCalculator.computePhotonEnergy
        
        def computeEquivalentMass(wavelength: Double): Double =
            computePhotonEnergy(wavelength) / (SPEED_OF_LIGHT * SPEED_OF_LIGHT)
    }
    
    def main(args: Array[String]): Unit = {
//        println(aList)
    }
}

object PhysicsConstants{
    // constants
    val SPEED_OF_LIGHT = 29972458
    val PLACK = 6.62e-34 // scientific notation
    val EARTH_GRAVITY = 9.8
}