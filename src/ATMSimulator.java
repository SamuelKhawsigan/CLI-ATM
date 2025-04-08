import java.util.Scanner;

public class ATMSimulator {
    private static Account currentAccount;
    private static Scanner scanner = new Scanner(System.in);

    // Sample accounts for testing
    private static Account[] accounts = {
        new Account("123456", "1234", 1000.00),
        new Account("654321", "4321", 500.00)
    };

    public static void main(String[] args) {
        System.out.println("=== ATM Simulator ===");
        
        if (login()) {
            showMainMenu();
        } else {
            System.out.println("Login failed. Exiting...");
        }
    }

    private static boolean login() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                if (account.validatePin(pin)) {
                    currentAccount = account;
                    return true;
                }
            }
        }
        return false;
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    viewTransactions();
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ATM operations methods go here

    private static void checkBalance() {
        System.out.printf("Current balance: $%.2f%n", 
            currentAccount.getBalance());
    }

    private static void deposit() {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.printf("$%.2f deposited successfully.%n", amount);
        } else {
            System.out.println("Invalid amount!");
        }
    }

    private static void withdraw() {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        try {
            if (amount > 0) {
                currentAccount.withdraw(amount);
                System.out.printf("$%.2f withdrawn successfully.%n", amount);
            } else {
                System.out.println("Invalid amount!");
            }
        } catch (InsufficientFundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void viewTransactions() {
        System.out.println("\nTransaction History:");
        for (String transaction : currentAccount.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
    
}