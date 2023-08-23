package atmInterfaceProject;

import java.math.BigDecimal;

public class AccountNumGenerator {
	
	private static BigDecimal accountNumber = new BigDecimal("1000000000000000");

	public String generateNumber() {
		
		
		accountNumber = accountNumber.add(BigDecimal.ONE);
		String accountNum = accountNumber.toString();
        return accountNum;
	}

}
