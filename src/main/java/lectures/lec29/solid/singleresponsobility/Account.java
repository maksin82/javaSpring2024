package lectures.lec29.solid.singleresponsobility;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private final String number;

	private double balans;

	private final List<Transaction> transactions = new ArrayList<>();

	public Account(String number, double initialBalans) {
		this.number = number;
		this.balans = initialBalans;
	}

	public void credit(double amount) {
		balans += amount;
		transactions.add(new Transaction(Instant.now(), amount));
	}

	public void debet() {

	}

	public String getNumber() {
		return number;
	}

	public double getBalans() {
		return balans;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

}
