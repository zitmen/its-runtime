# Semantic rules #

  * All identifiers are case-sensitive.

  * There are [built-in functions](BuiltInFunctions.md) for some basic operations.
  * Only user-defined or built-in function can be called.
  * When calling a function there must be the same count of parameters as in the definition.
  * Function overriding is not allowed.
  * There must be allways the **main** function in any program.

  * Variables use static type cast.
  * Each variable must be declared first before being used.
    * If there is no value assigned to the variable, then it is default integer **0**, bool **false**, double **0.0** and others (File, String, Array) **null**.
  * Both literals in binary expressions must be of the same data-type.
  * All expressions in conditions must be boolean (Java style).
  * Unary operators can be used only with these data-types:
    * **!** - bool
    * **~** - bool, int
    * **++** - int
    * **--** - int
    * **+** - bool, int, double
    * **-** - bool, int, double
  * Postfix operators **++** and **--** behave the same way as do the prefix operators in C++.
  * There is scoping of variables - variables in an inner block shadows any variables of the same name out of outer block.
  * Control flow statements **break** and **continue** must be inside of a loop statement.
  * Arrays can use only **int** data-type as indices.
  * Strings are implemented in the same way as in C++ (zero-terminated character array).
  * When assigning Arrays, Strings, Files or Structs it only assigns the reference.
    * If you want to clone the whole Array or String, you can use the built-in function called _cloneArray_.
    * Others needs to be copied manualy (your own function).
  * All functions must return a value of the declared data-type (except of a **void** functions which return nothing).