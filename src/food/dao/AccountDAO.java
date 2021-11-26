package food.dao;

import java.util.List;

import food.entity.Account;

public interface AccountDAO {
	
	List<Account> listAccounts();
	
	Account getAccount(int id);
	
	boolean update(Account account);
	
}
