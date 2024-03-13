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

class BalancedParentheses {
    private Stack window1 = new Stack();
    private Stack window2 = new Stack();
    private Stack window3 = new Stack();
    private Stack window4 = new Stack();

    private int time = 0;

    // constructor
    public BalancedParentheses () {}
    
    public void addToShortest(String customer) {
        System.out.println("Adding " + customer);
        if (window1.length() == 0) {
            window1.push(customer);
        } else if (window2.length() == 0) {
            window2.push(customer);
        } else if (window3.length() == 0) {
            window3.push(customer);
        } else if (window4.length() == 0) {
            window4.push(customer);
        } else {
            if (window1.length() <= window2.length() && window1.length() <= window3.length() && window1.length() <= window4.length()) {
                window1.push(customer);
            } else if (window2.length() <= window1.length() && window2.length() <= window3.length() && window2.length() <= window4.length()) {
                window2.push(customer);
            } else if (window3.length() <= window1.length() && window3.length() <= window2.length() && window3.length() <= window4.length()) {
                window3.push(customer);
            } else {
                window4.push(customer);
            }
        }
    }

    public boolean randomChance() {
        return Math.random() < 0.5;
    }

    public void serveCustomer() {
        if (window1 != null) {
            if (randomChance()) {
                window1.pop();
            }
        }
        if (window2 != null) {
            if (randomChance()) {
                window2.pop();
            }
        }
        if (window3 != null) {
            if (randomChance()) {
                window3.pop();
            }
        }
        if (window4 != null) {
            if (randomChance()) {
                window4.pop();
            }
        }
    }

    public void timeTick() {
        int new_customers = (int) (Math.random() * 8);
        time++;

        System.out.println("Time: " + time + " New customers: " + new_customers);
        for (int i = 0; i < new_customers; i++) {
            addToShortest("Customer " + i + " at time " + time);
        }
        
        serveCustomer();
    }

    public void print() {
        System.out.println("Window 1:");
        window1.print();
        System.out.println("Window 2:");
        window2.print();
        System.out.println("Window 3:");
        window3.print();
        System.out.println("Window 4:");
        window4.print();
    }

    public void totalCustomers() {
        System.out.println("Total customers served: " + (window1.length() + window2.length() + window3.length() + window4.length()));
    }
}

public class Main {
    // test the BalancedParentheses  class
    public static void main(String[] args) {
        BalancedParentheses  rack = new BalancedParentheses();

        for (int i = 0; i < 10; i++) {
            rack.timeTick();
            rack.print();
            rack.totalCustomers();
            System.out.println();
        }
        
    }
}