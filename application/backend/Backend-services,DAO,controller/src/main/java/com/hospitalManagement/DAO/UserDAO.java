package com.hospitalManagement.DAO;

import java.sql.SQLException;

public interface UserDAO {
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException;

}
