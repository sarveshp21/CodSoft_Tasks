package CodeSoft;

import java.util.Scanner;

class BankAccount {
 private double balance;

 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 public double getBalance() {
     return balance;
 }

 public void deposit(double amount) {
     balance += amount;
 }

 public boolean withdraw(double amount) {
     if (amount <= balance) {
         balance -= amount;
         return true;
     } else {
         return false;
     }
 }
}

class ATM {
 private BankAccount userAccount;

 public ATM(BankAccount userAccount) {
     this.userAccount = userAccount;
 }

 public void withdraw(double amount) {
     if (userAccount.withdraw(amount)) {
         System.out.println("Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
     } else {
         System.out.println("Insufficient funds. Withdrawal failed.");
     }
 }

 public void deposit(double amount) {
     userAccount.deposit(amount);
     System.out.println("Deposit successful. New balance: $" + userAccount.getBalance());
 }

 public void checkBalance() {
     System.out.println("Current balance: $" + userAccount.getBalance());
 }
}

public class ATMProgram {
 public static void main(String[] args) {
	 BankAccount userAccount = new BankAccount(1000);

     ATM atm = new ATM(userAccount);

     Scanner scanner = new Scanner(System.in);

     while (true) {
         System.out.println("\nATM Menu:");
         System.out.println("1. Withdraw");
         System.out.println("2. Deposit");
         System.out.println("3. Check Balance");
         System.out.println("4. Exit");

         System.out.print("Enter your choice (1-4): ");
         int choice = scanner.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter withdrawal amount: $");
                 double withdrawAmount = scanner.nextDouble();
                 atm.withdraw(withdrawAmount);
                 break;
             case 2:
                 System.out.print("Enter deposit amount: $");
                 double depositAmount = scanner.nextDouble();
                 atm.deposit(depositAmount);
                 break;
             case 3:
                 atm.checkBalance();
                 break;
             case 4:
                 System.out.println("Thank you for using the ATM. Goodbye!");
                 System.exit(0);
             default:
                 System.out.println("Invalid choice. Please enter a number between 1 and 4.");
         }
     }
 }
}
