package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class DirectionRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int i) throws SQLException {


        return rs.getString("postaddress");

    }
}
