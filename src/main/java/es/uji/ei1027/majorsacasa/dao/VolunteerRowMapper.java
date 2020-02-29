package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Volunteer;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
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
        volunteer.setDateOfBirith(rs.getDate("dateofbrith"));
        volunteer.setPostaddress(rs.getString("postaddress"));
        volunteer.setState(rs.getString("state").charAt(0));
        volunteer.setMail(rs.getString("email"));
        volunteer.setUsername(rs.getString("username"));
        volunteer.setPassword(rs.getString("passwd"));
        return volunteer;
    }
}
