package food.dao;

import java.util.List;

import food.entity.Account;

public interface AccountDAO {

	List<Account> listAccounts();
	
	List<Account> listLockAccounts();

	Account getAccount(int id);

	Account findByEmail(String email);

	boolean insert(Account account);

	boolean update(Account account);

	boolean delete(Account account);

	Account findByPhone(String phone);

}
