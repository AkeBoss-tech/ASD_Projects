package compiled;
import java.util.Scanner;

interface Band {
    public void chabeeBanao(int chaabee);
    public void bandKerBhai(int chaabee);
    public void kholo(int chaabee);
    public boolean locked();
}

abstract class Khaata {
    protected double paisa;
    
    public Khaata() {
        paisa = 0;
    }

    public void bholPaisa() {
        System.out.println("Paisa: ₹" + paisa);
    }

    public void rakhana(double dhan) {
        paisa += dhan;
    }

    public boolean nikhaalo(double dhan) {
        if (paisa >= dhan) {
            paisa -= dhan;
            return true;
        }

        System.out.println("paisa nehain hai mera yaar");
        return false;
    };
}    

class KhaateKeeJaanch extends Khaata implements Band {
    private int chaabee;
    private boolean locked = true;

    @Override
    public void chabeeBanao(int chaabee) {
        this.chaabee = chaabee;
        System.out.println("Chabee Bengaya");
    }

    @Override
    public void bandKerBhai(int chaabee) {
        if (chaabee == this.chaabee) {
            locked = true;
            System.out.println("Khaata band ho Gaya");
            return;
        }
        System.out.println("Kaam Nahin Karata Bro");
    }

    @Override
    public void kholo(int chaabee) {
        if (chaabee == this.chaabee) {
            locked = false;
            System.out.println("Khaata Khul Gaya");
            return;
        }
        System.out.println("Kaam Nahin Karata Bro");
    }

    @Override
    public boolean locked() {
        return locked;
    }

    public void bholRokPaisa() {
        if (locked()) {
            System.out.println("Kaam Nahin Karata Bro");
            return;
        }
        bholPaisa();
    }

    public boolean lockedNikhaalo(double dhan) {
        if (locked()) {
            System.out.println("Kaam Nahin Karata Bro");
            return false;
        }
        return nikhaalo(dhan);
    }

    public void lockedRakhana(double dhan) {
        if (locked()) {
            System.out.println("Kaam Nahin Karata Bro");
        }
        rakhana(dhan);
    }
}

public class BhaiKaBank {
    
    public static void main(String[] kya_chahiye_mera_bhai) {
        Scanner sunana = new Scanner(System.in);
        KhaateKeeJaanch khaate = new KhaateKeeJaanch();
        
        /* Make a loop that allows the user to access all the features of the khaate or leave if they want */
        while (true) {
            System.out.println("1. Chabee Lagao");
            System.out.println("2. Khaata pe Taala Lagao");
            System.out.println("3. Khaata Kholo");
            System.out.println("4. Paisa Bhol");
            System.out.println("5. Paisa Rakhana");
            System.out.println("6. Paisa Nikhaalo");
            System.out.println("7. Chhoden");
            System.out.println("Aap kya chaahate hain: ");
            int choice = sunana.nextInt();
            
            if (choice == 1) {
                System.out.println("chaabee daalo: ");
                khaate.chabeeBanao(sunana.nextInt());
            } else if (choice == 2) {
                System.out.println("chaabee daalo: ");
                khaate.bandKerBhai(sunana.nextInt());
            } else if (choice == 3) {
                System.out.println("chaabee daalo: ");
                khaate.kholo(sunana.nextInt());
            } else if (choice == 4) {
                khaate.bholRokPaisa();
            } else if (choice == 5) {
                System.out.println("Paisa daalo: ");
                khaate.lockedRakhana(sunana.nextDouble());
            } else if (choice == 6) {
                System.out.println("Paisa batao: ");
                khaate.lockedNikhaalo(sunana.nextDouble());
            } else if (choice == 7) {
                break;
            } else {
                System.out.println("Aare aare kya kar rahe ho bhai?");
                System.out.println("Kuch aur karo");
            }

            System.out.println('\n');

        }
    }
}