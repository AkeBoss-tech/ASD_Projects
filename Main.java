class ListNode {
    private Object value;
    private ListNode next;
    private ListNode previous;
    
    public ListNode(Object initValue, ListNode initNext, ListNode initPrevious) {
        value = initValue;
        next = initNext;
        previous = initPrevious;
    }
    
    public Object getValue() {
        return value;
    }
    
    public ListNode getNext() {
        return next;
    }
    
    public void setValue(Object theNewValue) {
        value = theNewValue;
    }
    
    public void setNext(ListNode theNewNext) {
        next = theNewNext;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode theNewPrevious) {
        previous = theNewPrevious;
    }
    
    public String toString() {
        return value.toString();
    }
}

class MagazineRack {
    // use the list nodes to store the magazine titles in a circular doubly linked list
    private ListNode list;

    // constructor
    public MagazineRack() {
        list = null;
    }

    // add a magazine to the rack
    public void add(Object obj) {
        if (list == null) {
            list = new ListNode(obj, null, null);
            list.setNext(list);
            list.setPrevious(list);
        } else {
            ListNode node = new ListNode(obj, list, list.getPrevious());
            list.getPrevious().setNext(node);
            list.setPrevious(node);
        }
    }

    // remove a magazine from the rack
    public void remove(Object obj) {
        ListNode current = list;
        do {
            if (current.getValue().equals(obj)) {
                current.getPrevious().setNext(current.getNext());
                current.getNext().setPrevious(current.getPrevious());
                if (current == list) {
                    list = current.getNext();
                }
                return;
            }
            current = current.getNext();
        } while (current != list);
    }

    // print the contents of the rack
    public void print() {
        ListNode current = list;
        do {
            System.out.println(current.getValue());
            current = current.getNext();
        } while (current != list);
    }

    // print the contents of the rack in reverse order
    public void printReverse() {
        System.out.println("The rack contains reversed:");
        ListNode current = list.getPrevious();
        do {
            System.out.println(current.getValue());
            current = current.getPrevious();
        } while (current != list.getPrevious());
    }

    // return the number of magazines in the rack
    public int size() {
        int count = 0;
        ListNode current = list;
        do {
            count++;
            current = current.getNext();
        } while (current != list);
        return count;
    }

    public void printNum(int num) {
        ListNode current = list;
        for (int i = 0; i < num; i++) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }
    public void printNumReverse(int num) {
        ListNode current = list;
        for (int i = 0; i < num; i++) {
            System.out.println(current.getValue());
            current = current.getPrevious();
        }
    }
}

public class Main {
    // test the MagazineRack class
    public static void main(String[] args) {
        MagazineRack rack = new MagazineRack();
        rack.add("Times of India");
        rack.add("Dhanak Bhaskar");
        rack.add("Sports Illustrated");
        rack.add("Car and Driver");
        rack.add("Road & Track");

        System.out.println("The rack contains:");
        rack.print();
        rack.printReverse();
        
        rack.remove("Sports Illustrated");
        System.out.println("Removing 'Sports Illustrated' the rack contains:");
        rack.print();
        rack.printReverse();
        
        System.out.println("Adding 'Technology Review' the rack contains:");
        rack.add("Technology Review");
        rack.print();
        rack.printReverse();
        System.out.println("Here's ten things in the rack with looping:");
        rack.printNum(10);
        System.out.println("Here's ten things in the rack with looping in reverse:");
        rack.printNumReverse(10);
    }
}