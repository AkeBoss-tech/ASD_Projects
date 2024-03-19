import java.util.ArrayList;
import java.util.Scanner;

import java.util.Stack;

class Number {
    private double value;
    private int index;

    public Number(double value, int index) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}

class Operator {
    private String value;
    private int index;

    public Operator(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    public double operation(double a, double b) {
        if (value.equals("+")) {
            return a + b;
        } else if (value.equals("-")) {
            return a - b;
        } else if (value.equals("*")) {
            return a * b;
        } else if (value.equals("/")) {
            return a / b;
        } else if (value.equals("%")) {
            return a % b;
        } else {
            return 0;
        }
    }
}

class ReversePolish {
    private String input;
    private Stack<Number> number_stack;
    private Stack<Operator> operator_stack;

    public ReversePolish(String input) {
        this.input = input;
        number_stack = new Stack<Number>();
        operator_stack = new Stack<Operator>();
    }

    private boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double evaluate() {
        number_stack = new Stack<Number>();
        operator_stack = new Stack<Operator>();

        String[] input_array = input.split(" ");
        for (int i = 0; i < input_array.length; i++) {
            if (isNumber(input_array[i])) {
                number_stack.push(new Number(Integer.parseInt(input_array[i]), i));
            } else {
                // operator_stack.push(new Operator(input_array[i], i));
                Number a = number_stack.pop();
                Number b = number_stack.pop();
                Operator op = new Operator(input_array[i], i);
                number_stack.push(new Number(op.operation(b.getValue(), a.getValue()), i));
            }
        }

        return number_stack.pop().getValue();
    }

    public void print() {
        System.out.println("The input is: " + input);
        System.out.println("The result is: " + evaluate());
    }

    public static double evaluate(String input) {
        ReversePolish thing = new ReversePolish(input);
        return thing.evaluate();
    }

}

public class Main {
    // test the BalancedParentheses class
    public static void main(String[] args) {
        System.out.println("Testing the BalancedParentheses class");

        // Test cases
        System.out.println("Test case 1: ");
        System.out.println("Input: 5 2 + 8 5 - *");
        System.out.println("Expected output: " + ReversePolish.evaluate("5 2 + 8 5 - *"));

        // Enter your own input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 'exit' to stop:");
        System.out.println("Press enter to continue:");

        while (!input.nextLine().equals("exit")) {
            System.out.println("Enter a string: ");
            String user_input = input.nextLine();

            ReversePolish thing3 = new ReversePolish(user_input);
            thing3.print();

            System.out.println("\n\nEnter 'exit' to stop:");
        }

        input.close();
    }
}