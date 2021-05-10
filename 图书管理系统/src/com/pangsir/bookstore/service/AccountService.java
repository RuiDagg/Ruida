package com.pangsir.bookstore.service;

import com.pangsir.bookstore.dao.AccountDao;
import com.pangsir.bookstore.dao.impl.AccountDaoImpl;
import com.pangsir.bookstore.entity.Account;

public class AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    public Account getAccountById(Integer accountId){
        return accountDao.getAccountById(accountId);
    }
}
