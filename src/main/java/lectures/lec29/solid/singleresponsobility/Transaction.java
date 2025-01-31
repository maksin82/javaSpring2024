package lectures.lec29.solid.singleresponsobility;

import java.time.Instant;

public class Transaction {

	private final Instant timestamp;

	private final double amount;

	public Instant getTimestamp() {
		return timestamp;
	}

	public double getAmount() {
		return amount;
	}

	public Transaction(Instant timestamp, double amount) {
		this.timestamp = timestamp;
		this.amount = amount;
	}

}
