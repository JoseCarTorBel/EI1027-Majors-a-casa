package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.ElderlyPeople;

import org.springframework.jdbc.core.RowMapper;

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
        elderlyPeople.setDateOfBirth(rs.getDate("dateofbrith"));
        elderlyPeople.setPostAddress(rs.getString("postaddress"));
        elderlyPeople.setJustification(rs.getString("justification"));
        elderlyPeople.setDniSocialWorker(rs.getString("dnisocialworker"));
        elderlyPeople.setEmail(rs.getString("email"));
        elderlyPeople.setUsername(rs.getString("username"));
        elderlyPeople.setPasswd(rs.getString("passwd"));
        return elderlyPeople;
    }
}
