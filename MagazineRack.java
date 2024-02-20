public class MagazineRack {
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

    // print the titles of the magazines in the rack
    public void print() {
        ListNode current = list;
        while (current != null) {
            String mag = (String) current.getValue();
            System.out.println(mag.toString());
            current = current.getNext();
        }
    }
}
