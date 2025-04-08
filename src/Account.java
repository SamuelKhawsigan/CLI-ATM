import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();

    }


     // Getters and setters
     public String getAccountNumber() { return accountNumber; }
     public double getBalance() { return balance; }
     public ArrayList<String> getTransactionHistory() { return transactionHistory; }
 
     public boolean validatePin(String enteredPin) {
         return pin.equals(enteredPin);
     }
 
     public void deposit(double amount) {
         balance += amount;
         transactionHistory.add("Deposit: +$" + amount);
     }
 
     public void withdraw(double amount) throws InsufficientFundException {
         if (amount > balance) {
             throw new InsufficientFundException();
         }
         balance -= amount;
         transactionHistory.add("Withdrawal: -$" + amount);
     }
 }
