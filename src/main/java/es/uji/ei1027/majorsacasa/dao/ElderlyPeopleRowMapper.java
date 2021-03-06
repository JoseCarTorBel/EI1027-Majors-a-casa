package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.ElderlyPeople;

import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ElderlyPeopleRowMapper implements RowMapper<ElderlyPeople> {

    @Override
    public ElderlyPeople mapRow(ResultSet rs, int i) throws SQLException {
        ElderlyPeople elderlyPeople = new ElderlyPeople();
        elderlyPeople.setDni(rs.getString("dni"));
        elderlyPeople.setName(rs.getString("name"));
        elderlyPeople.setSecondName(rs.getString("secondname"));
        elderlyPeople.setPhone(rs.getString("phone"));
        Date date=rs.getDate("dateofbirth");
        elderlyPeople.setDateOfBirth(date != null ? date.toLocalDate() : null);
        elderlyPeople.setPostAddress(rs.getString("postaddress"));
        String state = rs.getString("state");
        elderlyPeople.setState(state!=null?state.charAt(0):null);
        elderlyPeople.setJustification(rs.getString("justification"));
        elderlyPeople.setDniSocialWorker(rs.getString("dnisocialworker"));
        elderlyPeople.setEmail(rs.getString("email"));
        elderlyPeople.setUsername(rs.getString("username"));
        elderlyPeople.setPasswd(rs.getString("passwd"));
        return elderlyPeople;
    }
}
