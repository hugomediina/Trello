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
public class TarjetaDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TarjetaDao(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }

    public void addTarjeta(Tarjeta tarjeta){
        try{
            jdbcTemplate.update("insert into tarjeta ( titulo, descripcion,id_columna) values (?,?,?)",
                    tarjeta.getTitulo(),tarjeta.getDescripcion(),tarjeta.getIdColumna());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void updateTarjeta(Tarjeta tarjeta){
        try{
            jdbcTemplate.update("update tarjeta set titulo=?, descripcion=?,id_columna=? where id_tarjeta=?",
                    tarjeta.getTitulo(),tarjeta.getDescripcion(),tarjeta.getIdColumna(),tarjeta.getIdTarjeta());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void deleteTarjeta(Tarjeta tarjeta){
        try{
            jdbcTemplate.update("delete from tarjeta where id_tarjeta=?",
                    tarjeta.getIdTarjeta());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void deleteTarjeta(int tarjeta){
        try{
            jdbcTemplate.update("delete from tarjeta where id_tarjeta=?",
                    tarjeta);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public Columna getTablero(int columnaId) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM columna WHERE id_columna=?",
             new ColumnaRowMapper(),columnaId);
        } catch (EmptyResultDataAccessException e) {
            return null; 
        }
    }
    public Tarjeta getTarjeta(int id_tarjeta){
        try{
            return jdbcTemplate.queryForObject("select * from tarjeta where id_tarjeta=?",
            new TarjetaRowMapper(),id_tarjeta);
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }
    public List<Tarjeta> getTarjetas(){
        try{
            return jdbcTemplate.query("select * from tarjeta",
                    new TarjetaRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
