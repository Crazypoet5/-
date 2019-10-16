package dao;

import domain.User;

import java.sql.SQLException;

public interface UserDao  {
    void addDao(User user) throws SQLException;
    User getByCode(String code) throws SQLException;
    void update(User user) throws SQLException;
    User login(String username, String password) throws SQLException;
}
