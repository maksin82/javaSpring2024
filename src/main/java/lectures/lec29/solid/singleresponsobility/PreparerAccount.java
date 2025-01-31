package lectures.lec29.solid.singleresponsobility;

public class PreparerAccount {

	// для принципа разделения ответственности
	public void printStatement(Account account) {
		System.out.println("account " + account.getNumber());
		System.out.println("balance " + account.getBalans());
		for (Transaction transaction : account.getTransactions()) {
			System.out.println(transaction.getAmount() + " on " + transaction.getTimestamp());
		}
	}

}
