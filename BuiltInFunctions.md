# Built-in functions #

### String ###
| indexOf(s, "xxx") | index of first occurrence of substring |
|:------------------|:---------------------------------------|
| lastIndexOf(s, "xxx") | index of first occurance of substring |
| substring(s, start-pos, end-pos) | extract substring |
| toLower(s) | convert to lower case |
| toUpper(s) | convert to upper case |
| trim(s) | remove trailing spaces |
| startsWith(s, prefix) | check if string has specified prefix |
| endsWith(s, suffix) | check if string has specified suffix |
| string(x) | convert argument to string |
| integer("100") | convert string to integer |
| real("5.5") | convert string to real |
| length(x) | length of string |
| concat(s1, s2) | concatenation of strings s1 and s2 |

### Array ###
| cloneArray(a) | returns copy of the array a |
|:--------------|:----------------------------|
| clearArray(a, v) | sets all elements in array a to the value v |
| length(a) | returns cout of elements in the array a |

### IO ###
| f = openRFile("test.txt") | opens file with specified name in the read-only access mode |
|:--------------------------|:------------------------------------------------------------|
| f = openWFile("test.txt") | opens file with specified name in the write-only access mode |
| closeFile(f) | close file |
| flushFile(f) | flush buffers |
| printFile(f, "x=" + x) | print argument to the file |
| printlnFile(f, "x=" + x) | the same as above but append '\n' |
| print("x=" + x) | print argument to the console |
| println("x=" + x) | the same as above but append '\n' |
| inputFile(f) | input string from the specified file |
| input() | input string from console |
| eof(f) | returns true if end of file |
| eoi() | returns true if end of input |

### Math ###
| pow(x, n) | power x^n |
|:----------|:----------|
| sqrt(x) | square root of x |
| log(x) | natural logarithm of x |
| rand(x) | random integer modulo x |