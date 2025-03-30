package org.example.bluemoon.services;

import org.example.bluemoon.dao.UserDAO;
import org.example.bluemoon.models.User;
import org.example.bluemoon.security.PasswordUtil;

public class LoginService {
    public User login(String username, String password) {
        UserDAO userDao = new UserDAO();
        User user = userDao.get(username);
        if (PasswordUtil.verifyPassword(user.getPassword(), password)) {
            return user;
        }
        return null;
    }
}
