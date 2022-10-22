package com.rockthejvm.part2oop

object _12Enums {

    // - used as data type
    // - enumerate over all the instances of that type
    // - kind of a sealed data type that can't be extended i.e. we only have to used the defined instances.
     enum Permissions {
         // instances
         case READ, WRITE, EXECUTE, NONE
         // add fields/methods
         def openDocument(): Unit =
             if (this == READ)  println("opening document...")
             else               println("reading not allowed.")
     }

    val somePermissions: Permissions = Permissions.READ

    // enum with constructor args
    // similar to java :
    /*
        enum PermissionsWithBits {
            READ(4)    // 100
            WRITE(2)   // 010
            EXECUTE(1) // 001
            NONE(0)    // 000
            public final int bits
            private PermissionWithBits(int bits) {
                this.bits = bits;
            }
        }
    */

    enum PermissionsWithBits(bits: Int) {
        case READ extends PermissionsWithBits(4)    // 100
        case WRITE extends PermissionsWithBits(2)   // 010
        case EXECUTE extends PermissionsWithBits(1) // 001
        case NONE extends PermissionsWithBits(0)    // 000
    }

    // companion object for enum
    // We can use companion object for enum as a source for factory method,
    // In factory method, given some arguments we return one possible instance of Enum
    object PermissionsWithBits {
        def fromBits(bits:Int): PermissionsWithBits =
            if(bits == 4)
                PermissionsWithBits.READ
            else if (bits == 2)
                PermissionsWithBits.WRITE
            else if (bits == 1)
                PermissionsWithBits.EXECUTE
            else
                PermissionsWithBits.NONE
    }

    // standard API in enum
    //  - the ability to inspect the index/ordinal at which that particular Enum instance was defined
    //  - val somePermissions ordinal is 0, as READ is defined 1st in the Permissions Enum definition
    val somePermissionsOrdinal = somePermissions.ordinal

    // - ability to iterate over all possible values of enum
    //      suppose, we are accessing a Enum library from an external source, we might want to check the list
    //      array of all possible values of the enum
    val allPermissions = PermissionsWithBits.values

    // - we can also create an instance of an Enum from a string.
    //      can only be Enum with no constructor args
    val readPermission: Permissions = Permissions.valueOf("READ")

    def main(args: Array[String]): Unit = {
        somePermissions.openDocument()
            println(somePermissionsOrdinal) // 0
            println(readPermission)
    }
}
