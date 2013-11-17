This repository contains the interpreter of math expresions.
It still does not have the function which would check correction of expression.
Tests will be wriiten as soon as posible.
 
Supporting operators:
+
-
*
/
^

Note: Unar minus must be used with brackets only. 
Example: 2/(-1)
Unar plus is not supported yet.

Supporting functions:
sum(arg1, arg2, ..., argn)
sqrt(arg)
min(arg1, arg2, ..., argn)
max(arg1, arg2, ..., argn)

Arguments may be math expressions or functions either.
Functions may be the part of math expression as well.
Example: 1 + sqrt(16) + sum(4+3,sqrt(81))

Note: Functions that were written with the gap between the name and 
the oppening bracket will not be properly calculated. (Will be fixed soon)



This version has the pilot version of graphic interface.