package org.example.Trello.dao;

import org.example.Trello.model.Columna;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnaRowMapper implements RowMapper<Columna> {
    @Override
    public Columna mapRow(ResultSet rs, int rowNum) throws SQLException {
        Columna columna = new Columna();
        columna.setIdColumna(rs.getInt("id_columna"));
        columna.setNombre(rs.getString("nombre"));
        columna.setIdTablero(rs.getInt("id_tablero"));
        columna.setPosicion(rs.getInt("posicion"));
        return columna;
    }
}
