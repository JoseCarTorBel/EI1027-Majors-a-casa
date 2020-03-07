package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class CompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    public void addCompany(Company company){
        jdbcTemplate.update("INSERT INTO company WHERE dni=?");
    }

    public void removeCompany(String cif){
        jdbcTemplate.update("DELETE FROM company WHERE cif=?",cif);
    }

    public void getComany(String cif){
        jdbcTemplate.queryForObject("SELECT * FROM company WHERE cif=?",
                                    new CompanyRowMapper(),cif);
    }



}
