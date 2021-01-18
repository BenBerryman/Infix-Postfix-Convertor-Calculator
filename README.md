# Infix-Postfix Convertor/Calculator
A simple program that uses a stack to either convert from infix notation to
postfix notation, or to calculate a postfix expression.

## Getting Started

### Prerequisites
- JDK (Java Development Kit) 8 or later installed on your local machine

NOTE: I recommend compiling with JDK 8 to maintain compatibility with the latest release of the JRE (version 8),
as .class files generated from a newer JDK will not run on an older JRE.

### Usage
To compile the program:
- Open a Terminal window
- Navigate to the *src* directory
- Type:
```bash
javac Runner.java
```
To run the program, type:

```bash
java Runner type expression outputfile
```
Replace *type* with a "1" for infix-to-postfix-conversion or "2" for postfix calculation,
*expression* with the desired expression, and *outputfile* with the desired name for the output file.

Sample inputs are given in the *testcases* folder. To use one, simply open the text file, copy the contents, and paste
into the *expression* field above at runtime.
## Outline
+ **Runner.java** : Main class. Responsible for reading command line arguments and writing to output file.
+ **CustomStack.java** : Responsible for maintaining operations in a stack structure and calculating result.
