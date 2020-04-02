package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRowMapper implements RowMapper<Company> {


    @Override
    public Company mapRow(ResultSet rs, int i) throws SQLException {
        Company company = new Company();

        company.setCif(rs.getString("cif"));
        company.setName(rs.getString("name"));
        company.setPersonalContact(rs.getString("personalContact"));
        company.setPhoneContact(rs.getString("phonecontact"));
        company.setEmail(rs.getString("email"));
        company.setPostAddress(rs.getString("postaddress"));
        company.setUsername(rs.getString("username"));
        company.setUsername(rs.getString("passwd"));

        return company;
    }
}
