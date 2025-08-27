import java.util.Scanner;

// -------- BankAccount Class --------
class BankAccount {
    private double balance;   // Encapsulation
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Successfully deposited: " + amount);
        } else {
            System.out.println("❌ Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Successfully withdrawn: " + amount);
        } else {
            System.out.println("❌ Insufficient funds or invalid amount!");
        }
    }
}

// -------- User Class --------
class User {
    private String name;
    private String cardNumber;
    private int pin;
    private BankAccount account;

    public User(String name, String cardNumber, int pin, BankAccount account) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.account = account;
    }

    public boolean validatePIN(int enteredPin) {
        return this.pin == enteredPin;
    }

    public BankAccount getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }
}

// -------- ATM Class --------
class ATM {
    private User user;
    private Scanner scanner = new Scanner(System.in);

    public ATM(User user) {
        this.user = user;
    }

    public void start() {
        System.out.println("💳 Welcome to ATM Machine");
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (user.validatePIN(enteredPin)) {
            System.out.println("✅ Login successful! Hello, " + user.getName());
            menu();
        } else {
            System.out.println("❌ Invalid PIN! Exiting...");
        }
    }

    private void menu() {
        int choice;
        do {
            System.out.println("\n----- ATM Menu -----");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("💰 Balance: " + user.getAccount().getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double dep = scanner.nextDouble();
                    user.getAccount().deposit(dep);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double wit = scanner.nextDouble();
                    user.getAccount().withdraw(wit);
                    break;
                case 4:
                    System.out.println("👋 Thank you for using ATM!");
                    break;
                default:
                    System.out.println("❌ Invalid choice!");
            }
        } while (choice != 4);
    }
}

// -------- Main Class --------
public class main {
    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(5000.0);
        User user1 = new User("Vijay", "1234-5678-9999", 1234, account1);
        ATM atm = new ATM(user1);
        atm.start();
    }
}
