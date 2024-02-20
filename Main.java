public class Main {
    // test the MagazineRack class
    public static void main(String[] args) {
        MagazineRack rack = new MagazineRack();
        rack.add(new String("Time"));
        rack.add(new String("People"));
        rack.add(new String("Sports Illustrated"));
        rack.add(new String("Car and Driver"));
        rack.add(new String("Road & Track"));
        System.out.println("The rack contains:");
        rack.print();
        rack.remove(new String("Sports Illustrated"));
        System.out.println("Removing something the rack contains:");
        rack.print();
    }
}