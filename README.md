# SDF Project – Arbitrary Precision Integer & Float Calculator

Hello! This is my project for SDF course (CS1023) where I made a calculator for very large numbers.
It supports both **Integer** and **decimal(Float)** type values

---

## what is this ❓❓🤔

I've created my own classeslike 'AInteger' and 'AFloat' which can do operations like **add, sub, mul, div**
without using the in-built java int or float for big numbers.

Like, even if the number has 50 digits or decimal with 30 places, still this will work properly

---

## 📦 Files and Structure

```
.
├── arbitaryarithmetic/
│   ├── AInteger.java   --> for big integers
│   └── AFloat.java     --> for float
├── myInfArith.java     --> main file to run from terminal
├── build.xml           --> for compiling using `ant`
├── run.py --> for running with python also
├── README.md           --> this file
```

---

## How to run 🕹️

    Using Java (Command Line)

        Compile and run:

        ```bash
        javac arbitaryarithmetic/*.java myInfArith.java

        for int
            java myInfArith int add 360240608653758023578 73846984849474849

        for floats
            java myInfArith float div 1.234 0.0001

    Using Python "if you're lazy 😁"

        for int
            python3 run.py int mul 99999 88888

        for float 
            python3 run,py float div 11226873486258.0 34823874197.8474
    
    The script auto-compiles and runs the Java code for you.

⚙️ Features
    - Supports arbitrarily large integers and high-precision floats

    - Works with negative numbers

    - Handles edge cases like division by zero

    - Simple CLI interface

    - Can be run from Python too

❌ Limitations
    - Division could be better.
    
    - performance is not optimized.

🙌 Credits
    - Name: Budde Dhathvi Charan
    - Course: CS1023-Software Development Fundamentals '(SDF)'     
