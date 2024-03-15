import java.util.ArrayList;

class Stack {
    private ArrayList<String> stack = new ArrayList<String>();
    
    public Stack(String initValue) {
        stack.add(initValue);
    }

    public Stack() {}
    
    // fix the push and pop methods
    public void push(String newValue) {
        stack.add(newValue);
    }

    public String pop() {
        if (stack.size() == 0) {
            return "Stack is empty";
        } else {
            System.out.println("Served Customer: " + stack.get(stack.size() - 1));
            return "Removed from the stack: " + stack.remove(stack.size() - 1);
        }
    }

    public void print() {
        if (stack.size() == 0) {
            System.out.println("Stack is empty");
        } else {
            for (String value : stack) {
                System.out.println(value);
            }
        }
    }

    public int length() {
        return stack.size();
    }
}

class Seperator {
    private int index;

    public Seperator(int index) {
        this.index = index;
    }

    public String getSeperator(String input) {
        return input.substring(index, index + 1);
    }

    public int getIndex() {
        return index;
    }

    public String getAlternateSeperator(String input) {
        switch (getSeperator(input)) {
            case "(":
                return ")";
            case ")":
                return "(";
            case "{":
                return "}";
            case "}":
                return "{";
            case "[":
                return "]";
            case "]":
                return "[";
            default:
                return "Invalid Seperator";
        
        }
    }
}

class BalancedParentheses {
    private Stack parenthesis = new Stack();

    private int time = 0;

    // constructor
    public BalancedParentheses () {}
    
    public void addToShortest(String customer) {
        
    }

    public boolean randomChance() {
        return Math.random() < 0.5;
    }

    public void serveCustomer() {
        
    }

    public void timeTick() {
        
    }

    public void print() {
        
    }
}

public class Main {
    // test the BalancedParentheses  class
    public static void main(String[] args) {
        BalancedParentheses  rack = new BalancedParentheses();

        for (int i = 0; i < 10; i++) {
            rack.timeTick();
            rack.print();
            System.out.println();
        }
        
    }
}