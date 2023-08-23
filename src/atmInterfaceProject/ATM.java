package atmInterfaceProject;

import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

public class ATM {

	private static Scanner s = new Scanner(System.in);
	private static Bank bank = new Bank();
	private static User currentUser;

	//Constructor
	public ATM() {}


	//Function to start ATM
	public void start() {

		while (true) {
			
			System.out.println("--------WELCOME TO ATM INTERFACE---------");
			System.out.println("************************************************");
			System.out.println("1. Existing User");
			System.out.println("2. New User (Create Account)");
			System.out.println("3. Exit");
			System.out.println("************************************************");
			System.out.print("Enter your choice: ");
			
			String choice = s.nextLine();

			switch (choice) {
			case "1":
				existingUserLogin();
				break;
			case "2":
				createUser();
				break;
			case "3":
				System.out.println("================");
				System.out.println("Session Ended");
				System.out.println("================");
				s.close();
				return;
			default:
				System.out.println("Invalid choice");
			}
		}

	}

	// Function to implement login functionality for existing user
	private static void existingUserLogin() {

		//Asking User Name and pin from customer
		System.out.print("Enter User Name: ");
		String userName = s.nextLine();
		System.out.print("Enter PIN: ");
		String pin = s.nextLine();

		//Getting user details from Bank class and if user and credentials is valid then provide ATM services
		currentUser = bank.getUser(userName);
		if(currentUser != null && currentUser.getPin().equals(pin)) {
			System.out.println("**************************************");
			System.out.println("Welcome, Mr. "+currentUser.getName());
			System.out.println("**************************************");
			provideATMServices(currentUser);
		}else {
			System.out.println("==================================================================");
			System.out.println("Invalid User Name or Pin. Please try again with valid credential");
			System.out.println("==================================================================");
		}

	}

	// Function to implement show ATM services for Existing User
	private static void provideATMServices(User user) {

		while (true) {

			System.out.println("1. Balance Enquiry");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");
			System.out.println("4. Transaction History");
			System.out.println("5. Logout");
			System.out.println("**************************************");
			System.out.print("Enter your choice: ");

			String choice = s.nextLine();

			switch (choice) {
			case "1":
				balanceEnquiry(user);
				break;
			case "2":
				// Implement withdraw logic
				withdraw(user);
				break;
			case "3":
				// Implement deposit logic
				deposit(user);
				break;
			case "4":
				showTransaction(user);
				break;
			case "5":
				System.out.println("Logging out.");
				return;
			default:
				System.out.println("Invalid choice");
			}
		}

	}

	//Function to perform Deposit operation.
	private static void deposit(User user) {

		//Try-Catch block is used to handle Exception when user enters non numeric value in Amount
		try {
			System.out.print("Please enter an amount: ");
			double amount = s.nextDouble();
			s.nextLine();

			if (amount <= 0) {
				System.out.println("=================================================");
				System.out.println("Invalid amount. Please enter a positive value.");
				System.out.println("=================================================");
				return;
			}

			bank.getAccount(user.getUserName()).deposit(amount);
			System.out.println("===============================================");
			System.out.println("Amount deposited successfully: Rs." + amount);
			System.out.println("===============================================");

			Transaction newTransaction = new Transaction("Deposit", amount);
			TransactionHistory transactions = bank.getTransaction(user);
			transactions.addTransaction(newTransaction);
		} catch (InputMismatchException e) {
			System.out.println("====================================================================");
			System.out.println("Invalid input. Please enter a valid numeric value for the amount.");
			System.out.println("====================================================================");
			s.nextLine(); // Clear the input buffer
		}

	}

	// Function to implement Withdraw functionality
	private static void withdraw(User user) {

		// Try-Catch is used to handle exception if user inserts a non numeric number as an Amount or OTP
		try {
			System.out.print("Please enter an amount : ");
			double
			amount = s.nextDouble();
			s.nextLine();

			if (amount <= 0) {
				System.out.println("=================================================");
				System.out.println("Invalid amount. Please enter a positive value.");
				System.out.println("=================================================");
				return;
			}

			double availableBal = bank.getAccount(user.getUserName()).getBalance();
			if(availableBal >= amount) {
				bank.OTPverification(user);
				System.out.print("Enter the OTP : ");
				int OTP = s.nextInt();
				s.nextLine();
				if(OTP != 1234) {
					System.out.println("=================");
					System.out.println("Incorrect OTP");
					System.out.println("=================");
					return;
				}
				bank.getAccount(user.getUserName()).withdraw(amount);
				System.out.println("===================================");
				System.out.println("Please take your cash : "+amount);
				System.out.println("===================================");

				Transaction newTransaction = new Transaction("Withdraw", amount);
				TransactionHistory transactions = bank.getTransaction(user);
				transactions.addTransaction(newTransaction);
			}else {
				System.out.println("======================");
				System.out.println("Insufficient Balance");
				System.out.println("======================");
			}

		}catch(InputMismatchException e) {
			System.out.println("========================================================================");
			System.out.println("Invalid input. Please enter a valid numeric value for Amount and OTP.");
			System.out.println("========================================================================");
			s.nextLine(); // Clear the input buffer
		}
	}

	//Function to show Transaction History
	private static void showTransaction(User user) {
		TransactionHistory allTransaction = bank.getTransaction(user);
		ArrayList<Transaction> list = allTransaction.getTransactionHistory();
		System.out.println("\n===============LAST TRANSACTION DETAILS==================");
		int j = 1; //for maintaining serial number of transactions
		for(int i= list.size()-1; i>=0; i--) {
			double transactionAmount = list.get(i).getAmount();
			String transactionType = list.get(i).getTypeOfTransaction();
			System.out.printf("%d.  Type of transaction: %s\tAmount: %.2f%n", j, transactionType, transactionAmount);
			j++;
		}
		System.out.println();
	}

	// Function to implement Balance Enquery functionality

	//Function to check balance for the user
	private static void balanceEnquiry(User user) {
		Account currentAccount = bank.getAccount(user.getUserName());
		System.out.println("=================================================");
		System.out.println("Available Balance : "+currentAccount.getBalance());
		System.out.println("=================================================");

	}

	//Function to create New User

	//Function to create New User
	private void createUser() {

		//Taking name of customer
		System.out.print("Enter full name: ");
		String name = s.nextLine();

		// Validating the name criteria
		if (!name.matches("[a-zA-Z]+(\\s[a-zA-Z]+)*")) {
			System.out.println("==============================================================================================");
			System.out.println("Invalid name format. Please use only letters and spaces and name cannot start or end with space.");
			System.out.println("==============================================================================================");
			return;
		}


		//Taking UserName of customer and checking if the username is available in the bank.
		System.out.print("Enter desired User Name : ");
		String userName = s.nextLine();
		while(bank.isUserExixt(userName)) {
			System.out.println("==============================");
			System.out.println("User Name is already used.");
			System.out.println("==============================");
			System.out.println("1. Try different User Name");
			System.out.println("2. Recover password");
			System.out.println("3. Exit");
			System.out.println("==============================");

			System.out.print("Enter you choice : ");
			String option = s.nextLine();
			switch(option) {
			case "1":
				System.out.print("Enter a different User Name: ");
				userName = s.nextLine();
				break;
			case "2":
				User user = bank.getUser(userName);
				bank.OTPverification(user);
				System.out.print("\nPlease enter the OTP sent on your registered mobile : ");
				int otp = s.nextInt();
				s.nextLine();
				if(otp == 1234) {
					System.out.println("***************************");
					System.out.println("Your pin is : "+user.getPin());
					System.out.println("***************************");
					return;
				}else {
					System.out.println("***************************");
					System.out.println("Invalid OTP. Try again");
					System.out.println("***************************");
					return;
				}

			case "3":
				System.out.println("Exiting account creation.");
				return;
			}

		}

		//Taking pin from customer
		System.out.print("Enter desired PIN: ");
		String pin = s.nextLine();

		// Validating the pin criteria.
		if (!pin.matches("\\d{4}")) {
			System.out.println("===================================================");
			System.out.println("Invalid PIN format. Please enter a 4-digit PIN.");
			System.out.println("===================================================");
			return;
		}


		//Generating 16 digit Account Number using AccountNumGenerator class
		AccountNumGenerator a = new AccountNumGenerator();
		String accountNum= a.generateNumber();


		//Creating User, Account and Transaction History
		User newUser = new User(name, userName, pin);
		Account newAccount = new Account(accountNum, 1000);
		TransactionHistory newTransactionHistory = new TransactionHistory();


		//Adding User, Account and Transaction History to Bank
		bank.addUser(newUser);
		bank.addAccount(newAccount, newUser);
		bank.addTransaction(newTransactionHistory, newUser);

		//Printing message for successful creation of account with few details
		System.out.println("********************************************");
		System.out.println("Account create successfully");
		System.out.println("User Name : "+newUser.getUserName());
		System.out.println("Account Number : "+newAccount.getAccountNumber());
		System.out.println("********************************************");

	}

}
