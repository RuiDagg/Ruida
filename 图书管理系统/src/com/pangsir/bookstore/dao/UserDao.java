package com.pangsir.bookstore.dao;

import com.pangsir.bookstore.entity.User;

public interface UserDao {

    //根据用户名获取User对象
    User getUserByUsername(String username);
}
