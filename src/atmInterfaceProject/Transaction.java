package atmInterfaceProject;

public class Transaction {
	
	private String typeOfTransaction;
	private double amount;
	
	public Transaction(String typeOfTransaction, double amount) {
		this.typeOfTransaction = typeOfTransaction;
		this.amount = amount;
	}

	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}

	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
