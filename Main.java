class ListNode {
    private Object value;
    private ListNode next;
    
    public ListNode(Object initValue, ListNode initNext) {
        value = initValue;
        next = initNext;
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
}

class MagazineRack {
    // use the list nodes to store the magazine titles
    private ListNode list;

    // constructor
    public MagazineRack() {
        list = null;
    }

    // add a magazine to the rack
    public void add(String mag) {
        list = new ListNode(mag, list);
    }

    // remove a magazine from the rack
    public void remove(String mag) {
        ListNode current = list;
        ListNode previous = null;
        while (current != null) {
            String currTitle = (String) current.getValue();
            if (currTitle.equals(mag)) {
                if (previous == null) {
                    list = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }
    }

    public String remove(int index) {
        ListNode current = list;
        ListNode previous = null;
        int i = 0;
        while (current != null) {
            if (i == index) {
                if (previous == null) {
                    list = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                return (String) current.getValue();
            }
            previous = current;
            current = current.getNext();
            i++;
        }
        return "";
    }

    // print the titles of the magazines in the rack
    public void print() {
        ListNode current = list;
        while (current != null) {
            String mag = (String) current.getValue();
            System.out.println(mag.toString());
            current = current.getNext();
        }
    }

    public String toString() {
        return list.toString();
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
        
        rack.remove("Sports Illustrated");
        System.out.println("Removing 'Sports Illustrated' the rack contains:");
        rack.print();
        
        System.out.println("Removing 3 index");
        System.out.println("Removed " + rack.remove(3));
        rack.print();
        
        System.out.println("Adding 'Technology Review' the rack contains:");
        rack.add("Technology Review");
        rack.print();
    }
}