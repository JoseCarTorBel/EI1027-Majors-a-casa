package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a contract
     * @param contract
     */
    public void addContract(Contract contract){
        jdbcTemplate.update("INSERT INTO contract VALUES (?,?,?,?,?)",
                contract.getCifcompany(), contract.getService(), contract.getInitialDate(), contract.getFinalDate(), contract.getPrice()
        );
    }

    /**
     * update a contract
     * @param contract
     */
    public void updateContract(Contract contract){
        jdbcTemplate.update("UPDATE contract SET service=?, initialtime=?, finaltime=?, price=? WHERE cifcompany=?",
                contract.getService(), contract.getInitialDate(), contract.getFinalDate(), contract.getPrice(), contract.getCifcompany()
                );
    }

    /**
     * remove a contract
     * @param contract
     */
    public void removeContract(Contract contract){
        jdbcTemplate.update("DELETE FROM contract WHERE cifcompany=? ",contract.getCifcompany());
    }


    /**
     * get a contract
     * @param cifCompany
     * @return Contract
     */
    public Contract getContract(String cifCompany){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM contract WHERE cifcompany=?",
                    new ContractRowMapper() ,cifCompany);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Listado de contratos vigentes en comparación con hoy.
     * @return List<Contract>
     */
    public List<Contract> getListContractVigente(){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                                            "FROM contract " +
                                            "WHERE initialtime<= NOW() AND finaltime>=NOW();",
                                        new ContractRowMapper());

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }

    }

    /**
     * Listado de contrados NO vigentes comparados con hoy.
     * @return List<Contract>
     */
    public List<Contract> getListContractPasados(){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                                            "FROM contract " +
                                            "WHERE finaltime<=NOW();",
                                        new ContractRowMapper());

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }
    }
}
