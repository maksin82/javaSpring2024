package lectures.lec29.solid.dependencyinversion;

import lectures.lec29.solid.singleresponsobility.Account;

import java.util.Collections;
import java.util.List;

public class OracleAccountRepository implements AccountRepository {

	@Override
	public List<Account> getListAccount() {
		return Collections.emptyList();
	}

}
