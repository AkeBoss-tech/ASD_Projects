import java.util.Scanner;

interface Lockable {
    public void setKey(int key);
    public void lock(int key);
    public void unlock(int key);
    public boolean locked();
}

abstract class Account {
    protected double balance;
    
    public Account() {
        balance = 0;
    }

    public void printBalance() {
        System.out.println("Balance: " + balance);
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }

        System.out.println("Not enough balance");
        return false;
    };

}    

class CheckingAccount extends Account implements Lockable {
    private int key;
    private boolean locked = true;

    @Override
    public void setKey(int key) {
        this.key = key;
        System.out.println("Key Set");
    }

    @Override
    public void lock(int key) {
        if (key == this.key) {
            locked = true;
            System.out.println("Account Locked");
            return;
        }

        System.out.println("Doesn't Work");
    }

    @Override
    public void unlock(int key) {
        if (key == this.key) {
            locked = false;
            System.out.println("Account Unlocked");
            return;
        }
        System.out.println("Doesn't Work");
    }

    @Override
    public boolean locked() {
        return locked;
    }

    public void printLockedBalance() {
        if (locked()) {
            System.out.println("You are not allowed");
            return;
        }
        printBalance();
    }

    public boolean lockedWithdraw(double amount) {
        if (locked()) {
            System.out.println("You are not allowed");
            return false;
        }
        return withdraw(amount);
    }

    public void lockedDeposit(double amount) {
        if (locked()) {
            System.out.println("You are not allowed");
        }
        deposit(amount);
    }
}

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CheckingAccount account = new CheckingAccount();
        
        /* Make a loop that allows the user to access all the features of the account or leave if they want */
        while (true) {
            System.out.println("1. Set Key");
            System.out.println("2. Lock");
            System.out.println("3. Unlock");
            System.out.println("4. Print Balance");
            System.out.println("5. Deposit");
            System.out.println("6. Withdraw");
            System.out.println("7. Exit");
            System.out.println("Enter a number: ");
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                System.out.println("Enter the key: ");
                account.setKey(scanner.nextInt());
            } else if (choice == 2) {
                System.out.println("Enter the key: ");
                account.lock(scanner.nextInt());
            } else if (choice == 3) {
                System.out.println("Enter the key: ");
                account.unlock(scanner.nextInt());
            } else if (choice == 4) {
                account.printLockedBalance();
            } else if (choice == 5) {
                System.out.println("Enter the amount: ");
                account.lockedDeposit(scanner.nextDouble());
            } else if (choice == 6) {
                System.out.println("Enter the amount: ");
                account.lockedWithdraw(scanner.nextDouble());
            } else if (choice == 7) {
                break;
            }

            System.out.println('\n');

        }
    }
}