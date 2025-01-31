package lectures.exception.bank;

public class Account {

	private double balance = 0.0;

	private boolean active = true;

	public void credit(double amt) {
		balance += amt;
	}

	public void debet(double amt) {
		if (!active) {
			throw new AccountDeactivatedException();
		}
		if (amt > balance) {
			throw new InsufficientFundsException();
		}
		balance -= amt;
	}

	public double getBalance() throws SomethingIsReallyWrongException {
		if (balance < 0) {
			throw new SomethingIsReallyWrongException("Impossible!");
		}
		return balance;
	}

	public void deactivate() {
		this.active = false;
	}

	public void activate() {
		this.active = true;
	}

}
