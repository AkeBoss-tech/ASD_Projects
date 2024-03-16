import java.util.ArrayList;
import java.util.Scanner;

class Stack {
    private ArrayList<Separator> stack = new ArrayList<Separator>();
    
    public Stack(Separator initValue) {
        stack.add(initValue);
    }

    public Stack() {}
    
    // fix the push and pop methods
    public void push(Separator newValue) {
        stack.add(newValue);
    }

    public Separator pop() {
        if (stack.size() == 0) {
            return null;
        } else {
            // System.out.println("Served Customer: " + stack.get(stack.size() - 1));
            return stack.remove(stack.size() - 1);
        }
    }

    public Separator get(int index) {
        return stack.get(index);
    }

    public void print() {
        if (stack.size() == 0) {
            System.out.println("Stack is empty");
        } else {
            for (Separator value : stack) {
                System.out.println(value);
            }
        }
    }

    public int length() {
        return stack.size();
    }
}

class Separator {
    private int index;

    private static final String[] opening_separators = {"(", "{", "[", "<"}; // add whatever things you want
    private static final String[] closing_separators = {")", "}", "]", ">"}; // add whatever things you want

    public Separator(int index) {
        this.index = index;
    }

    public String getSeparator(String input) {
        return input.substring(index, index + 1);
    }

    public int getIndex() {
        return index;
    }

    public String getAlternateSeparator(String input) {
        for (int i = 0; i < opening_separators.length; i++) {
            if (getSeparator(input).equals(opening_separators[i])) {
                return closing_separators[i];
            }
        }
        for (int i = 0; i < closing_separators.length; i++) {
            if (getSeparator(input).equals(closing_separators[i])) {
                return opening_separators[i];
            }
        }
        return "Invalid";
    }

    public String toString() {
        return "Separator at index: " + index;
    }

    public boolean isOpening(String input) {
        for (String separator : opening_separators) {
            if (getSeparator(input).equals(separator)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfSeparator(String input) {
        for (String opennings : opening_separators) {
            if (input.equals(opennings)) {
                return true;
            }
        }

        for (String closings : closing_separators) {
            if (input.equals(closings)) {
                return true;
            }
        }

        return false;
    }
}

/* 
 * Use a stack to solve the balanced parentheses problem. 
 * It involves checking if a given string of text has properly 
 * matched and nested parentheses, brackets, and braces. 
 * Look at the sample input and output below to better understand the problem. 

    SAMPLE INPUT: (hello)(()){[{[goodbye]}]} 		
    SAMPLE OUTPUT: Valid 

    SAMPLE INPUT 2: (([is this balanced?))]{}{[]}{} 
    SAMPLE OUTPUT 2: Invalid 

    EXPLANATION: The first input has appropriately balanced parentheses, 
    brackets, and braces. Although the second input contains the same number 
    of opening and closing parenthetical symbols, 
    the first bracket is incorrectly closed outside the first two nested parentheses.


    EXTRA IDEAS: 
    1. Your code can check for angled brackets (chevrons <>). ✅
    2. Your code can output the longest enclosed string of characters in a valid string. ✅
    3. Your code can output the index and which character makes the string invalid. ✅
    4. Your code can output the swaps needed to validate a string. ✅
 */

class BalancedParentheses {
    private Stack parenthesis = new Stack();
    private String input;

    // constructor
    public BalancedParentheses (String input) {
        this.input = input;
        for (int i = 0; i < input.length(); i++) {
            if (Separator.checkIfSeparator(input.substring(i, i + 1))) {
                parenthesis.push(new Separator(i));
            }
        }
    }

    public boolean isValid() {
        // this is an easy thing to check for 
        // but I want it to be more dynamic and check for errors
        /* if (parenthesis.length() % 2 != 0) {
            return false;
        } */
        System.out.println();
        System.out.println("Starting Input: " + input);

        String largest_enclosed = "";

        Stack open_separators = new Stack();

        for (int i = 0; i < parenthesis.length(); i++) {
            Separator current = parenthesis.get(i);

            if (current.isOpening(input)) {
                open_separators.push(current);
            } else { // if it is a closing separator
                if (open_separators.length() == 0) {
                    System.out.println("Error\nNo opening separator for: " + current.getSeparator(input));
                    System.out.println("Index: " + current.getIndex());
                    return false;
                } else {
                    Separator last_open = open_separators.pop();
                    if (!current.getAlternateSeparator(input).equals(last_open.getSeparator(input))) {
                        System.out.println("Error\nMismatched separators: ");
                        System.out.println("Expected: " + last_open.getAlternateSeparator(input) + " but got: " + current.getSeparator(input));
                        System.out.println("Index: " + current.getIndex());

                        // little code to show where exactly the issue is
                        System.out.println(input);
                        String spaces = "";
                        for (int j = 0; j < current.getIndex(); j++) {
                            spaces += " ";
                        }
                        System.out.println(spaces + "^");
                        return false;
                    }
                    // print the stuff between the separators
                    String text = input.substring(last_open.getIndex() + 1, current.getIndex());
                    System.out.println("Text between: '" + text + "'");
                    if (text.length() > largest_enclosed.length()) {
                        largest_enclosed = text;
                    }
                }
            }
        }

        if (open_separators.length() != 0) {
            System.out.println("Error\nThere are still open separators");
            for (int i = 0; i < open_separators.length(); i++) {
                System.out.println(open_separators.get(i).getSeparator(input));
            }
            return false;
        }

        System.out.println("Largest enclosed: '" + largest_enclosed + "'");
        System.out.println("Length of largest enclosed: " + largest_enclosed.length());

        return true;
    }

    public void print() {
        System.out.println("String is valid: " + isValid());
    }

}

public class Main {
    // test the BalancedParentheses  class
    public static void main(String[] args) {
        BalancedParentheses thing = new BalancedParentheses("(hello)(()){[{[goodbye]}]}");
        thing.print();

        BalancedParentheses thing2 = new BalancedParentheses("(([is this balanced?))]{}{[]}{}");
        thing2.print(); 
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 'exit' to stop:");
        System.out.println("Press enter to continue:");
        while (!input.nextLine().equals("exit")) {
            System.out.println("Enter a string: ");
            String user_input = input.nextLine();
            BalancedParentheses thing3 = new BalancedParentheses(user_input);
            thing3.print();
            System.out.println("Enter 'exit' to stop:");
        }
        input.close();
    }
}