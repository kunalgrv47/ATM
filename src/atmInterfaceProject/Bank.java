package atmInterfaceProject;

import java.util.*;

public class Bank {
	
	private Map<String, User> users;  // key:userName and Value: User 
    private Map<String, Account> accounts; //Key:username and Value: Account
    private Map<String, TransactionHistory> transactionHistory;

    public Bank() {
        this.users = new HashMap<>();
        this.accounts = new HashMap<>();
        this.transactionHistory = new HashMap<>();
    }



    public void addUser(User user) {
        users.put(user.getUserName(), user);
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public void addAccount(Account account, User user) {
        accounts.put(user.getUserName(), account);
    }

    public Account getAccount(String userName) {
        return accounts.get(userName);
    }

	public TransactionHistory getTransaction(User user) {
		return transactionHistory.get(user.getUserName());
	}

	
	public void addTransaction(TransactionHistory transaction, User user) {
		this.transactionHistory.put(user.getUserName(), transaction);
	}
	
	public void OTPverification(User user) {
		System.out.println("OTP is : "+1234);	
	}

	public boolean isUserExixt(String userName) {
		return users.containsKey(userName);
		
	}
	

}
