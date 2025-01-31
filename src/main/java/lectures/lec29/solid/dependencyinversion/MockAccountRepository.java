package lectures.lec29.solid.dependencyinversion;

import lectures.lec29.solid.singleresponsobility.Account;

import java.util.List;

public class MockAccountRepository implements AccountRepository {

	@Override
	public List<Account> getListAccount() {
		return List.of(new Account("poor", 100), new Account("rich", 1000000000));
	}

}
