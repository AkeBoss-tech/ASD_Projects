# Mera Stack ka Lab

Name: Akash Dubey

Design and implement an application that evaluates a postfix expression that operates on integer operands using the arithmetic operators +, -, *, /, and %.  You already know infix expressions, in which the operator is positioned between its two operands.  A postfix expression puts the operators after its operands.  Keep in mind that an operand could be the result of another operation.  Postfix notation eliminates the need for parenthese to force precedence.  For example the following infix expression
$(5+2)*(8-5)$
Is equivalent to the following postfix expression:
$5 2 + 8  5 - *$

The evaluation of a postfix expression is facilitated by using a stack.  As you process a postfix expression from left to right, you encounter operands and operators.  If you encounter an operand, push it on the stack.  If you encounter an operator, pop two operands off the stack, perform the operation, and push the result back on the stack.  When you have processed the entire expression there will be one value on the stack, which is the result of the entire expression.

You may want to use a scanner object to assist in the parsing of the expression.  You can assume the expression will be in valid postfix form.  Import the pre-made stack class for this lab.
