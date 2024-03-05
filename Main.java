import java.util.ArrayList;

class Queue {
    private ArrayList<String> queue = new ArrayList<String>();
    
    public Queue(String initValue) {
        queue.add(initValue);
    }

    public Queue() {}
    
    // fix the enqueue and dequeue methods
    public void enqueue(String newValue) {
        queue.add(newValue);
    }

    public String dequeue() {
        if (queue.size() == 0) {
            return "Queue is empty";
        } else {
            return "Removed from the queue: " + queue.remove(0);
        }
    }

    public void print() {
        if (queue.size() == 0) {
            System.out.println("Queue is empty");
        } else {
            for (String value : queue) {
                System.out.println(value);
            }
        }
    }

    public int length() {
        return queue.size();
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
        System.out.println("Adding " + customer);
        if (window1.length() == 0) {
            window1.enqueue(customer);
        } else if (window2.length() == 0) {
            window2.enqueue(customer);
        } else if (window3.length() == 0) {
            window3.enqueue(customer);
        } else if (window4.length() == 0) {
            window4.enqueue(customer);
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

    public boolean randomChance() {
        return Math.random() < 0.5;
    }

    public void serveCustomer() {
        if (window1 != null) {
            if (randomChance()) {
                window1.dequeue();
            }
        }
        if (window2 != null) {
            if (randomChance()) {
                window2.dequeue();
            }
        }
        if (window3 != null) {
            if (randomChance()) {
                window3.dequeue();
            }
        }
        if (window4 != null) {
            if (randomChance()) {
                window4.dequeue();
            }
        }
    }

    public void timeTick() {
        int new_customers = (int) (Math.random() * 8);
        time++;
        
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