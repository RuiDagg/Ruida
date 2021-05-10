package com.pangsir.bookstore.test;


import com.pangsir.bookstore.dao.AccountDao;
import com.pangsir.bookstore.dao.impl.AccountDaoImpl;
import com.pangsir.bookstore.entity.Account;
import org.junit.Test;

public class AccountDaoImplTest {

    private AccountDao accountDao = new AccountDaoImpl();

    @Test
    public void getAccountById() {
        Account account = accountDao.getAccountById(1);
        System.out.println(account);

    }

    @Test
    public void updateBalance() {
        accountDao.updateBalance(1,5000);
        Account account = accountDao.getAccountById(1);
        System.out.println(account);
    }
}
