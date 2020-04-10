package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Volunteer;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolunteerRowMapper implements RowMapper<Volunteer> {

    @Override
    public Volunteer mapRow(ResultSet rs, int i) throws SQLException {
        Volunteer volunteer = new Volunteer();
        volunteer.setDni(rs.getString("dni"));
        volunteer.setName(rs.getString("name"));
        volunteer.setSecondName(rs.getString("secondname"));
        volunteer.setPhone(rs.getString("phone"));
        Date date=rs.getDate("dateofbirth");
        volunteer.setDateOfBirth(date != null ? date.toLocalDate() : null);
        volunteer.setPostAddress(rs.getString("postaddress"));
        String state = rs.getString("state");
        volunteer.setState(state!=null?state.charAt(0):null);
        volunteer.setEmail(rs.getString("email"));
        volunteer.setUsername(rs.getString("username"));
        volunteer.setPasswd(rs.getString("passwd"));
        return volunteer;
    }
}
