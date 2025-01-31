package lectures.lec29.solid.dependencyinversion;

public class Main {

	public static void main(String[] args) {
		AccountRepository accountRepository = new OracleAccountRepository();
		GoodBank realBank = new GoodBank(accountRepository);

		AccountRepository accountRepositoryRich = new MockAccountRepository();
		GoodBank testBank = new GoodBank(accountRepositoryRich);
		System.out.println(testBank.getRichPeopleAccount().get(0).getNumber());

	}

}
