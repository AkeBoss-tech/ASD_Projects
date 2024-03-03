
class Queue {
    private String value;
    private Queue next;
    
    public Queue(String initValue, Queue initPrevious) {
        value = initValue;
        next = initPrevious;
    }

    public Queue() {
        value = null;
        next = null;
    }
    
    // fix the enqueue and dequeue methods
    public String enqueue(String newValue) {
        if (next.length() == 0) {
            next = new Queue(newValue, this);
            return "Added to the end of the queue";
        } else {
            return next.enqueue(newValue);
        }
    }

    public String dequeue() {
        if (next.length() == 0) {
            return "Queue is empty";
        } else {
            next = next.next;
            return "Removed from the queue";
        }
    }

    public void peek() {
        if (next.length() == 0) {
            System.out.println("Queue is empty");
        } else {
            System.out.println(next.value);
        }
    }

    public int length() {
        if (value == null) {
            return 0;
        }

        if (next == null) {
            return 1;
        }

        if (next.length() == 0) {
            return 1;
        } else {
            return 1 + next.length();
        }
    }

    public void print() {
        if (value == null) {
            return;
        }
        System.out.println(value);
        if (next == null) {
            return;
        }
        if (next.length() != 0) {
            next.print();
        }
    }
}

class TicketWindows {
    private Queue window1 = new Queue();
    private Queue window2 = new Queue();
    private Queue window3 = new Queue();
    private Queue window4 = new Queue();

    private int time = 0;

    // constructor
    public TicketWindows() {}
    
    public void addToShortest(String customer) {
        if (window1.length() == 0) {
            window1 = new Queue(customer, null);
        } else if (window2.length() == 0) {
            window2 = new Queue(customer, null);
        } else if (window3.length() == 0) {
            window3 = new Queue(customer, null);
        } else if (window4.length() == 0) {
            window4 = new Queue(customer, null);
        } else {
            if (window1.length() <= window2.length() && window1.length() <= window3.length() && window1.length() <= window4.length()) {
                window1.enqueue(customer);
            } else if (window2.length() <= window1.length() && window2.length() <= window3.length() && window2.length() <= window4.length()) {
                window2.enqueue(customer);
            } else if (window3.length() <= window1.length() && window3.length() <= window2.length() && window3.length() <= window4.length()) {
                window3.enqueue(customer);
            } else {
                window4.enqueue(customer);
            }
        }
    }

    public void serveCustomer() {
        if (window1 != null) {
            window1.dequeue();
        }
        if (window2 != null) {
            window2.dequeue();
        }
        if (window3 != null) {
            window3.dequeue();
        }
        if (window4 != null) {
            window4.dequeue();
        }
    }

    public void timeTick() {
        int new_customers = (int) (Math.random() * 4);
        time++;
        
        for (int i = 0; i < new_customers; i++) {
            addToShortest("Customer " + i + " at time " + time);
        }
        
        // serveCustomer();
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
    // test the TicketWindows class
    public static void main(String[] args) {
        TicketWindows rack = new TicketWindows();

        for (int i = 0; i < 10; i++) {
            rack.timeTick();
            rack.print();
            rack.totalCustomers();
            System.out.println();
        }
        
    }
}