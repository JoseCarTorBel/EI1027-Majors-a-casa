package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {


    @Override
    public Request mapRow(ResultSet rs, int i) throws SQLException {
        Request request = new Request();

        request.setState(rs.getString("state").charAt(0));
        request.setService((ServiceType) rs.getObject("servicetype")); //TODO esto no se si esta bien asi
        request.setInitialDate(rs.getDate("requestdate"));
        request.setAprovedDate(rs.getDate("approvedDate"));
        request.setRejected(rs.getBoolean("rejected"));
        request.setEndDate(rs.getDate("enddate"));
        request.setDniElderlyPeople(rs.getString("dnielderlypeople"));

        return request;
    }
}
