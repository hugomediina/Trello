package org.example.Trello.dao;

import org.example.Trello.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsRowMapper implements RowMapper<UserDetails> {
    @Override
    public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(rs.getString("username"));
        userDetails.setEmail(rs.getString("email"));
        userDetails.setPassword(rs.getString("password"));
        userDetails.setTipo(rs.getInt("tipo"));
        return userDetails;
    }
}
