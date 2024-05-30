package org.example.Trello.dao;

import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDetailsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailsDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void addUser(UserDetails userDetails){
        try{
            jdbcTemplate.update("insert into userdetails (username,email,password,tipo) values(?,?,?,?)",
                    userDetails.getUsername(),userDetails.getEmail(),userDetails.getPassword(),userDetails.getTipo());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void updateUser(UserDetails userDetails){
        try{
            jdbcTemplate.update("update userdetails set email=?,password=?,tipo=? where username=?",
                    userDetails.getEmail(),userDetails.getPassword(),userDetails.getTipo(),userDetails.getUsername());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void deleteUser(UserDetails userDetails){
        try {
            jdbcTemplate.update("delete from userdetails where username=?",
                    userDetails.getUsername());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }

    public List<UserDetails> getUsers(){
        try{
            return jdbcTemplate.query("select * from userdetails",
                    new UserDetailsRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
