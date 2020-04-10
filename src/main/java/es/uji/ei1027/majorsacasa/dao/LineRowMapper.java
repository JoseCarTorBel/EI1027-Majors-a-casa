package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Line;
import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LineRowMapper implements RowMapper<Line> {

    @Override
    public Line mapRow(ResultSet rs, int i) throws SQLException {

        Line line = new Line();

        line.setCodInvoice(rs.getString("codinvoice"));
        line.setCodRequest(rs.getString("codrequest"));
        line.setConcept(rs.getString("concept"));
        line.setPrice(rs.getFloat("price"));
        return line;

    }
}
