import java.util.ArrayList;
import java.util.Scanner;

// base stack class
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
            return stack.remove(stack.size() - 1);
        }
    }

    // get things
    public Separator get(int index) {
        return stack.get(index);
    }

    // pretty self explanatory
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
    // have the separator store an index that is used later
    private int index;

    // store the separators so any can be used
    private static final String[] opening_separators = {"(", "{", "[", "<"}; // add whatever things you want
    private static final String[] closing_separators = {")", "}", "]", ">"}; // add whatever things you want

    public Separator(int index) {
        this.index = index;
    }

    // get the string the index references
    public String getSeparator(String input) {
        return input.substring(index, index + 1);
    }

    public int getIndex() {
        return index;
    }

    // use our arrays to get the alternate separator
    // check which kind of separator it is and return the opposite
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

    // check if the separator is an opening one
    public boolean isOpening(String input) {
        for (String separator : opening_separators) {
            if (getSeparator(input).equals(separator)) {
                return true;
            }
        }
        return false;
    }

    // check if the separator if it is in either array
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

class BalancedParentheses {
    // you didn't need to use a stack for this but I thought it would be cool
    private Stack separators = new Stack();
    // store the input string
    private String input;

    // constructor
    public BalancedParentheses (String input) {
        this.input = input;
        // find all the separators and store them in the stack
        for (int i = 0; i < input.length(); i++) {
            if (Separator.checkIfSeparator(input.substring(i, i + 1))) {
                separators.push(new Separator(i));
            }
        }
    }

    public boolean isValid() {
        // this is an easy thing to check for 
        // but I want it to be more dynamic and check for errors
        /* if (separators.length() % 2 != 0) {
            return false;
        } */
        System.out.println();
        System.out.println("Starting Input: " + input);

        String largest_enclosed = "";

        // create a stack to store the opening separators
        // very important
        Stack open_separators = new Stack();

        // go through the stack of all the separators
        for (int i = 0; i < separators.length(); i++) {
            Separator current = separators.get(i);

            // if its an opening separator add it to the stack
            if (current.isOpening(input)) {
                open_separators.push(current);
            } else { // if it is a closing separator
                if (open_separators.length() == 0) {
                    // theres no corresponding opening separator
                    System.out.println("Error\nNo opening separator for: " + current.getSeparator(input));
                    System.out.println("Index: " + current.getIndex());
                    return false;
                } else {
                    // the opening separator is being closed
                    Separator last_open = open_separators.pop();
                    // checks to see that the closing separator matches the one for opening 
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
                    // check if the text is the largest enclosed
                    if (text.length() > largest_enclosed.length()) {
                        largest_enclosed = text;
                    }
                }
            }
        }

        // if there are still open separators then there is an issue
        if (open_separators.length() != 0) {
            System.out.println("Error\nThere are still open separators");
            for (int i = 0; i < open_separators.length(); i++) {
                System.out.println(open_separators.get(i).getSeparator(input));
            }
            return false;
        }

        // print the largest enclosed
        System.out.println("Largest enclosed: '" + largest_enclosed + "'");
        System.out.println("Length of largest enclosed: " + largest_enclosed.length());

        return true;
    }

    public void print() {
        // run isValid and print the result
        System.out.println("String is valid: " + isValid());
    }

}

public class Main {
    // test the BalancedParentheses  class
    public static void main(String[] args) {
        System.out.println("Testing the BalancedParentheses class");

        // Test cases
        BalancedParentheses thing = new BalancedParentheses("(hello)(()){[{[goodbye]}]}");
        thing.print();

        BalancedParentheses thing2 = new BalancedParentheses("(([is this balanced?))]{}{[]}{}");
        thing2.print(); 
        
        // Enter your own input
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