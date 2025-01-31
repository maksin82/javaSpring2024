package lectures.lec29.solid.dependencyinversion;

import lectures.lec29.solid.singleresponsobility.Account;

import java.util.List;

public class GoodBank {

	private final AccountRepository accountRepository;

	public GoodBank(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	List<Account> getRichPeopleAccount() {
		return accountRepository.getListAccount().stream().filter(acc -> acc.getBalans() > 1000000).toList();
	}

}
