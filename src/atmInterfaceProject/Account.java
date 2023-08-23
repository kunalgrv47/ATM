package atmInterfaceProject;

public class Account {
	
	private String accountNumber;
    private double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }



    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    //Function to perform deposit operation
    public void deposit(double amount) {
    	
    	// All validation for amount is already done in ATM class so directly adding amount
    	balance += amount;
    	
    }

    //Function to perform withdraw operation
    public void withdraw(double amount) {
    	// All validation for amount is already done in ATM class so directly substracting amount
    	balance -= amount;
    	
    }
	
}
