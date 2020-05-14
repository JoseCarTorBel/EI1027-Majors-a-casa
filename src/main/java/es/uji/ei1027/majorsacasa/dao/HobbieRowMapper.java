package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Hobbie;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HobbieRowMapper implements RowMapper<Hobbie> {

    @Override
    public Hobbie mapRow(ResultSet rs, int i) throws SQLException {
        Hobbie hobbie = new Hobbie();
        hobbie.setDni(rs.getString("dni"));
        hobbie.setHobbie(rs.getString("hobbie"));
        return hobbie;
    }
}
