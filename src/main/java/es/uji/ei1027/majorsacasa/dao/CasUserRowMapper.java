package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CasUserRowMapper implements RowMapper<UserDetails> {

    @Override
    public UserDetails mapRow(ResultSet rs, int i) throws SQLException {
        UserDetails userDetails = new UserDetails();
        userDetails.setUsername(rs.getString("username"));
        userDetails.setPassword(rs.getString("passwd"));
        userDetails.setRol(rs.getString("rol"));

        return userDetails;
    }
}
