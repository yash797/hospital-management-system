package com.hospitalManagement.DAO;

import com.hsbc.models.User;
import com.hsbc.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserDAO {
    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws SQLException {
        User user = null;
        try (Connection conn = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getInt("role_id"));
            }
        }
        return user;
    }
}
