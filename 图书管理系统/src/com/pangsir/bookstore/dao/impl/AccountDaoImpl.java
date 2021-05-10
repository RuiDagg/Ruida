package com.pangsir.bookstore.dao.impl;

import com.pangsir.bookstore.dao.AccountDao;
import com.pangsir.bookstore.entity.Account;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {
    @Override
    public Account getAccountById(Integer accountId) {
        String sql = "select accountId,balance from account where accountId = ?";
        return getBean(sql,accountId);
    }

    @Override
    public void updateBalance(Integer accountId, double amount) {
        String sql = "update account set balance = balance - ? where accountId = ?";
        update(sql,amount,accountId);
    }
}
