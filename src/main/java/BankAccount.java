import java.util.Scanner;

public class BankAccount {
    private String accountHolderName;
    private double balance;
    private int accountNumber;
    private static int nextAccountNumber = 1;

    // Constructor with all attributes
    public BankAccount(String accountHolderName, double balance, int accountNumber) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    // Constructor with name and balance, auto-generates account number
    public BankAccount(String accountHolderName, double balance) {
        this(accountHolderName, balance, nextAccountNumber++);
    }

    // No-parameter constructor for interactive account creation
    public BankAccount() {
        this.accountNumber = nextAccountNumber++;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's make a new account!");
        System.out.print("What is the name for the account? ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("What is the beginning balance for the account? ");
        this.balance = scanner.nextDouble();
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + " into account number " + accountNumber);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + " from account number " + accountNumber);
        } else if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    // Method to transfer an amount to another account
    public void transfer(BankAccount toAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transferred $" + amount + " from account number " + this.accountNumber + " to account number " + toAccount.getAccountNumber());
        } else if (amount > balance) {
            System.out.println("Insufficient funds for transfer.");
        } else {
            System.out.println("Transfer amount must be positive.");
        }
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolderName + ", Balance: $" + balance;
    }
}
