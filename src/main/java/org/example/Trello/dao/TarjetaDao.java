package org.example.Trello.dao;

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
            jdbcTemplate.update("insert into tarjeta (id_tarjeta, titulo, descrpcion,id_columna) values (?,?,?,?)",
                    tarjeta.getIdTarjeta(),tarjeta.getTitulo(),tarjeta.getDescripcion(),tarjeta.getIdColumna());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void updateTarjeta(Tarjeta tarjeta){
        try{
            jdbcTemplate.update("update tarjeta set titulo=?, descripcion=?,id_colmna=? where id_tarjeta=?",
                    tarjeta.getTitulo(),tarjeta.getDescripcion(),tarjeta.getIdColumna(),tarjeta.getIdColumna());
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
    public List<Tarjeta> getTarjetas(){
        try{
            return jdbcTemplate.query("select * from tarjeta",
                    new TarjetaRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
