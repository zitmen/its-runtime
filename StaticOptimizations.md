# Static optimizations #

  * Dead code elimination
    * loops, conditions
    * [TODO](TODO.md) functions or variables that are not used are deleted
    * [TODO](TODO.md) empty functions are deleted including their calls
    * empty blocks are deleted
    * useless statements are deleted, f.e.: `~x; 5; [0+1:2*4-4];`
  * Constant folding (expressions)