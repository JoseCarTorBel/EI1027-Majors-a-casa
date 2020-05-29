package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
        jdbcTemplate.update("INSERT INTO company VALUES (?,?,?,?,?,?,?,?,?,NOW())",
                      company.getCif(),company.getName(),company.getNifContact(),company.getPersonalContact(),
                            company.getPhoneContact(),company.getEmail(),company.getPostAddress(),null,null);
    }

    /**
     * update a company
     * @param company
     */
    public void updateCompany(Company company){
    //    System.out.println(company.getPhoneContact());
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

    public List<ElderlyPeople> getServicesToDo(String cif){
        try {
                return jdbcTemplate.query(
                        "SELECT person.*, ep.*\n" +
                                "FROM person     JOIN elderlypeople AS ep ON person.dni =ep.dni\n" +
                                "JOIN request AS req ON ep.dni=req.dniElderlyPeople\n" +
                                "JOIN contract AS con ON req.servicetype = con.service\n" +
                                "WHERE con.cifcompany=? " +
                                "AND con.initialtime<=NOW() AND con.finaltime>=NOW();"
                                        ,new ElderlyPeopleRowMapper(), cif);

        }catch(EmptyResultDataAccessException e){
            return new ArrayList<ElderlyPeople>();
        }
    }

    public List<Company> getCompanys(){
        try{
            return jdbcTemplate.query("SELECT * FROM company;",
                                        new CompanyRowMapper());
        }catch (EmptyResultDataAccessException e){
            return new ArrayList<Company>();
        }
    }

    public List<Contract> getPastContracts(String cif){
        try{
            return jdbcTemplate.query("SELECT * " +
                    "                       FROM contract " +
                    "                       WHERE cifcompany=? AND finaltime<NOW()",new ContractRowMapper(),cif);
        }catch(EmptyResultDataAccessException e){
            return new ArrayList<Contract>();
        }
    }

    public Contract getCurrentContract(String cif) {
        try{
            return jdbcTemplate.queryForObject("SELECT * " +
                    "                       FROM contract " +
                    "                       WHERE cifcompany=? AND finaltime>NOW()", new ContractRowMapper(), cif);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }


    }

    public Company getCompanyWithContract(String codcontract){
        return jdbcTemplate.queryForObject("SELECT company.* " +
                "                               FROM company " +
                "                               WHERE cif=( SELECT cifcompany " +
                "                                           FROM contract " +
                "                                           WHERE codcontract = ?);\n",new CompanyRowMapper(),codcontract);
    }

    public Contract getContract(String codcontract){
        return jdbcTemplate.queryForObject("SELECT * FROM contract WHERE codcontract=?;\n",new ContractRowMapper(),codcontract);
    }





}
