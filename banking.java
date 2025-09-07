import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;
    private static int nextAccountNumber = 1001;

    public Account(String name, double initialDeposit, String email, String phone) {
        this.accountNumber = nextAccountNumber++;
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phone;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

public class banking {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    public banking() {
        accounts = new Account[100];

        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    private Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        if (amount < 0) {
            System.out.println("Initial deposit must be positive.");
            return;
        }

        Account acc = new Account(name, amount, email, phone);
        accounts[accountCount++] = acc;
        System.out.println("Account created successfully with Account Number: " + acc.getAccountNumber());
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        Account acc = findAccount(accNo);
        if (acc != null) {
            if (acc.deposit(amount)) {
                System.out.println("Deposit successful.");
            } else {
                System.out.println("Amount must be positive.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer

        Account acc = findAccount(accNo);
        if (acc != null) {
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Invalid amount or insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void mainMenu() {
        while (true) {
            System.out.println("\nWelcome to the Banking Application!");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear input buffer

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        banking app = new banking();
        app.mainMenu();
    }
}
