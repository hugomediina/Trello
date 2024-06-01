package org.example.Trello.dao;

import org.example.Trello.model.Tablero;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableroRowMapper implements RowMapper<Tablero> {
    @Override
    public Tablero mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tablero tablero = new Tablero();
        tablero.setIdTablero(rs.getInt("id_tablero"));
        tablero.setNombre(rs.getString("nombre"));
        tablero.setUsuario(rs.getString("usuario"));
        return tablero;
    }
}
