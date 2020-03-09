package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class ContractDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a contract
     * @param contract, codRequest, cifCompany
     */
    public void addContract(Contract contract, String codRequest, String cifCompany){
        jdbcTemplate.update("INSERT INTO contract VALUES (?,?,?,?,?,?)",
                codRequest, cifCompany, contract.getService(), contract.getInitialDate(), contract.getFinalDate(), contract.getPrice()
        );
    }

    /**
     * update a contract
     * @param contract, codRequest, cifCompany
     */
    public void updateContract(Contract contract, String codRequest, String cifCompany){
        jdbcTemplate.update("UPDATE contract SET service=?, initialtime=?, finaltime=?, price=? WHERE codrequest=? AND cifcompany=?",
                contract.getService(), contract.getInitialDate(), contract.getFinalDate(), contract.getPrice(),codRequest, cifCompany
                );
    }

    /**
     * remove a contract
     * @param codRequest, cifCompany
     */
    public void removeContract(String codRequest, String cifCompany){
        jdbcTemplate.update("DELETE FROM contract WHERE codrequest=? AND cifcompany=? ",codRequest,cifCompany);
    }


    /**
     * get a contract
     * @param codRequest, cifCompany
     * @return Contract
     */
    public Contract getContract(String codRequest, String cifCompany){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM contract WHERE codrequest=? AND cifcompany=?",
                    new ContractRowMapper(),codRequest,cifCompany);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
