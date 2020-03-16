package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Disponibility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisponibilityRowMapper implements RowMapper<Disponibility> {


    @Override
    public Disponibility mapRow(ResultSet rs, int i) throws SQLException {
        Disponibility disponibility = new Disponibility();

        disponibility.setDayOfWeek(rs.getInt("dayofweek"));
        Date date=rs.getDate("initialtime");
        disponibility.setInitialTime(date != null ? date.toLocalDate() : null);
        date=rs.getDate("finaltime");
        disponibility.setFinalTime(date != null ? date.toLocalDate() : null);
        disponibility.setOpen(rs.getBoolean("open"));


        return disponibility;
    }
}
