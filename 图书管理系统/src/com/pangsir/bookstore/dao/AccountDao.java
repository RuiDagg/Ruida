package com.pangsir.bookstore.dao;

import com.pangsir.bookstore.entity.Account;

public interface AccountDao {

    //根据id获取Account对象
    Account getAccountById(Integer accountId);

    //根据传入的accountId，和要更新的钱数，来扣除账户的余额
    void updateBalance(Integer accountId,double amount);
}
