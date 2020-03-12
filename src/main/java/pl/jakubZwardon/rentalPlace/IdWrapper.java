package pl.jakubZwardon.rentalPlace;

import org.springframework.stereotype.Component;

@Component
public class IdWrapper {
	private int transactionIg;

	public int getTransactionIg() {
		return transactionIg;
	}

	public void setTransactionIg(int transactionIg) {
		this.transactionIg = transactionIg;
	}
	
	
}
