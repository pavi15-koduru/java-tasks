 import java.util.*;

class Account {
    String userId;
    String userPin;
    double balance;
    List<String> transactionHistory;

    public Account(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println("- " + transaction);
            }
        }
    }
}

public class ATMInterface {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Account> accounts = new HashMap<>();
    static Account currentAccount;

    public static void main(String[] args) {
        // Creating a demo user
        accounts.put("user123", new Account("user123", "1234", 1000.00));

        System.out.println("=== Welcome to the ATM Interface ===");

        if (login()) {
            int choice;
            do {
                System.out.println("\nChoose an option:");
                System.out.println("1. Transactions History");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> currentAccount.showTransactionHistory();
                    case 2 -> withdraw();
                    case 3 -> deposit();
                    case 4 -> transfer();
                    case 5 -> System.out.println("Thank you for using the ATM.");
                    default -> System.out.println("Invalid option!");
                }
            } while (choice != 5);
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }

    static boolean login() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (accounts.containsKey(userId) && accounts.get(userId).userPin.equals(pin)) {
            currentAccount = accounts.get(userId);
            System.out.println("Login successful.");
            return true;
        } else {
            return false;
        }
    }

    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= currentAccount.balance) {
            currentAccount.balance -= amount;
            currentAccount.addTransaction("Withdrew ₹" + amount);
            System.out.println("Withdrawal successful. Current balance: ₹" + currentAccount.balance);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
    }

    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            currentAccount.balance += amount;
            currentAccount.addTransaction("Deposited ₹" + amount);
            System.out.println("Deposit successful. Current balance: ₹" + currentAccount.balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    static void transfer() {
        System.out.print("Enter recipient User ID: ");
        scanner.nextLine(); // consume leftover newline
        String recipientId = scanner.nextLine();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (!accounts.containsKey(recipientId)) {
            System.out.println("Recipient account not found.");
        } else if (amount <= 0 || amount > currentAccount.balance) {
            System.out.println("Invalid amount or insufficient balance.");
        } else {
            Account recipient = accounts.get(recipientId);
            currentAccount.balance -= amount;
            recipient.balance += amount;
            currentAccount.addTransaction("Transferred ₹" + amount + " to " + recipientId);
            recipient.addTransaction("Received ₹" + amount + " from " + currentAccount.userId);
            System.out.println("Transfer successful.");
        }
    }
}

