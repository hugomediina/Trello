package org.example.Trello.dao;

import org.example.Trello.model.Tarjeta;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TarjetaRowMapper implements RowMapper<Tarjeta> {
    @Override
    public Tarjeta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setIdTarjeta(rs.getInt("id_tarjeta"));
        tarjeta.setTitulo(rs.getString("titulo"));
        tarjeta.setDescripcion(rs.getString("descripcion"));
        tarjeta.setIdColumna(rs.getInt("id_columna"));
        return tarjeta;
    }
}
