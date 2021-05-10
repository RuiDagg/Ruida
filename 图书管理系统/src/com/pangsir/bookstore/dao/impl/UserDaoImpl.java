package com.pangsir.bookstore.dao.impl;

import com.pangsir.bookstore.dao.UserDao;
import com.pangsir.bookstore.entity.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {


    @Override
    public User getUserByUsername(String username) {
        String sql = "select userId,username,accountId from userinfo where username = ?";
        return getBean(sql,username);
    }
}
