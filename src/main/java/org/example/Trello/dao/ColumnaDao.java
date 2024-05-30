package org.example.Trello.dao;

import org.example.Trello.model.Columna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ColumnaDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ColumnaDao(DataSource dataSource){
        this.jdbcTemplate=new JdbcTemplate();
    }
    public void addColumna(Columna columna){
        try{
            jdbcTemplate.update("insert into columna (id_columna, nombre,id_tablero) values(?,?,?)",
                    columna.getIdColumna(),columna.getNombre(),columna.getIdTablero());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void updateColumna(Columna columna){
        try{
            jdbcTemplate.update("update columna set nombre=?,id_tablero=? where id_columna=?",
                     columna.getNombre(),columna.getIdTablero(),columna.getIdColumna());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void deleteColumna(Columna columna){
        try{
            jdbcTemplate.update("delete from columna where id_columna=?",
                    columna.getIdColumna());
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
        }
    }
    public List<Columna> getColumnas(){
        try{
            return jdbcTemplate.query("select * from columna",
                    new ColumnaRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
