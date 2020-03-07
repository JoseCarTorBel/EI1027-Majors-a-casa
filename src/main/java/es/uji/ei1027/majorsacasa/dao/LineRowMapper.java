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
        line.setConcept(rs.getString("concept"));
        line.setRequest((Request) rs.getObject("codrequest"));  //TODO esto no se si esta bien asi
        line.setPrice(rs.getFloat("price"));
        return line;

    }
}
