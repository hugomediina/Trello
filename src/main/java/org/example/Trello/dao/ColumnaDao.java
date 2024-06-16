package org.example.Trello.dao;

import org.example.Trello.model.Columna;
import org.example.Trello.model.Tablero;
import org.example.Trello.model.Tarjeta;
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
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }
    public void addColumna(Columna columna){
        try{
            jdbcTemplate.update("insert into columna ( nombre,id_tablero) values(?,?)",
                    columna.getNombre(),columna.getIdTablero());
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
    public void deleteColumna(int columna){
        try{
            jdbcTemplate.update("delete from columna where id_columna=?",
                    columna);
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
        }
    }
    public List<Columna> getColumnas(int tablero){
        try{
            return jdbcTemplate.query("select * from columna where id_tablero=?",
                    new ColumnaRowMapper(),tablero);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
    public Tablero getTablero(int tablero){
        try{
            return jdbcTemplate.queryForObject("select * from tablero where id_tablero=?",
                    new TableroRowMapper(),tablero);
        }catch (EmptyResultDataAccessException e){
            return new Tablero();
        }
    }
    public List<Tarjeta> getTarjetas(int columna){
        try{
            return jdbcTemplate.query("select * from tarjeta where id_columna=?",
                    new TarjetaRowMapper(),columna);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
