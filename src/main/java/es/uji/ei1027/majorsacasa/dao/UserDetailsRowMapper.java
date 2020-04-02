package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDetailsRowMapper implements RowMapper<UserDetails> {

public UserDetails mapRow(ResultSet rs, int i) throws SQLException{
    UserDetails userDetails = new UserDetails();
    userDetails.setUsername(rs.getString("username"));
    userDetails.setPassword(rs.getString("passwd"));
        return userDetails;
        }
}
