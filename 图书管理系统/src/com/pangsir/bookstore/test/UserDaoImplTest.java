package com.pangsir.bookstore.test;


import com.pangsir.bookstore.dao.UserDao;
import com.pangsir.bookstore.dao.impl.UserDaoImpl;
import com.pangsir.bookstore.entity.User;
import org.junit.Test;

public class UserDaoImplTest {

    private UserDao userDao= new UserDaoImpl();


    @Test
    public void getUserByUsername() {
        User user = userDao.getUserByUsername("AAA");
        System.out.println(user);

    }
}
