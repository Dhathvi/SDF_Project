import os
import sys
import subprocess


#compiling java files
print("compiling in progressss.......⏳")
compileCmd="javac arbitaryarithmetic/*.java myInfArith.java"

if os.system(compileCmd) !=0:
    print("compilation failed sorry😔")
    sys.exit(1)


#verifying Inputformat and running the program
if len(sys.argv) !=5:
    print("Usage: java myInfArith <int|float> <add|sub|mul|div> <op1> <op2>")
    sys.exit(1)

_type , op, a, b = sys.argv[1:]

cmd = ["java", "myInfArith", _type, op, a, b]
subprocess.run(cmd)
