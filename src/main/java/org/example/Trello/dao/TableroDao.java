package org.example.Trello.dao;

import org.example.Trello.model.Tablero;
import org.example.Trello.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TableroDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TableroDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addTablero(Tablero tablero){
        try{
            jdbcTemplate.update("insert into tablero (nombre, usuario) values(?,?)",
                    tablero.getNombre(),tablero.getUsuario());
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
        }
    }

    public void updateTablero(Tablero tablero){
        try{
            jdbcTemplate.update("update tablero set  nombre=?, usuario=? where id_tablero=?",
                    tablero.getNombre(), tablero.getUsuario(), tablero.getIdTablero());
        }catch (EmptyResultDataAccessException e ){
            e.printStackTrace();
        }
    }
    public void deleteTablero(Tablero tablero){
        try {
            jdbcTemplate.update("delete from tablero where id_tablero=?",
                    tablero.getIdTablero());
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public void deleteTablero(int id_tablero){
        try {
            jdbcTemplate.update("delete from tablero where id_tablero=?",
                    id_tablero);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
    }
    public List<Tablero> getTableros(String usuario){
        try{
            return jdbcTemplate.query("select * from tablero where usuario=?",
                     new TableroRowMapper(),usuario);
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<>();
        }
    }
}
