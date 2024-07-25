import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello world! Welcome to the Bank of Matt!");
        while (true) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == -1) {
                break;
            } else if (choice == 1) {
                System.out.print("Enter your account number: ");
                int accountNumber = scanner.nextInt();
    1            scanner.nextLine(); // Consume newline
                BankAccount account = findAccountByNumber(accounts, accountNumber);
                if (account != null) {
                    mainMenu(account, scanner, accounts);
                } else {
                    System.out.println("Account not found.");
                }
            } else if (choice == 2) {
                BankAccount newAccount = new BankAccount();
                accounts.add(newAccount);
                System.out.println("New account created: " + newAccount);
                mainMenu(newAccount, scanner, accounts);
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static BankAccount findAccountByNumber(ArrayList<BankAccount> accounts, int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private static void mainMenu(BankAccount account, Scanner scanner, ArrayList<BankAccount> accounts) {
        while (true) {
            System.out.println("Hello " + account.getAccountHolderName() + "!");
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Your account balance is $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Please enter the account number to transfer to: ");
                    int toAccountNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    BankAccount toAccount = findAccountByNumber(accounts, toAccountNumber);
                    if (toAccount != null) {
                        System.out.print("Please enter the amount to transfer: ");
                        double transferAmount = scanner.nextDouble();
                        account.transfer(toAccount, transferAmount);
                        System.out.println("Updated account details: " + account);
                        System.out.println("Recipient account details: " + toAccount);
                    } else {
                        System.out.println("Account doesn't exist.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Updated account details: " + account);
        }
    }
}
