package lectures.exception;

import lectures.exception.bank.*;

public class Main {

	public static void main(String[] args) throws SomethingIsReallyWrongException {
		int a = 9;
		int b = 0;
		divideInts(a, b);
		System.out.println("_-_-_-_-_-_-_-_-_\n");

		Account account = new Account();
		System.out.println(account.getBalance());
		account.credit(-5);
		try { // можно try на весь блок
			System.out.println(account.getBalance());
		}
		catch (SomethingIsReallyWrongException e) {
			e.printStackTrace();
		}
		account.credit(100);
		account.deactivate();
		System.out.println(debitFromAccount(account, 90));
		System.out.println(account.getBalance());
		account.activate();
		System.out.println(debitFromAccount(account, 120));
		System.out.println(account.getBalance());
	}

	// public static String debitFromAccount(Account acct, double amt) {
	// try {
	// acct.debet(amt);
	// return "OK";
	// } catch (RuntimeException e) {
	// return "Something wrong. " + e.getMessage();
	// }
	// }
	public static String debitFromAccount(Account acct, double amt) {
		try {
			acct.debet(amt);
			return "OK";
		}
		catch (InsufficientFundsException e) {
			return "Not enough money";
		}
		catch (AccountDeactivatedException e) {
			return "Account deactivate";
		}
		catch (BankException e) {
			return e.getMessage();
		}
	}

	public static void divideInts(int a, int b) {
		try {
			System.out.println(a / b);
		}
		catch (Exception e) {
			System.out.println("Error");
		}
	}

}
