package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CompanyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a company
     * @param company
     */
    public void addCompany(Company company){
        jdbcTemplate.update("INSERT INTO company VALUES (?,?,?,?,?,?,?,?)",
                      company.getCif(),company.getName(),company.getPersonalContact(),
                            company.getPhoneContact(),company.getEmail(),company.getPostAddress(),null,null);
    }

    /**
     * update a company
     * @param company
     */
    public void updateCompany(Company company){
        jdbcTemplate.update("UPDATE company SET name=?, personalcontact=?, phonecontact=?, email=?, postaddress=? WHERE cif=?",
                company.getName(),company.getPersonalContact(),company.getPhoneContact(),company.getEmail(),company.getPostAddress(), company.getCif()
        );
    }

    /**
     * remove a company
     * @param cif
     */
    public void removeCompany(String cif){
        jdbcTemplate.update("DELETE FROM company WHERE cif=?",cif);
    }


    /**
     * get a company
     * @param cif
     * @return Company
     */
    public Company getCompany(String cif){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM company WHERE cif=?",
                                    new CompanyRowMapper(),cif);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void setUsernameAndPasswd(String cif,String username, String passwd){
        jdbcTemplate.update("UPDATE company SET username=?, passwd=? WHERE cif=?",
                username,passwd,cif
        );
    }



}
