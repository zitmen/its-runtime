# It's Run Time #
_This is my semestral project (winter term, 2011) @FIT CTU in Prague for MI-RUN (Runtime Systems) class._

This project is composed of two main components: bytecode compiler (written in Java) and virtual machine (written in C++). It supports bytecode interpretation, garbage collecting and hotspot dynamic compilation (JIT compilation). For all memory operations (stack, heap, even the code if it's compiled at runtime) the Virtual Machine uses its own memory manager.

The language is hybrid of C++, CTalk and Java. It's mostly CTalk, but there are some features inspired from C++ and Java, bacause I wanted the language to be staticaly-typed with dynamic allocation.

**VM was tested only on WindowsXP Professional SP3 (32b) and Windows 7 (64b). Without JIT compilation is possible to run VM on 64b platform, but with JIT turned on it will fail!**

**Further reading:**
  * [Syntax](Syntax.md)
  * [Semantics](Semantics.md)
  * [Static Optimizations](StaticOptimizations.md)
  * [Runtime](Runtime.md)
  * [Testing & Examples](TestingExamples.md)


**Possible improvements:**
  * Settings of bytecode generator and virtual machine could be set via command line arguments,
  * JIT Compilation in threads,
  * optimize compiled code (faster instructions, use FPU more efficiently, experiments with SSE),
  * tri-color Garbage Collector algorithm (it would make JIT Compilation more complex),
  * more static optimizations (bytecode generator),
  * optimize bytecode generator to use less temporary variables,
  * redesign the bytecode (whole CALL/RET(V)/POP system and ZF usage are not efficient),
  * get rid of all memory leaks in Virtual Machine.


---

## MI-RUN Exam ##
| **Question** | **Teacher's answer** | **My status** |
|:-------------|:---------------------|:--------------|
| Do I have to implement GC? | Yes | ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png) |
| Do I have to implement bytecode interpreter? | Yes | ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png) |
| Do I have to implement bytecode compiler? | Yes | ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png) |
| Do I have to implement just-in-time compiler? | Yes | ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png) |
| Do I have to implement XYZ? | Yes, of course! | ![http://zitmen.cz/admin/Template/media/images/button-cancel.png](http://zitmen.cz/admin/Template/media/images/button-cancel.png) |

What optimization do I have to implement?
  * All known, ![http://zitmen.cz/admin/Template/media/images/button-cancel.png](http://zitmen.cz/admin/Template/media/images/button-cancel.png)
  * dead code elimination, ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png)
  * constant folding, ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png)
  * abstract interpretation, ![http://zitmen.cz/admin/Template/media/scripts/CLEditor/images/icons/8.gif](http://zitmen.cz/admin/Template/media/scripts/CLEditor/images/icons/8.gif) _(it depends on definition)_
  * trace-based compilation, ![http://zitmen.cz/admin/Template/media/images/button-ok.png](http://zitmen.cz/admin/Template/media/images/button-ok.png)
  * custom compilation, ![http://zitmen.cz/admin/Template/media/scripts/CLEditor/images/icons/8.gif](http://zitmen.cz/admin/Template/media/scripts/CLEditor/images/icons/8.gif) _(somewhat)_
  * inlining, ![http://zitmen.cz/admin/Template/media/images/button-cancel.png](http://zitmen.cz/admin/Template/media/images/button-cancel.png)
  * everything! ![http://zitmen.cz/admin/Template/media/images/button-cancel.png](http://zitmen.cz/admin/Template/media/images/button-cancel.png)