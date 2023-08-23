package atmInterfaceProject;

import java.util.*;

public class TransactionHistory {
	
	private ArrayList<Transaction> transactionHistory;
	
	public TransactionHistory() {
		transactionHistory = new ArrayList<>();
	}

	public ArrayList<Transaction> getTransactionHistory() {
		return transactionHistory;
	}

	public void addTransaction(Transaction transaction) {
		this.transactionHistory.add(transaction);
	}
	
	
	
	
	
	
}
