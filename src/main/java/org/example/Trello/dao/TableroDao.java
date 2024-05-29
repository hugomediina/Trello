package org.example.Trello.dao;

import org.example.Trello.model.Tablero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TableroDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {jdbcTemplate = new JdbcTemplate(dataSource);}

    public void addTablero(Tablero tablero){
        jdbcTemplate.update("INSERT INTO tablero (id_tablero, nombre, usuario) values(?,?,?)",
                tablero.getIdTablero(),tablero.getNombre(),tablero.getUsuario());
    }

    public void updateTablero(Tablero tablero){
        jdbcTemplate.update("update tablero set  nombre=?, usuario=? where id_tablero=?",
                tablero.getNombre(), tablero.getUsuario(), tablero.getIdTablero());
    }
    public void deleteTablero(Tablero tablero){
        jdbcTemplate.update("delete from tablero where id_tablero=?",
                tablero.getIdTablero());
    }
}
