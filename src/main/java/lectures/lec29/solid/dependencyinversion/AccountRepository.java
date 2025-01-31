package lectures.lec29.solid.dependencyinversion;

import lectures.lec29.solid.singleresponsobility.Account;

import java.util.List;

public interface AccountRepository {

	List<Account> getListAccount();

}
