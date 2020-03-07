package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.SocialWorker;

import org.springframework.jdbc.core.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocialWorkerRowMapper implements RowMapper<SocialWorker> {

    public SocialWorker mapRow(ResultSet rs, int i) throws SQLException{
        SocialWorker socialWorker = new SocialWorker();
        socialWorker.setDni(rs.getString("dni"));
        socialWorker.setName(rs.getString("name"));
        socialWorker.setPhone(rs.getInt("phone"));
        return socialWorker;
    }
}
