package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {


    @Override
    public Request mapRow(ResultSet rs, int i) throws SQLException {
        Request request = new Request();

        request.setCodRequest(rs.getString("codrequest"));
        request.setState(rs.getString("state").charAt(0));
        int service = rs.getInt("servicetype");                      //Primero cojo el int de la BD, despues meto el enum en el modelo
        request.setService(ServiceType.getOpcion(service));        //TODO cuando se implemente comprobar que va bien
        Date date=rs.getDate("requestdate");
        request.setInitialDate(date != null ? date.toLocalDate() : null);
        date=rs.getDate("approvedDate");
        request.setAprovedDate(date != null ? date.toLocalDate() : null);
        date=rs.getDate("enddate");
        request.setEndDate(date != null ? date.toLocalDate() : null);
        request.setRejected(rs.getBoolean("rejected"));
        request.setServiceHour(rs.getTime("servicehour"));
        request.setPrice(rs.getFloat("price"));
        request.setDniElderlyPeople(rs.getString("dnielderlypeople"));
        request.setCifCompany(rs.getString("cifcompany"));

        return request;
    }
}
